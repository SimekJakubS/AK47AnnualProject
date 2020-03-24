package com.e.amatrskyklub47
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.database_write.*
import kotlinx.android.synthetic.main.event_row_show.*
import java.text.SimpleDateFormat
import java.util.*

class DatabaseWrite : AppCompatActivity() {

    //FORMAT DATUMU => if !funkcne vymazat
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_write)

        //ACTION BAR TLACIDLO SPAT TOTO JE ZAPIS AKO VYMAZAT Z ACTION BARU NADPIS!!
        val actionbar = supportActionBar
        actionbar!!.title = "Nová udalosť - zápasy"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        databaseUpdateButton.setOnClickListener {
            testButtonsIfSelected()
            saveEventToDatabase()

            //UPOZORNI UZIVATELA ZE JE SPLNENA POZIADAVKA
            Toast.makeText(applicationContext,"Nová udalosť vytvorená!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, CoreActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        //CALENDAR PICKER INSTANCE if !FUNKCNE VYMAZ
        val aktualCas = Calendar.getInstance()
        //MAYBE POTOM PRIDAJ, USER FRIENDLY CAST KODU => datePickerButton.text = dateFormat.format(aktualCas.time) **

        datePickerButton.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    aktualCas.set(Calendar.YEAR, year)
                    aktualCas.set(Calendar.MONTH, month)
                    aktualCas.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    //** => NAPISE TU FRIENDLY CAST KODU, PREPISE BUTTON NA ZVOLENU PREMENNU AKTUAL.PICKED TIME
                    datePickerButton.text = dateFormat.format(aktualCas.time)
                },
                aktualCas.get(Calendar.YEAR),
                aktualCas.get(Calendar.MONTH),
                aktualCas.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        //BUTTONS A ICH LOGIKA----------------------------------------------------------------------

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->

                val radio_langange: RadioButton = findViewById(checkedId)
            }
        )
    }

    override fun onSupportNavigateUp(): Boolean { //TLACIDLO NASPAT
        onBackPressed()
        return true
    }


    private fun testButtonsIfSelected() {
        // Get the checked radio button id from radio group
        var id: Int = radio_group.checkedRadioButtonId
        if (id!=-1){ // If any radio button checked from radio group
            // Get the instance of radio button using id
            val radio:RadioButton = findViewById(id)
            if(radio == radioButton1)
            {
                textView4.text = "Futbal - Žaškov"
                textView.text = "1"
            }

            if(radio == radioButton2)
            {
                textView4.text = "Futbal - Dolný Kubín"
                textView.text = "1"
            }

            if(radio == radioButton3)
            {
                textView4.text = "Hokej - Ružomberok"
                textView.text = "2"
            }

            if(radio == radioButton4)
            {
                textView4.text = "Hokej - Dolný Kubín"
                textView.text = "2"
            }
        }
        else{
            // If no radio button checked in this radio group
            Toast.makeText(applicationContext,"Vyberte jednu možnosť",
                Toast.LENGTH_SHORT).show()
        }
    }

    // Get the selected radio button text using radio button on click listener

    //TOTO JE SKUSKA UKLADANIA USERA do DATABAZY 4 = USPESNA!-----------------------------------
    private fun saveEventToDatabase()
    {
        val uid = FirebaseAuth.getInstance()
        val ref = FirebaseDatabase.getInstance().getReference()
        val udalost = Udalost(datePickerButton.text.toString(), textView4.text.toString(), textView.text.toString())

        //ref.setValue(udalost)

        ref.child("Zapasy").push().setValue(udalost)
    }

    class Udalost(val datum: String, val menoUdalosti: String, val cisloFotky: String){
            constructor() : this("","","")
    }

}

