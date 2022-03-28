package com.example.todo.ui.main.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ListItemViewHolderBinding
import com.example.todo.models.TaskList

class ListItemRecyclerViewAdapter(var list: TaskList) :
RecyclerView.Adapter<ListItemViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding =
            ListItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context)
                ,parent,false
            )
            return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.binding.taskTv.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }
}