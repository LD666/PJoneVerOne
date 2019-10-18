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

//        var string = ""
//        if(arguments!!.getString("pass_end_point_0") != null && arguments!!.getString("pass_p_0_name") != null){
//            string = arguments!!.getString("pass_end_point_0")!!
//            view.text_view_toolbar_title.text = arguments!!.getString("pass_p_0_name")
//        }else if(arguments!!.getString("pass_end_point_1") != null && arguments!!.getString("pass_p_1_name") != null){
//            string = arguments!!.getString("pass_end_point_1")!!
//            view.text_view_toolbar_title.text = arguments!!.getString("pass_p_1_name")
//        }else if(arguments!!.getString("pass_end_point_2") != null && arguments!!.getString("pass_p_2_name") != null){
//            string = arguments!!.getString("pass_end_point_2")!!
//            view.text_view_toolbar_title.text = arguments!!.getString("pass_p_2_name")
//        }else if(arguments!!.getString("pass_end_point_3") != null && arguments!!.getString("pass_p_3_name") != null){
//            string = arguments!!.getString("pass_end_point_3")!!
//            view.text_view_toolbar_title.text = arguments!!.getString("pass_p_3_name")
//        }
//
//        Log.d("In read Frag", string)






        view.image_button_rd_data.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)

        })

        return view
    }

}