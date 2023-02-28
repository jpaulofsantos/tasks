package com.example.tasks.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.FragmentAllTasksBinding
import com.example.tasks.databinding.FragmentExpiredTasksBinding
import com.example.tasks.viewmodel.TaskListViewModel

class ExpiredTasksFragment : Fragment() {

    private lateinit var viewModel: TaskListViewModel
    private var _binding: FragmentExpiredTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        _binding = FragmentExpiredTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textExpiredTasks
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}