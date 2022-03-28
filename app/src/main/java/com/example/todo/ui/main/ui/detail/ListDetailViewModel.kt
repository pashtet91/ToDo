package com.example.todo.ui.main.ui.detail

import androidx.lifecycle.ViewModel
import com.example.todo.models.TaskList

class ListDetailViewModel : ViewModel() {
    lateinit var onTaskAdded: (() -> Unit)
    lateinit var list: TaskList

    fun addTask(task: String){
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}