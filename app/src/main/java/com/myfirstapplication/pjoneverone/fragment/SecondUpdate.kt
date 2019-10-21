package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myfirstapplication.pjoneverone.R
import kotlinx.android.synthetic.main.fragment_update.view.*

class SecondUpdate:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_second_update, container, false)




//        try{
//            total = arguments!!.getInt("putTheSecondTotal")
//        }catch (e: KotlinNullPointerException){
//            total = arguments!!.getInt("putTheSecondUpdateTotal")
//            Log.i("whatIsTotal", total.toString())
//        }

         var total = arguments!!.getInt("putTheSecondTotal")
        view.text_view_f_update.text = "Total: " + total.toString()

        try {
            var totalO = arguments!!.getInt("putTheSecondUpdateTotal")
            if(totalO != 0){
                view!!.text_view_f_update.text = ""
                view.text_view_f_update.text = "Total: " + totalO.toString()
            }
        }catch(e: KotlinNullPointerException){

        }


        return view
    }

}