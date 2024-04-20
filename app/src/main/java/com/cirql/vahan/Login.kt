package com.cirql.vahan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var email_et: TextInputEditText
    private lateinit var password_et: TextInputEditText
    private lateinit var submit_btn: AppCompatButton
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email_et=findViewById(R.id.email_et)
        password_et=findViewById(R.id.password_et)
        submit_btn=findViewById(R.id.submit_btn)
        val register_page=findViewById<TextView>(R.id.register_page)
        register_page.setOnClickListener{
            startActivity(Intent(this@Login,SignupActivity::class.java))
        }
        auth = FirebaseAuth.getInstance()
        submit_btn.setOnClickListener{
            auth.signInWithEmailAndPassword(email_et.text.toString(),password_et.text.toString()).addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    startActivity(Intent(this@Login,MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}