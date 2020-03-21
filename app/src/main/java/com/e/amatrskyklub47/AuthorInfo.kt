package com.e.amatrskyklub47

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class AuthorInfo : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        /*
            ZAUJIMAVY SPOSOB AKO DAT ACTIVITU FULLSCREEN, BOHUZIAL NERIESI PROBLEM S ACTION SCREEN
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        */

        setContentView(R.layout.activity_author_info)
    }
}

