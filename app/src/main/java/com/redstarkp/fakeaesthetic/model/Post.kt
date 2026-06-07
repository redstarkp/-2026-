package com.redstarkp.fakeaesthetic.model

data class Post(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val authorId: String = "",
    val createdAt: Long = 0
)