package ua.youdin.a2020giphyrestapi.ui.searsh

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.youdin.a2020giphyrestapi.data.localDB.model.Repo
import ua.youdin.a2020giphyrestapi.databinding.RepoViewItemHorizontalBinding
import ua.youdin.a2020giphyrestapi.databinding.RepoViewItemVerticalBinding
import ua.youdin.a2020giphyrestapi.ui.SharedSearchRepositoriesViewModel

class RepoViewHolderHorizontal (
    private val binding: RepoViewItemHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): RepoViewHolderHorizontal {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RepoViewItemHorizontalBinding.inflate(layoutInflater, parent, false)
                return RepoViewHolderHorizontal(binding)
            }
        }
    }