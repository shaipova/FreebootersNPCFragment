package com.example.freebootersnpcfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NPCListAdapter(private val list: ArrayList<String>, private val context: Context) : RecyclerView.Adapter<NPCListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: NPCListAdapter.ViewHolder, position: Int) {
        holder.bindViews(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NPCListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.npc_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val infoNPC = itemView.findViewById<TextView>(R.id.infoCard)

        fun bindViews(info : String) {
            infoNPC.text = info
            }
        }
}

