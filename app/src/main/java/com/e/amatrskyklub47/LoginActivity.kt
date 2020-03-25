package com.e.amatrskyklub47

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            val email = email_login.text.toString()
            val password = password_login.text.toString()

            Log.d("Logcat", "Pokus o prihlasenie sa with mail; $email")

            if (email.isEmpty())
            {
                email_login.error = "Zadajte email"
                return@setOnClickListener
            }

            if(password.isEmpty())
            {
                password_login.error = "Zadajte heslo"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Logcat", "Prihlaseny ucet s id: ${it.result?.user?.uid}")
                    Toast.makeText(this, "Úspešne prihlásený!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, CoreActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK )
                    startActivity(intent)
                }


                .addOnFailureListener {
                    Log.d("Logcat", "Failed to create user: ${it.message}")
                    Toast.makeText(this, "Skontrolujte email, heslo a pripojenie k internetu!",
                        Toast.LENGTH_SHORT).show()
                }
        }

        //FUNGUJE!! NO NEED TO CLOSE ALL TABS ALONE !!
        backToRegisterButton.setOnClickListener {
            Log.d("Logcat","back to RegisterActivity button pressed")
            finish()
        }


    }
}