package com.example.notebook.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.notebook.R
import com.example.notebook.model.note
import com.example.notebook.viewmodels.RecyclerNoteViewModel
import java.util.Calendar


/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var mNoteViewModel : RecyclerNoteViewModel
    private val TAG : String = "AddFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        //Initializing the viewModel
        mNoteViewModel = ViewModelProvider(this).get(RecyclerNoteViewModel::class.java)

        view.findViewById<Button>(R.id.add_button).setOnClickListener{
            Log.d(TAG, "onCreateView: Clicked add note button")


            val currentDate = Calendar.getInstance().time

            val content = view.findViewById<TextView>(R.id.add_note).text.toString()
            val title = view.findViewById<TextView>(R.id.add_title).text.toString()
            val note  = note(title=title, content = content, date = currentDate.toString() )

            insertNotetoDatabase(note)
        }
        return view
    }

    private fun insertNotetoDatabase(note: note) {
             mNoteViewModel.addNote(note)
        Toast.makeText(requireContext(),"successfully added note!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }


}