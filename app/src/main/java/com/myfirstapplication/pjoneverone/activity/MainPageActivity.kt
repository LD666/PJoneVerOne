package com.myfirstapplication.pjoneverone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.fragment.CartFragment
import com.myfirstapplication.pjoneverone.fragment.MainPageFragment
import com.myfirstapplication.pjoneverone.fragment.ReadDataFragment
import com.myfirstapplication.pjoneverone.fragment.SettingFragment
import com.myfirstapplication.pjoneverone.fragment.UserFragment
import com.myfirstapplication.pjoneverone.myinterf.MyInterface

class MainPageActivity : AppCompatActivity(), MyInterface {

    override fun passIDandName(bundle: Bundle) {
        var readDataFragment = ReadDataFragment()
        var passName = bundle.getString("theCatName")
        var passID = bundle.getString("theCatID")
        Log.i("In MP get", bundle.getString("theCatName"))
        Log.i("In MP get", bundle.getString("theCatID"))
        bundle.putString("passTheCatName", passName)
        bundle.putString("passTheCatID", passID)
        Log.i("In MP pass", bundle.getString("passTheCatName"))
        Log.i("In MP pass", bundle.getString("passTheCatID"))
        readDataFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_page, readDataFragment).commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        supportActionBar?.hide()

        var mySettingTask = findViewById<BottomNavigationView>(R.id.bottom_nav)

        mySettingTask.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {

                R.id.iteam_setting -> {

                    var settingFragment = SettingFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_page, settingFragment).commit()
                    true
                }

                R.id.iteam_account -> {
                    var userFragment = UserFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_page, userFragment).commit()
                    true
                }

                R.id.iteam_cart -> {
                    var cartFragment = CartFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_page, cartFragment).commit()
                    true
                }


                else -> false
            }

        }


        var mainPageFrag = MainPageFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page, mainPageFrag).commit()

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
