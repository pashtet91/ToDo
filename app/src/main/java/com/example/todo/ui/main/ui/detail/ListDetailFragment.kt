package com.example.todo.ui.main.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.databinding.ListDetailFragmentBinding


class ListDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    lateinit var binding:ListDetailFragmentBinding
    private lateinit var viewModel: ListDetailViewModel

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
        viewModel = ViewModelProvider(requireActivity())
            .get(ListDetailViewModel::class.java)

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