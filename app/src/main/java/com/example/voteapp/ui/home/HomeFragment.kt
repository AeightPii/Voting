package com.example.voteapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voteapp.R
import com.example.voteapp.ui.adapter.VoteAdapter
import com.example.voteapp.ui.viewmodel.VoteViewModel

class HomeFragment : Fragment(),VoteAdapter.ClickListener {

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
        viewManager = GridLayoutManager(context,2)
        foodAdapter= VoteAdapter()
        recBeef.apply {
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
}