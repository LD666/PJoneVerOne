package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.content.Intent
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
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import com.myfirstapplication.pjoneverone.adapter.SubCatRecyclerAdapter
import com.myfirstapplication.pjoneverone.recycler_data.RecycelrMainData
import kotlinx.android.synthetic.main.fragment_read_data.view.*
import org.json.JSONObject

class ReadDataFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_read_data, container, false)

        var saveCatNameID = context!!.getSharedPreferences("saveCatNameID", Context.MODE_PRIVATE)


        var loginInfo = context!!.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)

        var string = saveCatNameID.getString("saveCatName", null)
        var id = saveCatNameID.getString("saveCatID", null)

        var apiID = loginInfo.getString("appapikey", null)
        var uID = loginInfo.getString("id", null)

        var subButton: ArrayList<RecycelrMainData> = ArrayList()

        if (arguments!!.getString("passTheCatName") != null && arguments!!.getString("passTheCatID") != null){
            string = arguments!!.getString("passTheCatName").toString()
            id = arguments!!.getString("passTheCatID").toString()
            view.text_view_toolbar_title.text = arguments!!.getString("passTheCatName")
            Log.i("In read Frag", string)
            Log.i("In read Frag", id)
        }

        Log.d("InreadFrag", uID)
        Log.d("InreadFrag", apiID)
        Log.d("InreadFrag", id)



        var editor = saveCatNameID.edit()
        editor.putString("saveCatName", string)
        editor.putString("saveCatID", id)
        editor.commit()

        Log.d("MyCat", saveCatNameID.getString("saveCatName", null))
        Log.d("MyCat", saveCatNameID.getString("saveCatID", null))



        view.read_data_recycler.layoutManager = LinearLayoutManager(context)

        var url = ("http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id="+ id +
                "&api_key=" + apiID + "&user_id=" + uID)


        var myReq = Volley.newRequestQueue(this.context)

        var request = StringRequest(Request.Method.GET, url,
            Response.Listener {


                if(JSONObject(it) == null){

                }else{

                    var jsonObject = JSONObject(it)
                    var jsonArray = jsonObject.getJSONArray("subcategory")

                    for (i in 0 until(jsonArray.length())){

                        var contact = jsonArray.getJSONObject(i)

                        var subCatName = contact.getString("scname")
                        var subCatID = contact.getString("scid")

                        var subCatDiscription = contact.getString("scdiscription")
                        var subCatImg = contact.getString("scimageurl")


                        subButton.add(RecycelrMainData(subCatName, subCatID, subCatDiscription, subCatImg))

                    }

                    for (i in 0 until subButton.size){
                        Log.i("inSubList", subButton[i].toString())
                    }

                    view.read_data_recycler.adapter = SubCatRecyclerAdapter(subButton, this.context)
                }

            }, Response.ErrorListener {  })

        if(id != "114"){
            myReq.add(request)
        }

        view.image_button_rd_data.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, MainPageActivity::class.java)
            startActivity(myIntent)

        })

        return view
    }

}