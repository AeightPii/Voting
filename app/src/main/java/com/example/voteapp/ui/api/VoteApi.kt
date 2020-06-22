package com.example.voteapp.ui.api


import com.example.voteapp.ui.model.Voting
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VoteApi {
    private val voteApiInterface: VoteApiInterface

    companion object{
        const val BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"
    }
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
       voteApiInterface=retrofit.create(VoteApiInterface::class.java)
    }
    fun getKing():Call<Voting>{
        return voteApiInterface.getKing()
    }
}