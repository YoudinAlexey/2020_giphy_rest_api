package ua.youdin.a2020giphyrestapi.ui

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ua.youdin.a2020giphyrestapi.R
import ua.youdin.a2020giphyrestapi.data.Repository
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.ui.searsh.SearchRepositoriesDirections


class SharedSearchRepositoriesViewModel(private val repository: Repository) : ViewModel() {
    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Repo>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    fun onClick(view: View, repo: Repo, position: Int) {
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.urlOriginal))
//        view.context.startActivity(intent)
        val action = SearchRepositoriesDirections.actionSearchRepositories2ToDetailFragment(repo, position)
        view.findNavController().navigate(action)
    }
    fun deletePosition(id:String){
        viewModelScope.launch {
            repository.deletePosition(id)
        }
    }
}
