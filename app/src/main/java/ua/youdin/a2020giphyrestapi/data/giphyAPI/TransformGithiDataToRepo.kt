package ua.youdin.a2020giphyrestapi.data.giphyAPI

import ua.youdin.a2020giphyrestapi.data.giphyAPI.model.SearchResponse
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo

fun SearchResponse.asRepo(request: String): List<Repo> {
    val listRepoDB: MutableList<Repo> = mutableListOf()
    this.items.forEach {
        listRepoDB.add(
            Repo(
                id = it.id,
                request = request,
                title = it.title,
                urlFixedHeight = it.images?.fixed_height?.url ?: "",
                urlFixedHeightSmall = it.images?.fixed_height_small?.url ?: "",
                urlOriginal = it.images?.original?.url ?: "",
                urlFixedWidthSmall = it.images?.fixed_width_small?.url ?: "",
            )
        )
    }
    return listRepoDB
}