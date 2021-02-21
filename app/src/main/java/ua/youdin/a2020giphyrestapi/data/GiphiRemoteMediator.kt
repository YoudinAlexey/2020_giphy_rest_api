package ua.youdin.a2020giphyrestapi.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import ua.youdin.a2020giphyrestapi.data.Repository.Companion.NETWORK_PAGE_SIZE
import ua.youdin.a2020giphyrestapi.data.giphyAPI.GiphyService
import ua.youdin.a2020giphyrestapi.data.localDB.RepoDatabase
import ua.youdin.a2020giphyrestapi.data.localDB.model.RemoteKeys
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import java.io.IOException

private const val GIPHI_STARTING_PAGE_INDEX = 0

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator(
    private val apiQuery: String,
    private val service: GiphyService,
    private val repoDatabase: RepoDatabase
) : RemoteMediator<Int, Repo>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Repo>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GIPHI_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                if (remoteKeys == null) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                // If the previous key is null, then we can't request more data
                val prevKey = remoteKeys.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                remoteKeys.prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys == null || remoteKeys.nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                remoteKeys.nextKey
            }

        }
        try {
            val itemsPerPage = state.config.pageSize
            val apiResponse =
                service.searchRepos(apiQuery, page * itemsPerPage, itemsPerPage)
                    .apply {
                        // after load add corect request fild
                        this.items.forEach {
                            it.request = apiQuery
                        }
                    }.also {
                    Log.d("Tag", "\n______________Страница ____ $page _______________________\n")
                    Log.d("Tag", it.toString())
                }

            val repos = apiResponse.items
            val endOfPaginationReached = repos.isEmpty()
            repoDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    repoDatabase.remoteKeysDao().clearRemoteKeys()
                    repoDatabase.reposDao().clearRepos()
                }
                val prevKey = if (page == GIPHI_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = repos.map {
                    RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                repoDatabase.remoteKeysDao().insertAll(keys)
                repoDatabase.reposDao().insertAll(repos)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Repo>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                    repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Repo>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                    repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Repo>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                    repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }

}