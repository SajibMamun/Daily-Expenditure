package com.learnwithsajib.dailyexpenditure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.learnwithsajib.dailyexpenditure.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    lateinit var spinner: Spinner
    lateinit var joinas:String

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var myref:DatabaseReference
    lateinit var firebaseUser: FirebaseUser
    lateinit var uid:String
    var gender:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //spinner
        val joinaslist=resources.getStringArray(R.array.JoinAs)
        spinner=binding.spinnerid
        if(spinner!=null)
        {
            val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,joinaslist)

            spinner.adapter=adapter

            spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                 joinas=joinaslist[position]
                    Toast.makeText(applicationContext,"${joinas}",Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        firebaseAuth=FirebaseAuth.getInstance()

        val database=Firebase.database
        myref=database.reference.child("User")

        binding.malebtn.setOnClickListener {
            gender="Male"
            Toast.makeText(applicationContext,"$gender",Toast.LENGTH_SHORT).show()
        }
        binding.femalebtn.setOnClickListener {
            gender="Female"
            Toast.makeText(applicationContext,"$gender",Toast.LENGTH_SHORT).show()
        }





        binding.SignInTv.setOnClickListener {
            var intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }




    }
}