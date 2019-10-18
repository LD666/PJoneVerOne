package com.myfirstapplication.pjoneverone.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myfirstapplication.pjoneverone.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide()

        var ifSingUp = getSharedPreferences("IfSignIn", Context.MODE_PRIVATE)

        button_sign_up.setOnClickListener(View.OnClickListener {


            var myIntent = Intent(this, SignUpActivity::class.java)
            startActivity(myIntent)

        })

        button_sign_in.setOnClickListener(View.OnClickListener {


            var myIntent = Intent(this, SignInActivity::class.java)
            startActivity(myIntent)

        })


    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
