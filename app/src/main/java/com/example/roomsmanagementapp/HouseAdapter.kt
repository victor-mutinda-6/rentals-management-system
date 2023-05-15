package com.example.roomsmanagementapp


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList


class HouseAdapter(var context: Context, var data:ArrayList<House>):BaseAdapter(){
private class ViewHolder(row: View?){
    var mTxtNumber: TextView
    var mTxtType: TextView
    var mTxtRent: TextView
    var mTxtTenant: TextView
    var mTxtMore: TextView
    var btnDelete: Button
    var btnUpdate: Button
    init {
        this.mTxtNumber = row?.findViewById(R.id.mtvNumber) as TextView
        this.mTxtType = row?.findViewById(R.id.mtvType) as TextView
        this.mTxtRent = row?.findViewById(R.id.mtvRent) as TextView
        this.mTxtTenant = row?.findViewById(R.id.mtvTenant) as TextView
        this.mTxtMore = row?.findViewById(R.id.mtvMore) as TextView
        this.btnDelete = row?.findViewById(R.id.mbtnDelete) as Button
        this.btnUpdate = row?.findViewById(R.id.mbtnEdit) as Button
    }
}
override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    var view: View?
    var viewHolder:ViewHolder
    if (convertView == null){
        var layout = LayoutInflater.from(context)
        view = layout.inflate(R.layout.houses_list_layout,parent,false)
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
    viewHolder.btnDelete.setOnClickListener {
        var ref = FirebaseDatabase.getInstance().getReference().child("Houses/"+item.id)
        ref.removeValue().addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context,"House Deleted successfully",
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Deleting house failed",Toast.LENGTH_LONG).show()
            }
        }
    }
    viewHolder.btnUpdate.setOnClickListener {
        var intent = Intent(context,UpdateHouseActivity::class.java)
        intent.putExtra("number",item.number)
        intent.putExtra("type",item.type)
        intent.putExtra("rent",item.rent)
        intent.putExtra("tenant",item.tenant)
        intent.putExtra("moreInfor",item.moreInfor)
        intent.putExtra("id",item.id)
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