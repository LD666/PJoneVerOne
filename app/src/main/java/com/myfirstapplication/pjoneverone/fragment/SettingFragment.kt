package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import com.myfirstapplication.pjoneverone.activity.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_setting, container, false)


        view.button_sign_out.setOnClickListener(View.OnClickListener {

            var user = FirebaseAuth.getInstance()
            user.signOut()

            view.text_view_setting.visibility = View.GONE
            view.button_sign_out.visibility = View.GONE

            var token = activity!!.getSharedPreferences("isIn", Context.MODE_PRIVATE)
            var editor = token.edit()
            editor.putString("my_token", null)
            editor.commit()

            var myIntent = Intent(context, WelcomeActivity::class.java)
            startActivity(myIntent)

        })

        view.image_button_setting.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)

        })

        view.button_history.setOnClickListener(View.OnClickListener {

            var historyFragment = OrderHistoryFragment()
            fragmentManager!!.beginTransaction().replace(R.id.main_page, historyFragment).commit()

        })

        return view
    }
}