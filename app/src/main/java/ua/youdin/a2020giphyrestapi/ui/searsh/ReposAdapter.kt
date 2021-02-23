package ua.youdin.a2020giphyrestapi.ui.searsh

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.ui.SharedSearchRepositoriesViewModel


class ReposAdapter(
    private val viewModelShared: SharedSearchRepositoriesViewModel,
    private val type: TypeLayout
) : PagingDataAdapter<Repo, ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (type) {
            TypeLayout.VERTICAL -> RepoViewHolderVertical.create(parent)
            TypeLayout.HORIZONTAL -> RepoViewHolderHorizontal.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            when (type) {
                TypeLayout.VERTICAL -> (holder as RepoViewHolderVertical).bind(repoItem, viewModelShared, position)
                TypeLayout.HORIZONTAL ->(holder as RepoViewHolderHorizontal).bind(repoItem)
            }

        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }

        enum class TypeLayout {
            VERTICAL, HORIZONTAL
        }
    }
}


