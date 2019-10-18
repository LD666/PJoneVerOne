package com.myfirstapplication.pjoneverone.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.myfirstapplication.pjoneverone.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up_sign_in.image_button_inup
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        var myAddress = getSharedPreferences("saveAddress", Context.MODE_PRIVATE)
        var editor = myAddress.edit()

        button_sign_up.setOnClickListener(View.OnClickListener {

            var signUpFname = edit_text_su_fname.text.toString()
            var signUpLname = edit_text_su_lname.text.toString()
            var signUpAddress = edit_text_su_address.text.toString()
            var signUpEmail = edit_text_su_email.text.toString()
            var signUpMobile = edit_text_su_mobile.text.toString()
            var signUpPass = edit_text_su_pass.text.toString()

            editor.putString("my_address", signUpAddress)
            editor.commit()

            var url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?fname=" + signUpFname +
                    "&lname=" + signUpLname + "&address=" + signUpAddress + "&email=" + signUpEmail + "&mobile=" +
                    signUpMobile + "&password=" + signUpPass

            var myReq = Volley.newRequestQueue(this)

            var request = StringRequest(Request.Method.GET, url, Response.Listener {

                Log.i("NoError", it.toString())

                if(it.toString() == "successfully registered"){
                    Log.i("In if", it.toString())
                    var myInterface = Intent(this,SignInActivity::class.java)
                    startActivity(myInterface)
                    Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
                }else if(it.toString() == "Mobile number already exsist"){
                    Log.i("In Else", it.toString())
                    var myInterface = Intent(this, WelcomeActivity::class.java)
                    startActivity(myInterface)
                    Toast.makeText(this, "Sign Up NOT Success", Toast.LENGTH_SHORT).show()
                }

            }, Response.ErrorListener {
                Log.e("Error", it.message)
            })

            myReq.add(request)

        })

        image_button_sign_up.setOnClickListener(View.OnClickListener {

            var myInterface = Intent(this, WelcomeActivity::class.java)
            startActivity(myInterface)

        })

    }
}
