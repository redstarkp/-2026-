package com.redstarkp.fakeaesthetic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.redstarkp.fakeaesthetic.adapter.FeedAdapter
import com.redstarkp.fakeaesthetic.data.FirebaseRepository
import com.redstarkp.fakeaesthetic.model.Post

class FeedActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter
    private lateinit var logoutButton: Button
    private val repository = FirebaseRepository()
    private val posts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        recyclerView = findViewById(R.id.feed_recycler_view)
        logoutButton = findViewById(R.id.logout_button)

        adapter = FeedAdapter(posts) { post ->
            navigateToDetail(post)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        logoutButton.setOnClickListener {
            logout()
        }

        loadFeed()
    }

    private fun loadFeed() {
        repository.getFeed { fetchedPosts ->
            posts.clear()
            posts.addAll(fetchedPosts)
            adapter.notifyDataSetChanged()
        }
    }

    private fun navigateToDetail(post: Post) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("post_id", post.id)
        startActivity(intent)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}