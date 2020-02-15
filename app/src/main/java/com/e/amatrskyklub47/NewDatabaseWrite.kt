package com.e.amatrskyklub47

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewDatabaseWrite : AppCompatActivity() {
    private lateinit var nameWrite: EditText
    lateinit var dateWrite: EditText
    lateinit var databaseUpdateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_write)

        nameWrite = findViewById(R.id.nameWrite)
        dateWrite = findViewById(R.id.dateWrite)
        databaseUpdateButton = findViewById(R.id.databaseUpdateButton)

        databaseUpdateButton.setOnClickListener {
            saveSomething()
        }

    }

    private fun saveSomething() {
        val name = nameWrite.text.toString().trim()
        val date = dateWrite.text.toString().trim()

        if (name.isEmpty()) {
            nameWrite.error = "Zadajte meno"
            return
        }

        val FirebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = FirebaseDatabase.getReference()
        //Log.d("Logcat", "key je pushnuty")
        databaseReference.child("udalosti").push().setValue("udalost")
        

        val udalost = DatesClass(idUdalost, name, date)

        ref.child(idUdalost).setValue(udalost).addOnCompleteListener {
            Toast.makeText(applicationContext, "DONE", Toast.LENGTH_SHORT).show()
        }
    }
}