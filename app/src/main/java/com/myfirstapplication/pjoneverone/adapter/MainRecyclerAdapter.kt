package com.myfirstapplication.pjoneverone.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import com.myfirstapplication.pjoneverone.fragment.*
import com.myfirstapplication.pjoneverone.myinterf.MyInterface
import com.myfirstapplication.pjoneverone.recycler_data.RecycelrMainData
import kotlinx.android.synthetic.main.main_recycler_text.view.*


class Handler(view: View): RecyclerView.ViewHolder(view){

    var button = view.button_items
    var img = view.cat_img
    var dis = view.text_view_cat

}

class MainRecyclerAdapter(var item: ArrayList<RecycelrMainData>, val context: Context?): RecyclerView.Adapter<Handler>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Handler {
        var res  = Handler(LayoutInflater.from(context).inflate(R.layout.main_recycler_text, parent, false))
        return res
    }

    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: Handler, position: Int) {
        holder.button.text = item[position].itemName
        holder.dis.text = item[position].catDiscription
        Glide.with(this.context!!).load(item[position].catImg).into(holder.img)

        holder.button.setOnClickListener(View.OnClickListener {

            var dataPass = it.context as MyInterface
            var bundle = Bundle()
            bundle.putString("theCatName", item[position].itemName)
            bundle.putString("theCatID", item[position].itemID)
            Log.i("InAdapter", bundle.getString("theCatName"))
            Log.i("InAdapter", bundle.getString("theCatID"))
            dataPass.passIDandName(bundle)


        })

    }

}