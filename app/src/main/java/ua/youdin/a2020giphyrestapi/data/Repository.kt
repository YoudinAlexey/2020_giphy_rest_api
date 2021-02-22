package ua.youdin.a2020giphyrestapi.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.youdin.a2020giphyrestapi.data.giphyAPI.GiphyService
import ua.youdin.a2020giphyrestapi.data.localDB.RepoDatabase
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo

@OptIn(ExperimentalPagingApi::class)
class Repository(
    private val service: GiphyService,
    private val database: RepoDatabase
) {
    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        Log.d("GithubRepository", "New query: $query")

        // appending '%' so we can allow other characters to be before and after the query string
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { database.reposDao().reposByName(dbQuery) }

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = GithubRemoteMediator(
                query,
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 25
    }
}
