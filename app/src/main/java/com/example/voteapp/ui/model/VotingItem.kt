package com.example.voteapp.ui.model

data class VotingItem(
    val id: String,
    val img_url: String,
    val name: String,
    val vote_count: Int,
    val vote_time_status: Int
)