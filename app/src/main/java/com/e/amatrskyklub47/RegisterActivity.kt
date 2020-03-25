package com.e.amatrskyklub47

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {
            doRegister()
        }

        alreadyHaveAccountButton.setOnClickListener {
            Log.d("Logcat","ALREADY have acc. button pressed")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doRegister() // TATO FUN ZAREG UZIVAT, AK NEPOJDE DAJ TO PREC Z FUNKCIE
    {
        val email = email_register.text.toString()
        val password = password_register.text.toString()

        //ABY NECRASHLA APPKA PRI ZADANI NULL V NIEKTOROM Z POLI
        if (email.isEmpty()) {
            //Toast.makeText(this, "Pole nesmie byť prázdne", Toast.LENGTH_LONG).show()  TOTO MOZE BYT TIEZ, ALE TOTO JE MORE USER FRIENDLY
            //return
            email_register.error = "Pole nesmie byť prázdne"
            return
        }

        if (email.length <= 11)
        {
            email_register.error = "Zadajte platný email"
            return
        }

        if(password.isEmpty())
        {
            password_register.error = "Zadajte heslo"
            return
        }

        if (password.length <= 5)
        {
            password_register.error = "Heslo musí byť dlhé aspoň 6 znakov"
            return
        }

        Log.d("Logcat","Email je: "+ email)

        //FIREBASEEE AUTHENTICATION HERRE:  AK NIECO NEPOJDE,ZMEN TOTO NA MOZNOST BEZ FIREBASE
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                //ELSE IF SUCCESSFUL   TOTO MOZE BYT ZLE, DAVAJ POZOR!!
                Log.d("Logcat", "Zalozeny ucet s uid: ${it.result?.user?.uid}")
                Toast.makeText(this, "Účet úspešne vytvorený!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, CoreActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK )
                startActivity(intent)

            }

            .addOnFailureListener {
                Log.d("Logcat", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Skontrolujte pripojenie k internetu",
                    Toast.LENGTH_LONG).show()
            }
    }

}
