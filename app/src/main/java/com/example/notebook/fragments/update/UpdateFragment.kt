package com.example.notebook.fragments.update

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notebook.R
import com.example.notebook.model.note
import com.example.notebook.viewmodels.RecyclerNoteViewModel
import java.util.Calendar
import java.util.Date


class UpdateFragment : Fragment() {

    private lateinit var mNoteViewModel : RecyclerNoteViewModel
    private lateinit var currentNote : note
    private val TAG : String = "UpdateFragment"


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        //Initializing the viewModel
        mNoteViewModel = ViewModelProvider(this).get(RecyclerNoteViewModel::class.java)

        val note = arguments?.getParcelable("selectedRow",note::class.java)
        currentNote = note!!

        val uid = note!!.uid!!.toInt()


        //Set update screen
        view?.findViewById<EditText>(R.id.update_title)!!.text = Editable.Factory.getInstance().newEditable(note?.title)
        view?.findViewById<EditText>(R.id.update_note)!!.text = Editable.Factory.getInstance().newEditable(note?.content)


        view.findViewById<Button>(R.id.update_button).setOnClickListener{

            val title = view?.findViewById<EditText>(R.id.update_title)!!.text
            val note = view?.findViewById<EditText>(R.id.update_note)!!.text
            val date = Calendar.getInstance().time

            val newNote = note(uid=uid,title=title.toString(), content = note.toString(), date.toString())
            updateNoteOnDatabase(newNote)

        }
        Log.d("updatescreen", "onCreateView: ${note}")

        setHasOptionsMenu(true)
        return view
    }


    private fun updateNoteOnDatabase(newNote: note) {

        Log.d(TAG, "updateNoteOnDatabase: $newNote ")
        mNoteViewModel.updateNote(newNote)
        Toast.makeText(requireContext(),"successfully updated note!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return true
    }

    private fun deleteUser() {
       val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES" ){ _,i ->
            mNoteViewModel.deleteNote(currentNote)
            Toast.makeText(requireContext(),"successfully deleted note !", Toast.LENGTH_SHORT).show()
             findNavController().navigate(R.id.action_updateFragment_to_listFragment)}
        builder.setNegativeButton("NO")  { _, i ->}
        builder.setTitle("DELETE THE NOTE")
        builder.setMessage("Are u sure delete this note?")
        builder.create().show()
    }
}