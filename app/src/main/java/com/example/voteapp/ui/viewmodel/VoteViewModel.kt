package com.example.voteapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.voteapp.ui.api.VoteApi
import com.example.voteapp.ui.model.Voting
import com.example.voteapp.ui.model.VotingItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteViewModel : ViewModel() {
    var results: MutableLiveData<List<VotingItem>> = MutableLiveData()

    //getter..........
    fun getResult(): LiveData<List<VotingItem>> = results

    private val voteApi: VoteApi = VoteApi()//obj build init block work

    //setter..........
    fun loadResult() {
        val apiCall = voteApi.getKing()

      apiCall.enqueue(Callback<List<VotingItem>>{

      })
    }
}