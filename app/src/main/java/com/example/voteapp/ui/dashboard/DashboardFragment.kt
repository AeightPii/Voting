package com.example.voteapp.ui.dashboard

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
import com.example.voteapp.ui.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(), VoteAdapter.ClickListener {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var dashboardAdapter: VoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recQueen.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        dashboardAdapter = VoteAdapter()
        recQueen.adapter = dashboardAdapter

        dashboardAdapter.setClickListener(this)
        observeViewModel()
    }

    private fun observeViewModel(){
        dashboardViewModel= ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel.getResult().observe(viewLifecycleOwner, Observer {
            dashboardAdapter.updateList(it)
        }
        )
    }
    override fun onResume() {
        super.onResume()
        dashboardViewModel.loadQueen()
    }

    override fun onClick(vote: VotingItem) {
        Toast.makeText(context,"${vote.name}", Toast.LENGTH_LONG).show()

        var voteId=vote.id
        var voteName=vote.name
        var voteImg= vote.img_url
        var action= DashboardFragmentDirections.actionNavigationDashboardToVoteFragment(voteId,voteName,voteImg)
        findNavController().navigate(action)

    }
}