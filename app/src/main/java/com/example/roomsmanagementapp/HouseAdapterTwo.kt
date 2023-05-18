package com.example.roomsmanagementapp


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.FirebaseDatabase
import kotlin.collections.ArrayList


class HouseAdapterTwo(var context: Context, var data:ArrayList<House>):BaseAdapter(){
private class ViewHolder(row: View?){
    var mTxtNumber: TextView
    var mTxtType: TextView
    var mTxtRent: TextView
    var mTxtTenant: TextView
    var mTxtMore: TextView
    var btnBook: Button
    init {
        this.mTxtNumber = row?.findViewById(R.id.mtvNumber) as TextView
        this.mTxtType = row?.findViewById(R.id.mtvType) as TextView
        this.mTxtRent = row?.findViewById(R.id.mtvRent) as TextView
        this.mTxtTenant = row?.findViewById(R.id.mtvTenant) as TextView
        this.mTxtMore = row?.findViewById(R.id.mtvMore) as TextView
        this.btnBook = row?.findViewById(R.id.mbtnBook) as Button
    }
}
override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    var view: View?
    var viewHolder:ViewHolder
    if (convertView == null){
        var layout = LayoutInflater.from(context)
        view = layout.inflate(R.layout.houses_list_layout_two,parent,false)
        viewHolder = ViewHolder(view)
        view.tag = viewHolder
    }else{
        view = convertView
        viewHolder = view.tag as ViewHolder
    }
    var item:House = getItem(position) as House
    viewHolder.mTxtNumber.text = item.number
    viewHolder.mTxtType.text = item.type
    viewHolder.mTxtRent.text = item.rent
    viewHolder.mTxtTenant.text = item.tenant
    viewHolder.mTxtMore.text = item.moreInfor

    viewHolder.btnBook.setOnClickListener {
            val uri: Uri = Uri.parse("sms to:0706901577")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello how are you? I would like to rent one of your houses.")
        context.startActivity(intent)
    }


    return view as View
}

override fun getItem(position: Int): Any {
    return  data.get(position)
}

override fun getItemId(position: Int): Long {
    return position.toLong()
}

override fun getCount(): Int {
    return data.count()
}
}