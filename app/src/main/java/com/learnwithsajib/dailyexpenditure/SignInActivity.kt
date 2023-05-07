package com.learnwithsajib.dailyexpenditure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learnwithsajib.dailyexpenditure.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity()
{
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.RegisterTV.setOnClickListener {
            var intent= Intent(applicationContext,RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}