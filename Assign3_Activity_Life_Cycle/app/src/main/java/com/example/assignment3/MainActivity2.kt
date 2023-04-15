package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
const val TAG2 = "MainActivity2"

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(this, "SA onCreate", Toast.LENGTH_SHORT).show();
        //TODO
        // Use intent to get values forwarded by MainActivity
        // Show name, email and gender in textview
       val name = intent.getStringExtra("name")
       val mail = intent.getStringExtra("mail")
       val gender = intent.getStringExtra("gender")
        Log.d("here data : ","name : $name, your email id is $mail , and your gender is $gender ")
        Toast.makeText(this,"Called",Toast.LENGTH_LONG).show()
        Toast.makeText(this, "name : $name",Toast.LENGTH_LONG).show()
        Toast.makeText(this, " your email id is $mail" ,Toast.LENGTH_LONG).show()
        Toast.makeText(this, " and your gender is $gender ", Toast.LENGTH_LONG).show()

    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG2, "onResume Called")
        Toast.makeText(this, "SA onResume", Toast.LENGTH_SHORT).show();
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG2, "onPause Called")
        Toast.makeText(this, "SA onPause", Toast.LENGTH_SHORT).show();
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG2, "onStop Called")
        Toast.makeText(this, "SA onStop", Toast.LENGTH_SHORT).show();
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG2, "onDestroy Called")
        Toast.makeText(this, "SA onDestroy", Toast.LENGTH_SHORT).show();
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG2, "onRestart Called")
        Toast.makeText(this, "SA onRestart", Toast.LENGTH_SHORT).show();
    }
}