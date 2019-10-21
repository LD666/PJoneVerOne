package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.adapter.MainRecyclerAdapter
import com.myfirstapplication.pjoneverone.recycler_data.RecycelrMainData
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import org.json.JSONObject

class MainPageFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main_page, container, false)

//        var mySettingTask = view.findViewById<BottomNavigationView>(R.id.bottom_nav)

        val buttons: ArrayList<RecycelrMainData> = ArrayList()

        view.recyclerID.layoutManager = LinearLayoutManager(this.context)

        var loginInfo = activity!!.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)
        var user_id = loginInfo.getString("id", null)
        var api_key = loginInfo.getString("appapikey", null)
        Log.i("showDetal", user_id)
        Log.i("showDetal", api_key)

        var url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=" + api_key + "&user_id=" + user_id

        var myReq = Volley.newRequestQueue(this.context)

        var request = StringRequest(Request.Method.GET, url,
            Response.Listener {

            var jsonObject = JSONObject(it)
            var jasonArray = jsonObject.getJSONArray("category")

            for (i in 0 until (jasonArray.length())){

                var contact = jasonArray.getJSONObject(i)

                var catName = contact.getString("cname")
                var catId = contact.getString("cid")


                var catDiscription = contact.getString("cdiscription")
                var catImg = contact.getString("cimagerl")

                buttons.add(RecycelrMainData(catName, catId, catDiscription, catImg))

            }

            for (i in 0 until buttons.size){
                Log.i("show", buttons[i].toString())
            }

            view.recyclerID.adapter = MainRecyclerAdapter(buttons, this.context)

        }, Response.ErrorListener {  })

        myReq.add(request)

        return view
    }

}