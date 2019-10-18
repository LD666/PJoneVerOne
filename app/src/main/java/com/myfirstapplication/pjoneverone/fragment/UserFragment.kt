package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_user, container, false)

        var myAddress = activity!!.getSharedPreferences("saveAddress", Context.MODE_PRIVATE)
        var savedUser = activity!!.getSharedPreferences("saveUser", Context.MODE_PRIVATE)
        var loginInfo = activity!!.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)


        if(savedUser.getString("first_name", null) != null){
            var uf = savedUser.getString("first_name", null)
            view.edit_text_update_firstname.setText(uf)
        }else if(savedUser.getString("first_name", null) == null && loginInfo.getString("firstname", null) != null){
            var uf = loginInfo.getString("firstname", null)
            view.edit_text_update_firstname.setText(uf)
        }

        if(savedUser.getString("last_name", null) != null){
            var ul = savedUser.getString("last_name", null)
            view.edit_text_update_lastname.setText(ul)
        }else if(savedUser.getString("last_name", null) == null && loginInfo.getString("lastname", null) != null){
            var uf = loginInfo.getString("lastname", null)
            view.edit_text_update_lastname.setText(uf)
        }


        if(savedUser.getString("address", null) != null){
            var ad = savedUser.getString("address", null)
            view.edit_text_update_address.setText(ad)
        }else if(savedUser.getString("address", null) == null && myAddress.getString("my_address", null) != null){
            var addressone = myAddress.getString("address_one", null)
            view.edit_text_update_address.setText(addressone)
        }

        if(savedUser.getString("email", null) != null){
            var em = savedUser.getString("email", null)
            view.edit_text_update_email.setText(em)
        }else if(savedUser.getString("address", null) == null && loginInfo.getString("email", null) != null){
            var em = loginInfo.getString("email", null)
            view.edit_text_update_email.setText(em)
        }

        if(savedUser.getString("mobile", null) != null){
            var mb = savedUser.getString("mobile", null)
            view.edit_text_update_mobile.setText(mb)
        }else if(savedUser.getString("mobile", null) == null && loginInfo.getString("mobile", null) != null){
            var mb = loginInfo.getString("mobile", null)
            view.edit_text_update_mobile.setText(mb)
        }


        view.button_save_address.setOnClickListener(View.OnClickListener {

            var updateFirstname = view.edit_text_update_firstname.text.toString()
            var updateLastname = view.edit_text_update_lastname.text.toString()
            var updateAddress = view.edit_text_update_address.text.toString()
            var updateEmail = view.edit_text_update_email.text.toString()
            var updateMobile = view.edit_text_update_mobile.text.toString()

            var edit = savedUser.edit()
            edit.putString("first_name", updateFirstname)
            edit.putString("last_name", updateLastname)
            edit.putString("address", updateAddress)
            edit.putString("email", updateEmail)
            edit.putString("mobile", updateMobile)
            edit.commit()

            Toast.makeText(context, "Address changed", Toast.LENGTH_SHORT).show()

        })

        view.image_button_setting.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)

        })

        view.button_reset_password.setOnClickListener(View.OnClickListener {

            var resetPasswordFragment = ResetPasswordFragment()
            fragmentManager!!.beginTransaction().replace(R.id.main_page, resetPasswordFragment).commit()

        })

        return view
    }

}