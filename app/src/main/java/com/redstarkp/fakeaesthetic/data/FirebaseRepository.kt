package com.redstarkp.fakeaesthetic.data

import com.google.firebase.firestore.FirebaseFirestore
import com.redstarkp.fakeaesthetic.model.Post

class FirebaseRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getFeed(callback: (List<Post>) -> Unit) {
        db.collection("posts")
            .orderBy("createdAt")
            .limit(50)
            .get()
            .addOnSuccessListener { result ->
                val posts = mutableListOf<Post>()
                for (document in result) {
                    val post = document.toObject(Post::class.java).copy(id = document.id)
                    posts.add(post)
                }
                callback(posts.reversed())
            }
            .addOnFailureListener {
                callback(emptyList())
            }
    }

    fun addPost(post: Post, callback: (Boolean) -> Unit) {
        db.collection("posts")
            .add(post)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }
}