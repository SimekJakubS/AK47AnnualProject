package com.e.amatrskyklub47

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.user_review_activity.*

class UserReview : AppCompatActivity(){

    lateinit var ratingBar : RatingBar //PREMENNE NA PRACU S RATING BAR
    lateinit var reviewPole : EditText
    lateinit var submitReviewButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_review_activity)

        val actionbar = supportActionBar
        actionbar!!.title = "Ohodnotenie aplikácie"//NAZOV STRANY
        actionbar.setDisplayHomeAsUpEnabled(true)//ZOBRAZENIE TLACIDLA NASPAT
        actionbar.setDisplayHomeAsUpEnabled(true)

        //MAKING OF RATING BAR

        reviewPole = findViewById(R.id.reviewPole)
        ratingBar = findViewById(R.id.ratingBar)
        submitReviewButton = findViewById(R.id.submitReviewButton)

        val rate = findViewById<View>(R.id.ratingBar) as RatingBar

        submitReviewButton.setOnClickListener {
            sendReviewToDatabase() //SPUSTI FUNKCIU

            //UPOZORNI UZIVATELA ZE JE SPLNENA POZIADAVKA
            Toast.makeText(applicationContext,"Hodnotenie bolo odoslané!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, CoreActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean { //TLACIDLO NASPAT
        onBackPressed()
        return true
    }

    private fun sendReviewToDatabase()
    {
        val textFromReview = reviewPole.text.toString().trim()
        if(textFromReview.isEmpty()){
            reviewPole.error ="Prosím ohodnoďte nás pár slovami"
            return
        }
        val rate = findViewById<View>(R.id.ratingBar) as RatingBar
        val ref = FirebaseDatabase.getInstance().getReference()
        val review = Review(textFromReview, rate.rating.toString())

        ref.child("Reviews").push().setValue(review)
    }

    class Review(val review: String, val rating: String)
    {
    }
}

//ERRORS