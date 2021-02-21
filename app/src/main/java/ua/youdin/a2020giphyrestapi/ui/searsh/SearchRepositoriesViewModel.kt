package ua.youdin.a2020giphyrestapi.ui.searsh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ua.youdin.a2020giphyrestapi.data.Repository
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo

/**
 * ViewModel for the [SearchRepositoriesActivity] screen.
 * The ViewModel works with the [Repository] to get the data.
 */
class SearchRepositoriesViewModel(private val repository: Repository) : ViewModel() {
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
}
