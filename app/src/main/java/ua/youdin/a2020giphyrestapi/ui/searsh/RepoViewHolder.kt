package ua.youdin.a2020giphyrestapi.ui.searsh

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.databinding.RepoViewItemBinding


class RepoViewHolder(
    private val binding: RepoViewItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo, viewModelShared: SharedSearchRepositoriesViewModel) {
        binding.viewmodel = viewModelShared
        binding.repo = repo
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RepoViewItemBinding.inflate(layoutInflater, parent, false)
            return RepoViewHolder(binding)
        }
    }
}
