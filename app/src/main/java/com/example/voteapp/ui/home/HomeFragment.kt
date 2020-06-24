package com.example.voteapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.voteapp.R
import com.example.voteapp.ui.adapter.VoteAdapter
import com.example.voteapp.ui.model.VotingItem
import com.example.voteapp.ui.viewmodel.VoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), VoteAdapter.ClickListener {

    private lateinit var voteViewModel: VoteViewModel
    private lateinit var voteAdapter: VoteAdapter


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
        pb_vote.visibility = View.VISIBLE
        recKing.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        voteAdapter = VoteAdapter()
        recKing.adapter = voteAdapter
        voteAdapter.setClickListener(this)

        observeViewModel()


    }

    private fun observeViewModel() {

        voteViewModel = ViewModelProvider(this).get(VoteViewModel::class.java)
        voteViewModel.getResult().observe(viewLifecycleOwner, Observer {
            voteAdapter.updateList(it)
            pb_vote.visibility = View.GONE
        }
        )
    }

    override fun onResume() {
        super.onResume()
        voteViewModel.loadKing()

    }

    override fun onClick(vote: VotingItem) {
        Toast.makeText(context, "${vote.name}", Toast.LENGTH_LONG).show()

        var voteId = vote.id
        var voteName = vote.name
        var voteImg = vote.img_url
        var action =
            HomeFragmentDirections.actionNavigationHomeToVoteFragment(voteId, voteName, voteImg)
        findNavController().navigate(action)

    }
}