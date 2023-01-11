package com.example.labelingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labelingapp.db.Entry

class EntryAdapter: RecyclerView.Adapter<EntryAdapter.ViewHolder>() {
    private var entryList: ArrayList<Entry> = ArrayList()
    private var onClickItem: ((Entry) -> Unit)? =null
    private var onClickDeleteItem: ((Entry)-> Unit)? = null

    fun addItems(items: ArrayList<Entry>) {
        this.entryList = items
        notifyDataSetChanged()
    }
    fun onClickDeleteItem(callback: (Entry) -> Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ent = entryList[position]
        holder.bindView(ent)
        holder.itemView.setOnClickListener { onClickItem?.invoke(ent) }

    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<Button>(R.id.IdTv)
        private var name = view.findViewById<TextView>(R.id.NameTV)
        private var work = view.findViewById<TextView>(R.id.WorkTv)
        private var problem = view.findViewById<TextView>(R.id.ProblemTv)
        private var nItems = view.findViewById<TextView>(R.id.nItems)

        fun bindView(entry: Entry){
            id.text = entry.ID.toString()
            name.text = entry.name
            work.text = entry.item
            nItems.text = entry.nItems
            problem.text = entry.problems

        }
    }
}