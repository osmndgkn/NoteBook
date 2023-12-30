package com.example.notebook.fragments.list

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.model.note
import com.example.notebook.viewmodels.RecyclerNoteViewModel


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private lateinit var  context: Context
    private val TAG : String = "ListAdapter"

    private var noteList =  emptyList<note>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        context = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_row,parent,false))
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList.get(position)

        holder.itemView.findViewById<TextView>(/* id = */ R.id.title).text = currentItem.title
        holder.itemView.findViewById<TextView>(/* id = */ R.id.content).text = currentItem.content

        // Click listener for update operations
        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener {

            // Creating bundle for pass the arguments off note
            val bundle  = Bundle()
            bundle.putParcelable("selectedRow",currentItem)

            Log.d(TAG, "onBindViewHolder: $currentItem")
            Navigation.findNavController(holder.itemView).navigate(R.id.action_listFragment_to_updateFragment,bundle)

        }

    }

    fun setData(note :List<note>){
        this.noteList = note
        notifyDataSetChanged()
    }

}