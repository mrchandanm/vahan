package com.cirql.vahan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class SignupActivity : AppCompatActivity() {
    private lateinit var email_et:TextInputEditText
    private lateinit var password_et:TextInputEditText
    private lateinit var cnf_password_et:TextInputEditText
    private lateinit var submit_btn:AppCompatButton
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
        auth = FirebaseAuth.getInstance()
        val login_page=findViewById<TextView>(R.id.login_page)
        login_page.setOnClickListener{
            startActivity(Intent(this@SignupActivity,Login::class.java))
        }
        val user = auth.currentUser
        if(user!=null){
            startActivity(Intent(this@SignupActivity,MainActivity::class.java))
        }

        submit_btn.setOnClickListener {
            if(password_et.text.toString()!=cnf_password_et.text.toString()){
                cnf_password_et.setError("Password Mismatch")
                return@setOnClickListener
            }
            val email = email_et.text.toString()
            val password = password_et.text.toString()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    startActivity(Intent(this@SignupActivity,MainActivity::class.java))
                    finish()
                    Toast.makeText(this, "signIn succesfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d("sign", "onCreate: "+task.exception.toString())
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
    fun init(){
        email_et=findViewById(R.id.email_et)
        password_et=findViewById(R.id.password_et)
        cnf_password_et=findViewById(R.id.cnf_password_et)
        submit_btn=findViewById(R.id.submit_btn)
    }
}