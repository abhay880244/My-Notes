package com.abhay.mynotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abhay.mynotes.R
import com.abhay.mynotes.db.Note
import kotlinx.android.synthetic.main.fragment_add_note.view.*
import kotlinx.android.synthetic.main.note_layout.view.*

class NotesAdapter(val notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.view.text_view_title.text = notes[position].title
        holder.view.text_view_note.text = notes[position].note

        holder.view.setOnClickListener {

            val action = HomeFragmentDirections.actionAddNote()
            //note is the argument of addNotefragment and setting note as agrument of addNotefragment
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }


    class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}