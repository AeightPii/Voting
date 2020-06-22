package com.example.voteapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.voteapp.R

import com.example.voteapp.ui.model.VotingItem


import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.list_item.view.*

class VoteAdapter(var voteList: List<VotingItem> = ArrayList()) :
    RecyclerView.Adapter<VoteAdapter.FoodViewHolder>() {

    var mClickListener: ClickListener? = null   //....................global variable
    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }
    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private lateinit var vote: VotingItem//late init ka val assign ma lote buu bot fun mr

        init {
            itemView.setOnClickListener(this)
        }
        fun bindVote(vote: VotingItem) {
            this.vote=vote
            itemView.txtName.text = vote.name
            Picasso.get()
                .load(vote.img_url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.image_Person)

        }
        override fun onClick(v: View?) {

            mClickListener?.onClick(vote)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return voteList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindVote(voteList.get(position))
    }

    fun updateList(result: List<VotingItem>) {
        this.voteList = result
        notifyDataSetChanged()
    }
    interface ClickListener {
        fun onClick(vote: VotingItem)//no implementation no body
    }

}