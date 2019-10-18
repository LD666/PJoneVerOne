package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_reset_password.view.*


class ResetPasswordFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_reset_password, container, false)

        view.button_reset_pass.setOnClickListener(View.OnClickListener {

            var myPhone = view.edit_text_reset_pass_mobile.text.toString()
            var myNewPass = view.edit_text_reset_pass_one.text.toString()
            var reMyNewPass = view.edit_text_reset_pass_two.text.toString()

            var url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reset_pass.php?&mymobile="+ myPhone +
                    "&password="+ myNewPass +"&newpassword=" + reMyNewPass

            var token = context!!.getSharedPreferences("isIn", Context.MODE_PRIVATE)
            var editor = token.edit()
            editor.putString("my_token", null)
            editor.commit()

            var myReq = Volley.newRequestQueue(this.context)

            var request = StringRequest(Request.Method.GET, url, Response.Listener {
                Log.i("Res", it.toString())
                var myIntent = Intent(this.context, WelcomeActivity::class.java)
                context!!.startActivities(arrayOf(myIntent))
            }, Response.ErrorListener {

            })

            myReq.add(request)

        })


        view.image_button_back_reset.setOnClickListener(View.OnClickListener {

            var userFragment = UserFragment()
            fragmentManager!!.beginTransaction().replace(R.id.main_page, userFragment).commit()

        })

        return view
    }

}