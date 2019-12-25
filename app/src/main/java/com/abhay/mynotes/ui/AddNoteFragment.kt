package com.abhay.mynotes.ui


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import com.abhay.mynotes.R
import com.abhay.mynotes.db.Note
import com.abhay.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.note_layout.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : BaseFragment() {

    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)


        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            edit_text_title.setText(note?.title)
            editTextDesc.setText(note?.note)
        }


        button_save.setOnClickListener { view ->
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


            launch {


                context?.let {
                    val mNote = Note(noteTitle, noteBody)
                    if (note == null) {

                        NoteDatabase.invoke(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved")
                    } else {
                        //copy the id of argument's note(note) in mNote and perform update operation
                        mNote.id = note!!.id
                        NoteDatabase.invoke(it).getNoteDao().updateNote(mNote)
                        it.toast("Note Saved")
                    }


                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }

//            saveNote(note)


        }
    }

//    private fun saveNote(note: Note) {
//        class SaveNote : AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg params: Void?): Void? {
//                NoteDatabase.invoke(activity!!).getNoteDao().addNote(note)
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//                Toast.makeText(activity, "Note Saved", Toast.LENGTH_LONG).show()
//            }
//
//        }
//
//        SaveNote().execute()
//    }


}
