package com.learnwithsajib.dailyexpenditure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent=Intent(applicationContext,SignInActivity::class.java)
        startActivity(intent)
    }
}