package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.os.Bundle
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
import com.myfirstapplication.pjoneverone.adapter.HistoryAdapter
import com.myfirstapplication.pjoneverone.recycler_data.HistoryData
import kotlinx.android.synthetic.main.fragment_order_history.view.*
import org.json.JSONObject

class OrderHistoryFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var passHist: ArrayList<HistoryData> = ArrayList()

        var view = inflater.inflate(R.layout.fragment_order_history, container, false)

        var loginInfo = activity!!.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)

        var api_key = loginInfo.getString("appapikey", null)
        var user_id = loginInfo.getString("id", null)
        var mobile = loginInfo.getString("mobile", null)

        var url = ("http://rjtmobile.com/aamir/e-commerce/android-app/order_history.php?api_key=" + api_key +
                "&user_id=" + user_id + "&mobile=" + mobile)


        var myReq = Volley.newRequestQueue(this.context)

        view.history_recycler.layoutManager = LinearLayoutManager(context)

        var request = StringRequest(Request.Method.GET, url,
            Response.Listener {

                var jsonObject = JSONObject(it)
                var jsonArray = jsonObject.getJSONArray("Order history")


                for (i in 0 until(jsonArray.length())){

                    var contact = jsonArray.getJSONObject(i)

                    var orderid = contact.getString("orderid")
                    var name = contact.getString("name")
                    var mobile = contact.getString("mobile")
                    var email = contact.getString("email")
                    var placedon = contact.getString("placedon")

                    passHist.add(HistoryData(orderid, name, mobile, email, placedon))


                }


                view.history_recycler.adapter = HistoryAdapter(passHist, this.context)


            }, Response.ErrorListener {  })

        myReq.add(request)


        view.image_button_hist.setOnClickListener(View.OnClickListener {

            var userFragment = UserFragment()
            fragmentManager!!.beginTransaction().replace(R.id.main_page, userFragment).commit()

        })

        return view

    }

}