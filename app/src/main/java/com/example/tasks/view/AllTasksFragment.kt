package com.example.tasks.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks.R
import com.example.tasks.databinding.FragmentAllTasksBinding
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.view.adapter.TaskAdapter
import com.example.tasks.viewmodel.TaskFormViewModel
import com.example.tasks.viewmodel.TaskListViewModel


class AllTasksFragment : Fragment() {

    private var _binding: FragmentAllTasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TaskListViewModel
    private val adapter = TaskAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentAllTasksBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        binding.recyclerAllTasks.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllTasks.adapter = adapter

        val listener = object : TaskListener {
            override fun onClick(id: Int) {
            }

            override fun onDelete(id: Int) {
            }

            override fun onComplete(id: Int) {
            }

            override fun onUndo(id: Int) {
            }

        }

        adapter.getListener(listener)

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.listTasks()

    }

    private fun observe() {
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            adapter.updateTasks(it)
        })
    }
}