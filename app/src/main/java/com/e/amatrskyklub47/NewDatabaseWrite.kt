package com.e.amatrskyklub47

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class NewDatabaseWrite : AppCompatActivity(), View.OnClickListener {


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

        buttonSave.setOnClickListener(this)

    }

    override fun onClick(v: View?)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        saveData()
    }

    private fun saveData()
    {
        val datumVal = datum.text.toString().trim()
        val menoVal = meno.text.toString().trim()

        if(datumVal.isEmpty())
        {
            datum.error = "Prazdny datum"
            return
        }

        if(menoVal.isEmpty())
        {
            meno.error = "Prazdne meno"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("databazaZapis")

        val akciaId = ref.push().key
    }


}
