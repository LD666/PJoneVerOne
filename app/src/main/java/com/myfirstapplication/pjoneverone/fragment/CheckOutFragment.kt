package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity

class CheckOutFragment: Fragment() {

    var success = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_check_out, container, false)

        var item_id: ArrayList<String> = ArrayList()
        var item_name: ArrayList<String> = ArrayList()
        var item_prize: ArrayList<String> = ArrayList()
        var item_qla: ArrayList<Int> = ArrayList()

        try {
            item_qla = arguments!!.getIntegerArrayList("passQla")!!
            for (i in 0 until item_qla!!.size){
                Log.i("InCheckOut", item_qla[i].toString())
            }
        }catch (e: KotlinNullPointerException){
            item_qla = arguments!!.getIntegerArrayList("itemQla")!!

            for (i in 0 until item_qla!!.size){
                Log.i("InCheckOut", item_qla[i].toString())
            }

        }

        item_id = arguments!!.getStringArrayList("itemID")!!
        item_name = arguments!!.getStringArrayList("itemName")!!
        item_prize = arguments!!.getStringArrayList("itemPrize")!!

        var loginInfo = activity!!.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)
        var savedUser = activity!!.getSharedPreferences("saveUser", Context.MODE_PRIVATE)
        var pass = activity!!.getSharedPreferences("PassTheTotal", Context.MODE_PRIVATE)

        var user_id = loginInfo.getString("id", null)
        var user_name = loginInfo.getString("firstname", null)
        var billing_address = savedUser.getString("address", null)
        var deliveraddress = savedUser.getString("address", null)
        var mobile = loginInfo.getString("mobile", null)
        var email = loginInfo.getString("email", null)
        var api_key = loginInfo.getString("appapikey", null)

        for(i in 0 until item_id.size){


            var url = ("http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?&item_id=" + item_id[i] +
                    "&item_names=" + item_name[i] + "&item_quantity=" + item_qla[i] + "&final_price=" + item_prize[i] +
                    "&api_key=" + api_key + "&user_id=" + user_id + "&user_name=" + user_name + "&billingadd=" + billing_address +
                    "&deliveryadd=" + deliveraddress + "&mobile=" + mobile + "&email=" + email)

            var myReq = Volley.newRequestQueue(this.context)

            var request = StringRequest(Request.Method.GET, url,
                Response.Listener {
                    Log.i("whatHappened", it.toString())
                    if(it == "Unsucessful"){
                        success ++
                    }
                }, Response.ErrorListener {  })

            myReq.add(request)

        }


        if(success == 0){
            Toast.makeText(this.context, "Order Success", Toast.LENGTH_SHORT).show()
            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)
        }else{
            Toast.makeText(this.context, "Order Are NOT Success", Toast.LENGTH_SHORT).show()
            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)
        }


        return view
    }

}