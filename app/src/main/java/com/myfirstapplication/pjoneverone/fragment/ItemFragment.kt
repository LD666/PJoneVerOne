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
import com.myfirstapplication.pjoneverone.adapter.MyItemAdapter
import com.myfirstapplication.pjoneverone.recycler_data.ItemRecyclerData
import kotlinx.android.synthetic.main.fragment_item.view.*
import org.json.JSONObject

class ItemFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_item, container, false)

        var string = ""
        var subCatId = ""


        if (arguments!!.getString("passTheSubCatName") != null && arguments!!.getString("passTheSubCatID") != null){
            string = arguments!!.getString("passTheSubCatName").toString()
            subCatId = arguments!!.getString("passTheSubCatID").toString()
            view.text_view_item.text = arguments!!.getString("passTheSubCatName")
            Log.i("In read Frag", string)
            Log.i("In read Frag", subCatId)
        }

        var saveCatNameID = context!!.getSharedPreferences("saveCatNameID", Context.MODE_PRIVATE)
        var catId = saveCatNameID.getString("saveCatID", null)

        var loginInfo = context!!.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE)
        var apiID = loginInfo.getString("appapikey", null)
        var uID = loginInfo.getString("id", null)


        Log.d("InItemFrag", catId)
        Log.d("InItemFrag", subCatId)
        Log.d("InItemFrag", apiID)
        Log.d("InItemFrag", uID)


        var url = ("http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=" + catId + "&scid="
        + subCatId + "&api_key=" + apiID + "&user_id=" + uID)

        var myReq = Volley.newRequestQueue(this.context)

        view.item_recycler.layoutManager = LinearLayoutManager(context)

        var myItemList: ArrayList<ItemRecyclerData> = ArrayList()

        var request = StringRequest(Request.Method.GET, url,
            Response.Listener {

                if(JSONObject(it) != null){

                    var jsonObject = JSONObject(it)
                    var jsonArray = jsonObject.getJSONArray("products")

                    if (jsonArray != null){
                        for (i in 0 until (jsonArray.length())){

                            var content = jsonArray.getJSONObject(i)

                            var itemId = content.getString("id")
                            var itemName = content.getString("pname")
                            var itemQuantity = content.getInt("quantity")
                            var itemPrize = content.getString("prize")
                            var itemDiscription = content.getString("discription")
                            var itemImg = content.getString("image")

                            myItemList.add(ItemRecyclerData(itemId, itemName, itemQuantity, itemPrize, itemDiscription, itemImg))

                        }

                        for(i in 0 until myItemList.size){
                            Log.d("readList", myItemList[i].toString())
                        }

                        view.item_recycler.adapter = MyItemAdapter(myItemList, this.context)
                    }
                }


            }, Response.ErrorListener {  })

        if(subCatId != "206"){
            myReq.add(request)
        }




        view.image_button_item_back.setOnClickListener(View.OnClickListener {

            var mainPageFragment = MainPageFragment()
//            var readDataFragment = ReadDataFragment()
            fragmentManager!!.beginTransaction().replace(R.id.main_page, mainPageFragment).commit()

        })

        return view
    }



}