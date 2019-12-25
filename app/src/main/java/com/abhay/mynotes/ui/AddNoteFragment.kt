package com.abhay.mynotes.ui


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.abhay.mynotes.R
import com.abhay.mynotes.db.Note
import com.abhay.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_save.setOnClickListener {
            val noteTitle = edit_text_title.text.toString().trim()
            val noteBody = editTextDesc.text.toString().trim()

            if (noteTitle.isEmpty()) {
                edit_text_title.requestFocus()
                edit_text_title.error = "Note Title Required"
                return@setOnClickListener
            }

            if (noteBody.isEmpty()) {
                editTextDesc.requestFocus()
                editTextDesc.error = "Note Body Required"
                return@setOnClickListener
            }

            val note = Note(noteTitle, noteBody)

            saveNote(note)

        }
    }

    private fun saveNote(note: Note) {
        class SaveNote : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                NoteDatabase.invoke(activity!!).getNoteDao().addNote(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity, "Note Saved", Toast.LENGTH_LONG).show()
            }

        }

        SaveNote().execute()
    }


}
