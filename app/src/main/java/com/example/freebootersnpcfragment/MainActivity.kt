package com.example.freebootersnpcfragment

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myPref = getSharedPreferences("myPrefs", 0)
        val editor = myPref.edit()
        editor.apply()
    }

}