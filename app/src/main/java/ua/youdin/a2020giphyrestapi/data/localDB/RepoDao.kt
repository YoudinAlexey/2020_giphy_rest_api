package ua.youdin.a2020giphyrestapi.data.localDB

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Repo>)

    @Query("SELECT * FROM repos WHERE request LIKE :queryString OR title LIKE :queryString")
    fun reposByName(queryString: String): PagingSource<Int, Repo>

    @Query("DELETE FROM repos")
    suspend fun clearRepos()
}