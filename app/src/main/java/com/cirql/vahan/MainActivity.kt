package com.cirql.vahan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private var flag=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val logout=findViewById<AppCompatButton>(R.id.logout)
        auth=FirebaseAuth.getInstance()
        logout.setOnClickListener{
           auth.signOut()
            startActivity(Intent(this@MainActivity,Login::class.java))
            finish()
        }
        var webView =findViewById<WebView>(R.id.webView)

        var btn=findViewById<AppCompatButton>(R.id.rent)
        btn.setOnClickListener{
            if(flag==0){
                flag=1
                btn.setText("Return Cycle")
            }
            else{
                flag=0
                btn.setText("Rent Cycle")
            }
            webView.loadUrl("http://192.168.4.1/toggle")
        }
        // Load the Google Form link
    }
}