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
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.activity.MainPageActivity
import com.myfirstapplication.pjoneverone.fragment.*
import com.myfirstapplication.pjoneverone.myinterf.MyInterface
import com.myfirstapplication.pjoneverone.recycler_data.RecycelrMainData
import kotlinx.android.synthetic.main.main_recycler_text.view.*


class Handler(view: View): RecyclerView.ViewHolder(view){

    var button = view.button_items

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

        holder.button.setOnClickListener(View.OnClickListener {


            if(item[position].itemName == item[0].itemName){
                var dataPass = it.context as MyInterface
                var bundle = Bundle()
                bundle.putString("end_point_0", "the First")
                bundle.putString("p_0_name", item[position].itemName)
                Log.d("In Adapter", bundle.getString("end_point_0"))
                Log.d("In Adapter", bundle.getString("p_0_name"))
                dataPass.ItIsMeat(bundle)
            }

            if(item[position].itemName == item[1].itemName){
                var dataPass = it.context as MyInterface
                var bundle = Bundle()
                bundle.putString("end_point_1", "the Next")
                bundle.putString("p_1_name", item[position].itemName)
                Log.d("In Adapter", bundle.getString("end_point_1"))
                Log.d("In Adapter", bundle.getString("p_1_name"))
                dataPass.ItIsFruit(bundle)
            }

            if(item[position].itemName == item[2].itemName){
                var dataPass = it.context as MyInterface
                var bundle = Bundle()
                bundle.putString("end_point_2", "the Third")
                bundle.putString("p_2_name", item[position].itemName)
                Log.d("In Adapter", bundle.getString("end_point_2"))
                Log.d("In Adapter", bundle.getString("p_2_name"))
                dataPass.itIsVegetable(bundle)
            }

            if(item[position].itemName == item[3].itemName){
                var dataPass = it.context as MyInterface
                var bundle = Bundle()
                bundle.putString("end_point_3", "the Fourth")
                bundle.putString("p_3_name", item[position].itemName)
                Log.d("In Adapter", bundle.getString("end_point_3"))
                Log.d("In Adapter", bundle.getString("p_3_name"))
                dataPass.itIsSeaFood(bundle)
            }


        })

    }

}