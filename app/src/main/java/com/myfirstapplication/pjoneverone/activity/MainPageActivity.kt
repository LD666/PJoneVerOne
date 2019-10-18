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
    override fun ItIsMeat(bundle: Bundle) {
        var readDataFrag = ReadDataFragment()
        var pass = bundle.getString("end_point_0")
        var name = bundle.getString("p_0_name")
        Log.d("In MP get", bundle.getString("end_point_0"))
        Log.d("In MP get", bundle.getString("p_0_name"))
        bundle.putString("pass_end_point_0", pass)
        bundle.putString("pass_p_0_name", name)
        Log.d("In MP pass", bundle.getString("pass_end_point_0"))
        Log.d("In MP pass", bundle.getString("pass_p_0_name"))
        readDataFrag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_page, readDataFrag).commit()
    }

    override fun ItIsFruit(bundle: Bundle) {
        var readDataFrag = ReadDataFragment()
        var pass = bundle.getString("end_point_1")
        var name = bundle.getString("p_1_name")
        Log.d("In MP get", bundle.getString("end_point_1"))
        Log.d("In MP get", bundle.getString("p_1_name"))
        bundle.putString("pass_end_point_1", pass)
        bundle.putString("pass_p_1_name", name)
        Log.d("In MP pass", bundle.getString("pass_end_point_1"))
        Log.d("In MP pass", bundle.getString("pass_p_1_name"))
        readDataFrag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_page, readDataFrag).commit()
    }

    override fun itIsVegetable(bundle: Bundle) {
        var readDataFrag = ReadDataFragment()
        var pass = bundle.getString("end_point_2")
        var name = bundle.getString("p_2_name")
        Log.d("In MP get", bundle.getString("end_point_2"))
        Log.d("In MP get", bundle.getString("p_2_name"))
        bundle.putString("pass_end_point_2", pass)
        bundle.putString("pass_p_2_name", name)
        Log.d("In MP pass", bundle.getString("pass_end_point_2"))
        Log.d("In MP pass", bundle.getString("pass_p_2_name"))
        readDataFrag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_page, readDataFrag).commit()
    }

    override fun itIsSeaFood(bundle: Bundle) {
        var readDataFrag = ReadDataFragment()
        var name = bundle.getString("p_3_name")
        var pass = bundle.getString("end_point_3")
        Log.d("In MP get", bundle.getString("end_point_3"))
        bundle.putString("pass_end_point_3", pass)
        bundle.putString("pass_p_3_name", name)
        Log.d("In MP pass", bundle.getString("pass_end_point_3"))
        Log.d("In MP pass", bundle.getString("pass_p_3_name"))
        readDataFrag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_page, readDataFrag).commit()
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

//        var mySettingTask = findViewById<BottomNavigationView>(R.id.bottom_nav)
//
//        mySettingTask.setOnNavigationItemReselectedListener {item ->
//
//            when (item.itemId) {
//
//                R.id.iteam_setting -> {
//
//                    Log.e("This is SETTING", "setting pressed")
//                    var settingFragment = SettingFragment()
//                    supportFragmentManager.beginTransaction().replace(R.id.main_page, settingFragment).commit()
//                }
//
//            }
//
//        }

        var mainPageFrag = MainPageFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page, mainPageFrag).commit()

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
