package com.example.voteapp.ui.api


import com.example.voteapp.ui.model.VoteResponse
import com.example.voteapp.ui.model.Voting
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VoteApiInterface {
    @GET("king")
    fun getKing(): Call<Voting>

    @POST("kingvote")
    fun voteKing(
        @Query("code") code:String,
        @Query("king_id") kingId: String?
    ): Call<VoteResponse>

}