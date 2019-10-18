package com.myfirstapplication.pjoneverone.activity

import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
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
import kotlinx.android.synthetic.main.activity_sign_up_sign_in.*
import java.lang.Exception
import java.lang.StringBuilder

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_sign_in)
        supportActionBar?.hide()

        button_sign_in_up.text = "Sign In"
        button_forgot.text = "Forgot"

//        var user = FirebaseAuth.getInstance()
//        user.currentUser

//        if(user != null){
//            var myIntent = Intent(this@SignInActivity, MainPageActivity::class.java)
//            startActivity(myIntent)
//        }

        var token = getSharedPreferences("isIn", Context.MODE_PRIVATE)
        var editor = token.edit()
        var tmp = StringBuilder()

        var loginInfo = getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)
        var userEditor = loginInfo.edit()

        if(token.getString("my_token", null) != null){
            var myInterface = Intent(this, MainPageActivity::class.java)
            startActivity(myInterface)
        }

        button_sign_in_up.setOnClickListener(View.OnClickListener {

            var signInMobile = edit_text_in_mobile.text.toString()
            var signInPass = edit_text_password.text.toString()

            var url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?mobile=" + signInMobile +
                    "&password=" + signInPass

            var myReq = Volley.newRequestQueue(this)
            var myArrayList = arrayListOf<String>()
            var getValue = arrayListOf<String>()

            var request = StringRequest(Request.Method.GET, url, Response.Listener {

              //Log.i("Show", it)
                Log.i("Show", it.toString())

                for(i in 0 until it.toString().length){
                    if(it[i].toString() == "{" || it[i].toString() == "[" || it[i].toString() == "]" || it[i].toString() == "\""){
                        continue
                    }

                    myArrayList.add(it[i].toString())

                    //Log.i("Show", it[i].toString())
                }


                for(i in 0 until myArrayList.size){
                    //Log.i("Show", myArrayList[i])
                    if(myArrayList[i] != ":" || myArrayList[i] != "}" || myArrayList[i] != ","){
                        tmp.append(myArrayList[i])
                    }

                    if(myArrayList[i] == ":" || myArrayList[i] == "}" || myArrayList[i] == ","){
                        tmp.deleteCharAt(tmp.length - 1)
                        var tmpStr = tmp.toString()
                        getValue.add(tmpStr)
                        tmp.clear()
                    }

                }

                for(i in getValue){
//                    Log.i("AWSL", i)
                }


                if (it != "{\"msg\":[2]}" && it != "{\"msg\":[1]}" && it != "{\"msg\":[0]}" && it != "{\"msg\":[\"try in next 5 mins\"]}"
                    && it != "{\"msg\":[\"try in next 5 seconds\"]}"){

                    var getToken = it
                    editor.putString("my_token", getToken)
                    editor.commit()

                    Log.i("AWSL 1", getValue[3])
                    userEditor.putString("id", getValue[3])
                    Log.i("AWSL 2", getValue[5])
                    userEditor.putString("firstname", getValue[5])
                    Log.i("AWSL 3", getValue[7])
                    userEditor.putString("lastname", getValue[7])
                    Log.i("AWSL 4", getValue[9])
                    userEditor.putString("email", getValue[9])
                    Log.i("AWSL 5", getValue[11])
                    userEditor.putString("mobile", getValue[11])
                    Log.i("AWSL 6", getValue[13])
                    userEditor.putString("appapikey", getValue[13])
                    userEditor.commit()


                    var myInterface = Intent(this, MainPageActivity::class.java)
                    startActivity(myInterface)
                    Log.e("Error", it.toString())

                }

            }, Response.ErrorListener {
                Log.e("Error", it.message)
            })

            myReq.add(request)

            myArrayList.clear()
            getValue.clear()
        })

        image_button_inup.setOnClickListener(View.OnClickListener {

            var myInterface = Intent(this, WelcomeActivity::class.java)
            startActivity(myInterface)

        })

        button_forgot.setOnClickListener(View.OnClickListener {

            var myInterface = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(myInterface)

        })

    }
}
