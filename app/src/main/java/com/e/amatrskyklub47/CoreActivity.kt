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
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.database_write.*
import kotlinx.android.synthetic.main.event_row_show.*
import kotlinx.android.synthetic.main.event_row_show.view.*
import com.e.amatrskyklub47.DatabaseWrite as DatabaseWrite1


class CoreActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)

        //ACTION BAR TLACIDLO SPAT TOTO JE ZAPIS AKO VYMAZAT Z ACTION BARU NADPIS!!
        val actionbar = supportActionBar
        actionbar!!.title = ""
        //FUNGUJE TIEZ:  supportActionBar?.title = ""

        verifyIfUserIsLoggedIn() //KONTROLUJE CI JE POUZIVATEL PRIHLASENY

        //CITANIE Z DATABAZY,ZOBRAZOVANIE AKO RECYCLER VIEW V OKNE----------------------------------

        //val adapter = GroupAdapter<ViewHolder>()
        //adapter.add(UdalostItem())
        //recyclerWiewWindow.adapter = adapter

        recyclerWiewWindow.layoutManager = LinearLayoutManager(this)
        fetchEvents()

        //REFRESH BUTTON IMAGE => FUNKCNE!
        refreshButton.setOnClickListener {
            val intent = Intent(this, CoreActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun fetchEvents(){
        val ref = FirebaseDatabase.getInstance().getReference("/Zapasy")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    //it.toString()
                    Log.d("Logcat",it.toString())
                    val udalost = it.getValue(DatabaseWrite1.Udalost::class.java)
                    if (udalost != null){
                        adapter.add(UdalostItem(udalost))

                    }

                }
                recyclerWiewWindow.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun verifyIfUserIsLoggedIn() //VERIFIKUJE UZIVATELA CI JE PRHLASENY
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
            var pomocne : Int = 1
            while (pomocne == 1)//ZABEZPECUJE ZE SA UKAZE LOG LEN JEDINY RAZ
            {
                Log.d("Logcat", "UZIVATEL JE PRIHLASENY")
                pomocne = 0
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId) {

            R.id.core_new_date ->
            {
                //Toast.makeText(this, "Nový zápas vytvorený", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DatabaseWrite1::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK )NEZNICI CORE ACTIVITY, DA SA PREPNUT NASPAT
                startActivity(intent)
            }

            R.id.core_info ->
            {
                val intent = Intent(this, AuthorInfo::class.java)
                startActivity(intent)
            }

            R.id.core_rate_app ->
            {
                val intent = Intent(this, UserReview::class.java)
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

//KLASA NA NAHRANIE KLASY UDALOSTI DO KNIZNIC GRROUPIE, CO JE ADAPTER NA PRACU S RECYCLER VIEW
class UdalostItem(val udalost: com.e.amatrskyklub47.DatabaseWrite.Udalost): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.nameOfEventTextView.text = udalost.menoUdalosti
        viewHolder.itemView.dateShow.text = udalost.datum
    }

    override fun getLayout(): Int {
        return R.layout.event_row_show
    }
}