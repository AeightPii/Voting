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
    fun loadKing() {
        val apiCall = voteApi.getKing()

      apiCall.enqueue(object :Callback<Voting>{
          override fun onFailure(call: Call<Voting>, t: Throwable) {

          }

          override fun onResponse(call: Call<Voting>, response: Response<Voting>) {
              val a = response.body()
              Log.d("response>>>", a.toString())
              response.isSuccessful.let {
                  val resultList: List<VotingItem> = response.body() ?: emptyList()
                  results.value = resultList
              }
          }
      })
    }
}