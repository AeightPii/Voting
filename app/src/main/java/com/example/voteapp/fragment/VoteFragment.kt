package com.example.voteapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.voteapp.R
import com.example.voteapp.ui.api.VoteApiInterface
import com.example.voteapp.ui.model.VoteResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_vote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class VoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_vote, container, false)
        return root
    }
    val BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var retrofitService = retrofit.create(VoteApiInterface::class.java)

        var messageArgs = arguments?.let { VoteFragmentArgs.fromBundle(it) }
        var kingId = messageArgs?.VoteId
        var name: String? = messageArgs?.voteName
        var voteImg:String?=messageArgs?.voteImg

        Log.d("image", voteImg.toString())
        Picasso.get()
            .load(voteImg)
            .into(img_vote)

        voteName.text = name

        btn_vote.setOnClickListener {
            val code = voteCode.text.toString()
            val apiCall = retrofitService.voteKing(code,kingId)
            apiCall.enqueue(object : Callback<VoteResponse> {
                override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<VoteResponse>,
                    response: Response<VoteResponse>
                ) {
                    Toast.makeText(
                        context,
                        response.body().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

        }

    }


}
