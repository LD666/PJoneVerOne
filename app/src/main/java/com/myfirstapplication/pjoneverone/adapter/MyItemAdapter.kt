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
import com.myfirstapplication.pjoneverone.recycler_data.ItemRecyclerData
import kotlinx.android.synthetic.main.item_recycler_text.view.*

class ViewHolder (view: View): RecyclerView.ViewHolder(view){

    var itemName = view.text_view_name
    var itemPrize = view.text_view_prize
    var itemDis = view.text_view_dis
    var itemImg = view.iteam_img
    var button = view.button_add_to_cart

}

class MyItemAdapter(var item: ArrayList<ItemRecyclerData>, var context: Context?): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var res = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = item[position].pname
        holder.itemPrize.text = "Prize: " + item[position].prize
        holder.itemDis.text = item[position].discription
        this.context?.let { Glide.with(it).load(item[position].img).into(holder.itemImg) }

        holder.button.setOnClickListener(View.OnClickListener {

            var passData = this.context as MyInterface
            var bundle = Bundle()
            bundle.putString("itemName", item[position].pname)
            bundle.putString("itemPrize", item[position].prize)
            bundle.putString("itemImg", item[position].img)
            bundle.putString("itemID", item[position].id)
            Log.i("InItemAdapter", bundle.getString("itemName"))
            Log.i("InItemAdapter", bundle.getString("itemPrize"))
            Log.i("InItemAdapter", bundle.getString("itemImg"))
            passData.passItem(bundle)
        })

    }
}