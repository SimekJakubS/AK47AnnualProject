package com.e.amatrskyklub47

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_author_info.*
import android.text.Html
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.Html.fromHtml


class AuthorInfo : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_info)

        //ACTION BAR TLACIDLO SPAT TOTO JE ZAPIS AKO VYMAZAT Z ACTION BARU NADPIS!!
        val actionbar = supportActionBar
        actionbar!!.title = "Informácie"
        /*
            ZAUJIMAVY SPOSOB AKO DAT ACTIVITU FULLSCREEN, BOHUZIAL NERIESI PROBLEM S ACTION SCREEN
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        */

        val str_links = "https://github.com/SimekJakubS/AmaterskyKlub47" //SPOSOB AKO VLOZIT LINK DO APLIKACIE, NATIVNA KNIZNICA LINKIFY
        textView.text = str_links
        Linkify.addLinks(textView, Linkify.ALL)
    }
}

