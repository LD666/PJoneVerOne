package com.myfirstapplication.pjoneverone.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import kotlinx.android.synthetic.main.fragment_read_data.view.*

class ReadDataFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_read_data, container, false)

        var string = ""

        var id = ""

        if (arguments!!.getString("passTheCatName") != null && arguments!!.getString("passTheCatID") != null){
            string = arguments!!.getString("passTheCatName").toString()
            id = arguments!!.getString("passTheCatID").toString()
            view.text_view_toolbar_title.text = arguments!!.getString("passTheCatName")
            Log.i("In read Frag", string)
            Log.i("In read Frag", id)
        }
        
        Log.d("In read Frag", string)

        view.image_button_rd_data.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)

        })

        return view
    }

}