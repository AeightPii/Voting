package com.example.voteapp.ui.api


import com.example.voteapp.ui.model.Voting
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VoteApiInterface {
    @GET("king")
    fun getKing(): Call<Voting>

}