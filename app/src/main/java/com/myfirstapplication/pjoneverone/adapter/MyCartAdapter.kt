package com.myfirstapplication.pjoneverone.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.myinterf.MyInterface
import com.myfirstapplication.pjoneverone.recycler_data.CartCalculateData
import com.myfirstapplication.pjoneverone.recycler_data.FirstItemsListData
import kotlinx.android.synthetic.main.cart_recycler_text.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

class CartHolder(view: View): RecyclerView.ViewHolder(view){

    var img = view.iteam_cart_img
    var name = view.text_view_cart_item_name
    var qla = view.text_view_cart_item_qla
    var prize = view.text_view_cart_item_prize
    var removeB = view.button_cart_remove
    var addB = view.cart_buttons_add

}

class MyCartAdapter(var item: ArrayList<CartCalculateData>, var context: Context?): RecyclerView.Adapter<CartHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        var res = CartHolder(LayoutInflater.from(context).inflate(R.layout.cart_recycler_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.name.text = item[position].itemName
        holder.prize.text = "prize: " + item[position].itemPrize
        holder.qla.text = "quantity: " + item[position].itemQunt.toString()
        Glide.with(this.context!!).load(item[position].itemImg).into(holder.img)


//        var passData = this.context as MyInterface
//        var bundle = Bundle()
//        bundle.putInt("TheTotal", item[position].itemAllPrize)
//        passData.updateCart(bundle)

        var passData = this.context as MyInterface
        var bundle = Bundle()
        bundle.putInt("TheSecondTotal", item[position].itemAllPrize)
        passData.secondUpdateCart(bundle)

        holder.addB.setOnClickListener(View.OnClickListener {

            item[position].itemQunt += 1
            item[position].itemAllPrize += item[position].itemPrize.toInt()
            holder.qla.text = "quantity: " + item[position].itemQunt.toString()
            Log.i("NewTotal", item[position].itemAllPrize.toString())

            var passData = this.context as MyInterface
            var passTotal = this.context as MyInterface
            var bundle = Bundle()
            bundle.putInt("TheTotal", item[position].itemAllPrize)
            bundle.putString("TheID", item[position].itemID)
            bundle.putInt("TheQul", item[position].itemQunt)
            passData.updateCart(bundle)
            passTotal.secondAddRmUpdateCart(bundle)

//            var passTotal = this.context as MyInterface
//            var totalBundle = Bundle()
//            totalBundle.putInt("TheSecondUpdateTotal", item[position].itemAllPrize)
//            var gg = totalBundle.getInt("TheSecondUpdateTotal")
//            totalBundle.putInt("gg", gg)
//            Log.i("TheSecondUpdateTotal", gg.toString())
//            passTotal.secondAddRmUpdateCart(bundle)
        })


        holder.removeB.setOnClickListener(View.OnClickListener {

            if(item[position].itemQunt > 1){
                item[position].itemQunt -= 1
                item[position].itemAllPrize -= item[position].itemPrize.toInt()
                holder.qla.text = "quantity: " + item[position].itemQunt.toString()

                var passData = this.context as MyInterface
                var passTotal = this.context as MyInterface
                var bundle = Bundle()
                bundle.putInt("TheTotal", item[position].itemAllPrize)
                bundle.putString("TheID", item[position].itemID)
                bundle.putInt("TheQul", item[position].itemQunt)
                passData.updateCart(bundle)
                passTotal.secondAddRmUpdateCart(bundle)

//                var passTotal = this.context as MyInterface
//                var totalBundle = Bundle()
//                totalBundle.putInt("TheSecondUpdateTotal", item[position].itemAllPrize)
//                passTotal.secondAddRmUpdateCart(bundle)
            }

        })

    }

}