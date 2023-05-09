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

        binding.malebtn.setOnClickListener {
            gender="Male"
            Toast.makeText(applicationContext,"$gender",Toast.LENGTH_SHORT).show()
        }
        binding.femalebtn.setOnClickListener {
            gender="Female"

        }


        firebaseAuth=FirebaseAuth.getInstance()

        val database=Firebase.database
        myref=database.reference.child("User")






        binding.SignInTv.setOnClickListener {
            var intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }


        binding.Registerbtnid.setOnClickListener {
            val name:String=binding.usernameEt.text.toString()
            val email:String=binding.useremailEt.text.toString()
            val phone:String=binding.usernumberEt.text.toString()
            val password:String=binding.PasswordEt.text.toString()
            val confirmpasss:String=binding.ConfirmPasswordEt.text.toString()


            if(name.isEmpty())
            {
                binding.usernameEt.error = "enter name"
            }
            else if(email.isEmpty())
            {
                binding.useremailEt.error="enter email"
            }
            else if(phone.isEmpty())
            {
                binding.usernumberEt.error="enter number"

            }
            else if(password.length<8 || password.isEmpty())
            {
                binding.PasswordEt.error="enter valid password"
            }
            else if(confirmpasss.length<8 )
            {
                binding.ConfirmPasswordEt.error="enter valid password"
            }
            else if(password != confirmpasss)
            {
                binding.ConfirmPasswordEt.error="password not match"
            }

            else if(joinas.equals(null)|| joinas=="")
            {
                Toast.makeText(applicationContext,"Select User Type",Toast.LENGTH_SHORT).show()
            }
            else if(gender.equals(null)|| joinas=="")
            {
                Toast.makeText(applicationContext,"choose gender",Toast.LENGTH_SHORT).show()
            }
            else
            {
                RegisterAccount(name,phone,email,password,confirmpasss,gender,joinas)
            }

        }




    }

    private fun RegisterAccount(name: String, phone: String, email: String, password: String, confirmpasss: String, gender: String?, joinas: String) {
firebaseAuth.createUserWithEmailAndPassword(email,password)
    .addOnCompleteListener{
        if(it.isSuccessful)
        {
            firebaseUser=FirebaseAuth.getInstance().currentUser!!
            uid=firebaseUser.uid

            val map= mapOf("name" to name, "email" to email, "phone" to phone, "gender" to gender,"joinas" to joinas, "password" to password)

            if(uid!=null)

            {
                myref.child(uid).setValue(map).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"Register Successfully",Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"${it.exception}",Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }
    }
}


