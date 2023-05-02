package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapp.databinding.ActivityStartingBinding

class StartingActivity : AppCompatActivity()
{
    private var tempNote = Note()
    lateinit var binding: ActivityStartingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val dialog = DialogShowNote()
            dialog.sendNoteSelected(tempNote)
            dialog.show(supportFragmentManager, "123")
        }
        binding.floatingActionButton.setOnClickListener {
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "123")
        }
    }

    fun createNewNote(newNote: Note)
    {
        tempNote = newNote
    }
}