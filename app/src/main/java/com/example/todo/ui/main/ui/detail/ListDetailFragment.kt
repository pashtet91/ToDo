package com.example.todo.ui.main.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.databinding.ListDetailFragmentBinding
import com.example.todo.models.TaskList
import com.example.todo.ui.main.MainViewModel
import com.example.todo.ui.main.MainViewModelFactory


class ListDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    lateinit var binding:ListDetailFragmentBinding
    private lateinit var viewModel: MainViewModel//ListDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(
//            R.layout.list_detail_fragment, container, false
//            )

        binding = ListDetailFragmentBinding.inflate(inflater,
                    container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel = ViewModelProvider(requireActivity(),
            MainViewModelFactory(PreferenceManager
                    .getDefaultSharedPreferences(requireActivity())))
                    .get(MainViewModel::class.java)

        val list: TaskList? =
            arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)
        if(list != null){
            viewModel.list = list
            requireActivity().title = list.name
        }


        val recyclerAdapter =
            ListItemRecyclerViewAdapter(viewModel.list)
        binding.listItemsRv.adapter = recyclerAdapter
        binding.listItemsRv.layoutManager =
            LinearLayoutManager(requireContext())

        viewModel.onTaskAdded = {
            recyclerAdapter.notifyDataSetChanged()
        }
    }

}