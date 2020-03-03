package com.e.amatrskyklub47

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewDatabaseWrite : AppCompatActivity()
{
    private lateinit var datum : EditText
    private lateinit var meno : EditText
    private lateinit var buttonSave : Button


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_write)

        datum = findViewById(R.id.dateWriteDatabase)
        meno = findViewById(R.id.nameWriteDatabase)
        buttonSave = findViewById(R.id.databaseUpdateButton)

        buttonSave.setOnClickListener { this }
    }
}
