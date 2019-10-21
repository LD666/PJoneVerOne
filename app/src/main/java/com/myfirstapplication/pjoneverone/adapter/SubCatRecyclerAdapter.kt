package com.myfirstapplication.pjoneverone.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.myinterf.MyInterface
import com.myfirstapplication.pjoneverone.recycler_data.RecycelrMainData
import kotlinx.android.synthetic.main.sub_recycler_text.view.*

class Holder(view: View): RecyclerView.ViewHolder(view){

    var subText = view.button_sub_items

}

class SubCatRecyclerAdapter(var item: ArrayList<RecycelrMainData>, var context: Context?): RecyclerView.Adapter<Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var res = Holder(LayoutInflater.from(context).inflate(R.layout.sub_recycler_text, parent, false))
        return res
    }

    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.subText.text = item[position].itemName

        holder.subText.setOnClickListener(View.OnClickListener {

            var dataPass = it.context as MyInterface
            var bundle = Bundle()
            bundle.putString("theSubCatName", item[position].itemName)
            bundle.putString("theSubCatID", item[position].itemID)
            Log.i("InAdapter", bundle.getString("theSubCatName"))
            Log.i("InAdapter", bundle.getString("theSubCatID"))
            dataPass.passSubIDandName(bundle)

        })

    }
}