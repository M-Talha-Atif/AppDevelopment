package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "FA onCreate", Toast.LENGTH_SHORT).show();

        //TODO
  // Find views by ID and assign their reference to variables.
        // Only 4 lines required.

        val name = findViewById<EditText>(R.id.name)
        val mail = findViewById<EditText>(R.id.mail)
        val b1 = findViewById<RadioButton>(R.id.male)

        val button = findViewById<Button>(R.id.submit)
      


        //TODO
        // Implement setOnClickListener
        // Create Intent object and use this object to explicitly call the MainActivity2
        // Pass values of  name field, email field and radio button field to MainActivity2
        button.setOnClickListener {
        val intent = Intent(this,MainActivity2::class.java)
            val name = name.text.toString()
            val mail = mail.text.toString()
            var gender = ""
            if(b1.isChecked)
                gender="Male"
            else
                gender="Female";
            intent.putExtra("name",name)
           intent.putExtra("mail",mail)
            intent.putExtra("gender", gender)
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
        Toast.makeText(this, "FA onResume", Toast.LENGTH_SHORT).show();
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
        Toast.makeText(this, "FA onPause", Toast.LENGTH_SHORT).show();

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
        Toast.makeText(this, "FA onStop", Toast.LENGTH_SHORT).show();
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
        Toast.makeText(this, "FA onDestroy", Toast.LENGTH_SHORT).show();
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

}
