package com.example.voteapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.voteapp.R
import com.example.voteapp.ui.dashboard.DashboardFragment
import com.example.voteapp.ui.model.VotingItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class DashboardAdapter   (var voteList: List<VotingItem> = ArrayList()) :
    RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    var mClickListener: DashClickListener? = null   //....................global variable
    fun setDashClickListener(clickListener: DashClickListener) {
        this.mClickListener = clickListener
    }
    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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

    fun updateList(result: List<VotingItem>) {
        this.voteList = result
        notifyDataSetChanged()
    }
    interface DashClickListener {
        fun onClick(vote: VotingItem)//no implementation no body
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DashboardViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return voteList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bindVote(voteList.get(position))
    }

}