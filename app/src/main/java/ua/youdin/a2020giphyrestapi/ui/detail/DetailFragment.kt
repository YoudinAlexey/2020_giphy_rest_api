package ua.youdin.a2020giphyrestapi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ua.youdin.a2020giphyrestapi.R
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.databinding.FragmentDetailBinding
import ua.youdin.a2020giphyrestapi.ui.searsh.ReposAdapter
import ua.youdin.a2020giphyrestapi.ui.searsh.ReposLoadStateAdapter
import ua.youdin.a2020giphyrestapi.ui.SharedSearchRepositoriesViewModel
import ua.youdin.a2020giphyrestapi.ui.searsh.ReposAdapter.Companion.TypeLayout.HORIZONTAL


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<SharedSearchRepositoriesViewModel>()
    private lateinit var adapter: ReposAdapter
    private var searchJob: Job? = null
    private var position:Int = 0

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val repo: Repo = DetailFragmentArgs.fromBundle(requireArguments()).repo
        position = DetailFragmentArgs.fromBundle(requireArguments()).position as Int
        binding.repo = repo
        binding.lifecycleOwner = this
        adapter = ReposAdapter(viewModel, HORIZONTAL)
        initAdapter()
        search(repo.request)

        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.qlist.scrollToPosition(position) }
        }
        return binding.root
    }

    private fun initAdapter() {
        binding.qlist.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}