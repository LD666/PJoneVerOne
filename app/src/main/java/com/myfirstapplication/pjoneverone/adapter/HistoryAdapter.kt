package com.myfirstapplication.pjoneverone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.recycler_data.HistoryData
import kotlinx.android.synthetic.main.history_text.view.*

class HistoryHolder(view: View): RecyclerView.ViewHolder(view){


    var theOrderid = view.text_view_history_orderid
    var theName = view.text_view_history_name
    var theMobile = view.text_view_history_mobile
    var theEmail = view.text_view_history_email
    var thePlacedon = view.text_view_history_placedon

}

class HistoryAdapter(var item: ArrayList<HistoryData>, var context: Context?): RecyclerView.Adapter<HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        var res = HistoryHolder(LayoutInflater.from(context).inflate(R.layout.history_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.theOrderid.text = item[position].orderid
        holder.theName.text = item[position].name
        holder.theMobile.text = item[position].mobile
        holder.theEmail.text = item[position].email
        holder.thePlacedon.text = item[position].placedon
    }
}