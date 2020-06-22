package com.example.voteapp.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voteapp.R
import com.example.voteapp.ui.adapter.VoteAdapter
import com.example.voteapp.ui.model.VotingItem
import com.example.voteapp.ui.viewmodel.VoteViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), VoteAdapter.ClickListener {

    private lateinit var voteViewModel: VoteViewModel
    private lateinit var foodAdapter: VoteAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        foodAdapter= VoteAdapter()
        foodAdapter.setClickListener(this)
        recKing.apply {
            adapter = foodAdapter
            layoutManager = viewManager
        }
        observeViewModel()
    }
    private fun observeViewModel(){
        voteViewModel= ViewModelProvider(this).get(VoteViewModel::class.java)
        voteViewModel.getResult().observe(viewLifecycleOwner, Observer {
            foodAdapter.updateList(it)
        }
        )
    }
    override fun onResume() {
        super.onResume()
        voteViewModel.loadKing()
    }

    override fun onClick(vote: VotingItem) {
        Toast.makeText(context,"${vote.name}", Toast.LENGTH_LONG).show()

            var voteId=vote.id
            var voteName=vote.name
            var voteImg= vote.img_url
            var action= HomeFragmentDirections.actionNavigationHomeToVoteFragment(voteId,voteName,voteImg)
            findNavController().navigate(action)

    }
}