package com.example.todo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ListSelectionViewHolderBinding
import com.example.todo.models.TaskList

class ListSelectionRecyclerViewAdapter(private val lists:MutableList<TaskList>)
    : RecyclerView.Adapter<ListSelectionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding
            .inflate(LayoutInflater.from(parent.context),
            parent, false)

        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].name//listTitles[position]
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun listsUpdated(){
        notifyItemInserted(lists.size-1)
    }

}