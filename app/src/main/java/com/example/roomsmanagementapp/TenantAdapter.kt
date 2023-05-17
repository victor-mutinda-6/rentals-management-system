package com.example.roomsmanagementapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class TenantAdapter (var context: Context, var data:ArrayList<Tenant>): BaseAdapter(){
    private class ViewHolder(row: View?){
        var mTxtName: TextView
        var mTxtEmail: TextView
        var mTxtPhoneNo: TextView
        var mTxtAddress: TextView
        var mTxtIdNo: TextView
        var btnDelete: Button
        var btnUpdate: Button
        init {
            this.mTxtName = row?.findViewById(R.id.mtvName) as TextView
            this.mTxtEmail = row?.findViewById(R.id.mtvEmail) as TextView
            this.mTxtPhoneNo = row?.findViewById(R.id.mtvPhoneNo) as TextView
            this.mTxtAddress = row?.findViewById(R.id.mtvAddress) as TextView
            this.mTxtIdNo = row?.findViewById(R.id.mtvIdNo) as TextView
            this.btnDelete = row?.findViewById(R.id.mbtnDelete) as Button
            this.btnUpdate = row?.findViewById(R.id.mbtnEdit) as Button
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.tenants_list_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Tenant = getItem(position) as Tenant
        viewHolder.mTxtName.text = item.name
        viewHolder.mTxtEmail.text = item.email
        viewHolder.mTxtPhoneNo.text = item.phoneNo
        viewHolder.mTxtAddress.text = item.address
        viewHolder.mTxtIdNo.text = item.idNo
        viewHolder.btnDelete.setOnClickListener {
            var ref = FirebaseDatabase.getInstance().getReference().child("Tenants/"+item.id)
            ref.removeValue().addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(context,"Tenant Deleted successfully",
                        Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Deleting tenant failed", Toast.LENGTH_LONG).show()
                }
            }
        }
        viewHolder.btnUpdate.setOnClickListener {
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
            var intent = Intent(context,UpdateTenantActivity::class.java)
            intent.putExtra("name",item.name)
            intent.putExtra("email",item.email)
            intent.putExtra("phoneNo",item.phoneNo)
            intent.putExtra("address",item.address)
            intent.putExtra("idNo",item.idNo)
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