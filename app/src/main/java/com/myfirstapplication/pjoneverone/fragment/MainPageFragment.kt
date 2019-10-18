package com.myfirstapplication.pjoneverone.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.adapter.MainRecyclerAdapter
import com.myfirstapplication.pjoneverone.myinterf.MyInterface
import com.myfirstapplication.pjoneverone.recycler_data.RecycelrMainData
import kotlinx.android.synthetic.main.fragment_main_page.view.*

class MainPageFragment: Fragment() {

    val buttons: ArrayList<RecycelrMainData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main_page, container, false)

//        var mySettingTask = view.findViewById<BottomNavigationView>(R.id.bottom_nav)

        addButtons()
        view.recyclerID.layoutManager = LinearLayoutManager(this.context)
        view.recyclerID.adapter = MainRecyclerAdapter(buttons, this.context)

//        mySettingTask.setOnNavigationItemSelectedListener {item ->
//
//            when (item.itemId) {
//
//                R.id.iteam_setting -> {
//
//                    Log.e("This is SETTING", "setting pressed")
//                    var settingFragment = SettingFragment()
//                    fragmentManager!!.beginTransaction().replace(R.id.main_page, settingFragment).commit()
//                    true
//                }
//
//                R.id.iteam_account -> {
//                    var userFragment = UserFragment()
//                    fragmentManager!!.beginTransaction().replace(R.id.main_page, userFragment).commit()
//                    true
//                }
//
//
//                else -> false
//            }
//
//        }

        return view
    }

    fun addButtons(){

        buttons.add(RecycelrMainData("Meat"))
        buttons.add(RecycelrMainData("Fruits"))
        buttons.add(RecycelrMainData("vegetable"))
        buttons.add(RecycelrMainData("Sea Food"))


    }

}