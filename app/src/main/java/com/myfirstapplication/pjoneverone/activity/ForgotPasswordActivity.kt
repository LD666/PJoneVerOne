package com.myfirstapplication.pjoneverone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.myfirstapplication.pjoneverone.R
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        button_forgot_pass.text = "Reset"

        button_forgot_pass.setOnClickListener(View.OnClickListener {

            Log.i("Show", "In button")

            var entEmail = edit_text_forgot_email.text.toString()
            var url = "http://rjtmobile.com/aamir/e-commerce/android-app/forgot_pass_email.php?email=" + entEmail

            var getRes =Volley.newRequestQueue(this)

            var request = StringRequest(Request.Method.GET, url, Response.Listener {
                Log.i("Show", it.toString())
                var myInterface = Intent(this, WelcomeActivity::class.java)
                startActivity(myInterface)
            }, Response.ErrorListener {

            })

            getRes.add(request)

        })

        image_button_forgot_e.setOnClickListener(View.OnClickListener {

            var myInterface = Intent(this, SignInActivity::class.java)
            startActivity(myInterface)

        })

    }
}
