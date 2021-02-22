package ua.youdin.a2020giphyrestapi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ua.youdin.a2020giphyrestapi.R
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.databinding.FragmentDetailBinding
import ua.youdin.a2020giphyrestapi.ui.searsh.SharedSearchRepositoriesViewModel


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<SharedSearchRepositoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val repo: Repo = DetailFragmentArgs.fromBundle(requireArguments()).repo
        binding.repo = repo
        binding.lifecycleOwner = this
        return binding.root
    }

}