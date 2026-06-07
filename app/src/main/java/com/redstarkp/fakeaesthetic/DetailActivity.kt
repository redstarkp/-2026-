package com.redstarkp.fakeaesthetic

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.redstarkp.fakeaesthetic.model.Post

class DetailActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var descriptionView: TextView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.detail_image)
        titleView = findViewById(R.id.detail_title)
        descriptionView = findViewById(R.id.detail_description)

        val postId = intent.getStringExtra("post_id")
        if (postId != null) {
            loadPostDetail(postId)
        }
    }

    private fun loadPostDetail(postId: String) {
        db.collection("posts").document(postId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val title = document.getString("title") ?: "No title"
                    val description = document.getString("description") ?: "No description"
                    val imageUrl = document.getString("imageUrl") ?: ""

                    titleView.text = title
                    descriptionView.text = description
                    if (imageUrl.isNotEmpty()) {
                        Glide.with(this).load(imageUrl).into(imageView)
                    }
                }
            }
    }
}