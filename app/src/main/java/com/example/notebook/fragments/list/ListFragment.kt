package com.example.notebook.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.viewmodels.RecyclerNoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class listFragment : Fragment() {

    private lateinit var mNoteViewModel: RecyclerNoteViewModel
    private val TAG : String = "ListFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

          //Initializing  Recycler and List adapter
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //NotViewModel
        mNoteViewModel = ViewModelProvider(this).get(RecyclerNoteViewModel::class.java)
        //Observing the livedata for data changes
        mNoteViewModel.notes.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}