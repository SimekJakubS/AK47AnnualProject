package com.e.amatrskyklub47

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_core.*
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class CoreActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)

        verifyIfUserIsLoggedIn()
    }

    private fun verifyIfUserIsLoggedIn()
    {
        var uid = FirebaseAuth.getInstance().uid
        if (uid == null)
        {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK )
            startActivity(intent)
        }
        else
        {
            Log.d("Logcat", "UZIVATEL JE PRIHLASENY")
            textWiew.text = "Tu najdete prehlad zapasov pre tento mesiac."
            //TOTO JE PRIKLAD PERSONALIZOVANEHO OKNA: textWiew.text = "Hello, ${loginName.text}!!"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId) {

            R.id.core_new_date ->
            {
                //Toast.makeText(this, "Nový zápas vytvorený", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NewDatabaseWrite::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK )
                startActivity(intent)
            }

            R.id.core_info ->
            {
                val intent = Intent(this, AuthorInfo::class.java)
                startActivity(intent)
            }

            R.id.core_sign_out ->
            {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //TOTO JE MENU NA BOKU
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.core_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}