package ua.youdin.a2020giphyrestapi.data.localDB.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
        @PrimaryKey val id: String,
        val prevKey: Int?,
        val nextKey: Int?
)
