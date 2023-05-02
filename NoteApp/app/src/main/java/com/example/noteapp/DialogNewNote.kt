package com.example.noteapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.noteapp.databinding.DialogNewNoteBinding

class DialogNewNote: DialogFragment()
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {

        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogNewNote = DialogNewNoteBinding.inflate(inflater)
        builder.setView(dialogNewNote.root).setMessage("Add a New Note")
        //val dialogView = inflater.inflate(R.layout.dialog_new_note, null)
        dialogNewNote.btnCancel.setOnClickListener {
            dismiss()
        }
        dialogNewNote.btnOk.setOnClickListener {
            val newNote = Note()
            newNote.title = dialogNewNote.editTextTitle.text.toString()
            newNote.description = dialogNewNote.editTextDescription.text.toString()
            newNote.important = dialogNewNote.checkBoxImportant.isChecked
            newNote.idea = dialogNewNote.checkBoxIdea.isChecked
            newNote.todo = dialogNewNote.checkBoxTodo.isChecked
            val callingActivity = activity as StartingActivity?
            callingActivity!!.createNewNote(newNote)
            dismiss()
        }
        //return super.onCreateDialog(savedInstanceState)
        return builder.create()
    }
}