package ua.youdin.a2020giphyrestapi.data.giphyAPI

import com.google.gson.annotations.SerializedName
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo


data class RepoSearchResponse(
    @SerializedName("data") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)
