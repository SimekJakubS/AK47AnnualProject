package com.e.amatrskyklub47

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewDatabaseWrite : AppCompatActivity()
{
    private lateinit var nameWrite: EditText
    lateinit var dateWrite: EditText
    lateinit var databaseUpdateButton: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_write)

        nameWrite = findViewById(R.id.nameWrite)
        dateWrite = findViewById(R.id.dateWrite)
        databaseUpdateButton = findViewById(R.id.databaseUpdateButton)
    }

}
