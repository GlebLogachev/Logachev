package com.glogachev.developerslife.ui.pages.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glogachev.developerslife.App
import com.glogachev.developerslife.databinding.PostsItemBinding
import com.glogachev.developerslife.domain.model.LatesHotTopPost
import com.glogachev.developerslife.ui.pages.GifLoader

class LatestHotTopPostAdapter :
    androidx.recyclerview.widget.ListAdapter<LatesHotTopPost, CryptoViewHolder>(diffUtil) {

    private val gifLoader = GifLoader(App.component.context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CryptoViewHolder(PostsItemBinding.inflate(inflater, parent, false), gifLoader)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class CryptoViewHolder(private val binding: PostsItemBinding, private val gifLoader: GifLoader) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LatesHotTopPost) {
        gifLoader.load(item.gif.replace("http:", "https:")) {
            binding.ivImage.setImageDrawable(it)
        }
        binding.apply {
            tvDescription.text = item.description
        }

    }
}

private val diffUtil = object : DiffUtil.ItemCallback<LatesHotTopPost>() {
    override fun areItemsTheSame(oldItem: LatesHotTopPost, newItem: LatesHotTopPost): Boolean =
        oldItem.description == newItem.description


    override fun areContentsTheSame(oldItem: LatesHotTopPost, newItem: LatesHotTopPost): Boolean =
        oldItem == newItem
}