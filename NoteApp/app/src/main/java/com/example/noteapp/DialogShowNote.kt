package com.example.noteapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.noteapp.databinding.DialogShowNoteBinding

class DialogShowNote: DialogFragment()
{
    private var note: Note? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        //return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogShowNote = DialogShowNoteBinding.inflate(inflater)
        builder.setView(dialogShowNote.root).setMessage("Your Note")
        if (!note!!.important)
            dialogShowNote.textViewImportant.visibility = View.GONE
        if (!note!!.todo)
            dialogShowNote.textViewToDo.visibility = View.GONE
        if (!note!!.idea)
            dialogShowNote.textViewIdea.visibility = View.GONE
        dialogShowNote.textViewTitle.text = note!!.title
        dialogShowNote.textViewNote.text = note!!.description
        dialogShowNote.btnOK.setOnClickListener {
            dismiss()
        }
        return builder.create()
    }
    fun sendNoteSelected(noteSelected: Note)
    {
        note = noteSelected
    }
}