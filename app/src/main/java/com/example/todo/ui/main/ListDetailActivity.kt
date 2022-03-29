package com.example.todo.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.databinding.ListDetailActivityBinding
import com.example.todo.models.TaskList
import com.example.todo.ui.main.ui.detail.ListDetailFragment
import com.example.todo.ui.main.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {

//    lateinit var list: TaskList
    lateinit var binding: ListDetailActivityBinding
    lateinit var viewModel: MainViewModel//ListDetailViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.list_detail_activity)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addTaskFab.setOnClickListener{
            showCreateTaskDialog()
        }

        viewModel = ViewModelProvider(this,
        MainViewModelFactory(PreferenceManager.
            getDefaultSharedPreferences(this))).get(
            MainViewModel::class.java)
        viewModel.list = intent.
                getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

//        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        title = viewModel.list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed(){
        super.onBackPressed()
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,
            viewModel.list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
    }

    private fun showCreateTaskDialog(){
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task){ dialog, _->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }
}