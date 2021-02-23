package ua.youdin.a2020giphyrestapi.ui.searsh

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.databinding.RepoViewItemVerticalBinding
import ua.youdin.a2020giphyrestapi.ui.SharedSearchRepositoriesViewModel


class RepoViewHolderVertical(
    private val binding: RepoViewItemVerticalBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo, viewModelShared: SharedSearchRepositoriesViewModel, position:Int) {
        binding.position = position
        binding.viewmodel = viewModelShared
        binding.repo = repo
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolderVertical {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RepoViewItemVerticalBinding.inflate(layoutInflater, parent, false)
            return RepoViewHolderVertical(binding)
        }
    }
}
