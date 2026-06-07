package com.redstarkp.fakeaesthetic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redstarkp.fakeaesthetic.databinding.ItemFeedMotionBinding
import com.redstarkp.fakeaesthetic.model.Post

class FeedAdapter(
    private val posts: List<Post>,
    private val onItemClick: (Post) -> Unit
) : RecyclerView.Adapter<FeedAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val binding: ItemFeedMotionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.itemTitle.text = post.title
            binding.itemDescription.text = post.description
            if (post.imageUrl.isNotEmpty()) {
                Glide.with(binding.root).load(post.imageUrl).into(binding.itemImage)
            }
            binding.root.setOnClickListener {
                onItemClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemFeedMotionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size
}