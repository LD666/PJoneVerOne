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

class UpdateFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_update, container, false)


        var pass = context!!.getSharedPreferences("PassTheTotal", Context.MODE_PRIVATE)
        var theUpdate = pass.getInt("passThisTotal", 0)

        view.text_view_f_update.text = "Total: " + theUpdate.toString()

        view.button_up_update.setOnClickListener(View.OnClickListener {

            var theNewUpdate = pass.getInt("passThisTotal", 0)

            Log.i("AAAA", "AAAAAA")
            view.text_view_f_update.text = ""
            view.text_view_f_update.text = "Total: " + theNewUpdate.toString()
        })

        return view


    }

}