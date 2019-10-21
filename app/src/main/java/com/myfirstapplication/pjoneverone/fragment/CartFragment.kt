package com.myfirstapplication.pjoneverone.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import com.myfirstapplication.pjoneverone.adapter.MyCartAdapter
import com.myfirstapplication.pjoneverone.recycler_data.CartCalculateData
import com.myfirstapplication.pjoneverone.recycler_data.FirstItemsListData
import com.myfirstapplication.pjoneverone.recycler_data.PushData
import kotlinx.android.synthetic.main.cart_recycler_text.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import java.lang.Exception

class CartFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_cart, container, false)

        var iteamList: ArrayList<CartCalculateData> = ArrayList()


        var name = arguments!!.getStringArrayList("nameArray")
        var id = arguments!!.getStringArrayList("IDArray")
        var prize = arguments!!.getStringArrayList("prizeArray")
        var img = arguments!!.getStringArrayList("ImgArray")
        var qla = arguments!!.getIntegerArrayList("qlaArray")
        var allPrize = arguments!!.getInt("All")


        for (i in 0 until qla!!.size){
            iteamList.add(CartCalculateData(
                name!![i], id!![i], prize!![i].toInt().toString(), img!![i], qla[i], allPrize))
        }

        var theTotal = arguments!!.getInt("passTheUpdateCartTotal")
        Log.i("InCartTheTotal", theTotal.toString())

        //view.text_view_total_prize.text = "Total: " + allPrize.toString()

        view.cart_recycler.layoutManager = LinearLayoutManager(context)
        view.cart_recycler.adapter = MyCartAdapter(iteamList, this.context)

        view.image_button_cart.setOnClickListener(View.OnClickListener {

            showSettingDialoge()
        })

//        view.button_cart_update.setOnClickListener(View.OnClickListener {
//
//            view.text_view_total_prize.text = ""
//
//            var updateFragment = UpdateFragment()
//            fragmentManager!!.beginTransaction().add(R.id.main_page, updateFragment).commit()
//            view.button_cart_update.visibility = View.GONE
//
//            gone = 1
//
//        })

        view.button_cart_checkout.setOnClickListener(View.OnClickListener {

            var checkOutFragment = CheckOutFragment()

            var bundle = Bundle()
            bundle.putStringArrayList("itemID", id)
            bundle.putStringArrayList("itemName", name)
            bundle.putStringArrayList("itemPrize", prize)
            bundle.putIntegerArrayList("itemQla", qla)



            checkOutFragment.arguments = bundle
            fragmentManager!!.beginTransaction().replace(R.id.main_page, checkOutFragment).commit()

        })

        return view
    }

    private fun showSettingDialoge(){
        var builder = AlertDialog.Builder(this.context!!)
        builder.setTitle("Lost All Item in Cart")
        builder.setMessage("You will lost ALL items in Cart")
        builder.setPositiveButton("EXIST", object: DialogInterface.OnClickListener{
            override fun onClick(dialoge: DialogInterface?, p1: Int) {
                var myIntent = Intent(context, MainPageActivity::class.java)
                startActivity(myIntent)
            }

        })
        builder.setNegativeButton("Canecl", object: DialogInterface.OnClickListener{
            override fun onClick(dialoge: DialogInterface?, p1: Int) {
                dialoge!!.dismiss()
            }

        })

        builder.show()
    }

}