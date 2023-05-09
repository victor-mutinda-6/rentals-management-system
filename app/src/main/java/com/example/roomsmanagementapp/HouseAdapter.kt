package com.example.roomsmanagementapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class HouseAdapter(private val context: Context, listHouses: ArrayList<Houses>) :
    RecyclerView.Adapter<HousesViewHolder>(), Filterable {
    private var listHouses: ArrayList<Houses>
    private val mArrayList: ArrayList<Houses>
    private val mDatabase: SqliteDatabase
    init {
        this.listHouses = listHouses
        this.mArrayList = listHouses
        mDatabase = SqliteDatabase(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HousesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.houses_list_layout, parent, false)
        return HousesViewHolder(view)
    }
    override fun onBindViewHolder(holder: HousesViewHolder, position: Int) {
        val houses = listHouses[position]
        holder.tvNumber.text = houses.number
        holder.tvType.text = houses.type
        holder.tvRent.text = houses.rent
        holder.tvTenant.text = houses.tenant
        holder.tvMore.text = houses.moreInfor
        holder.editHouse.setOnClickListener { editTaskDialog(houses) }
        holder.deleteHouse.setOnClickListener {
            mDatabase.deleteHouse(houses.id)
            (context as Activity).finish()
            context.startActivity(context.intent)
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                listHouses = if (charString.isEmpty()) {
                    mArrayList
                }
                else {
                    val filteredList = ArrayList<Houses>()
                    for (houses in mArrayList) {
                        if (houses.number.toLowerCase().contains(charString)) {
                            filteredList.add(houses)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = listHouses
                return filterResults
            }
            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            )
            {
                listHouses =
                    filterResults.values as ArrayList<Houses>
                notifyDataSetChanged()
            }
        }
    }
    override fun getItemCount(): Int {
        return listHouses.size
    }
    private fun editTaskDialog(houses: Houses) {
        val inflater = LayoutInflater.from(context)
        val subView = inflater.inflate(R.layout.add_houses, null)
        val numberField: EditText = subView.findViewById(R.id.enterNumber)
        val typeField: EditText = subView.findViewById(R.id.enterType)
        val rentField: EditText = subView.findViewById(R.id.enterRent)
        val tenantField: EditText = subView.findViewById(R.id.enterTenant)
        val moreField: EditText = subView.findViewById(R.id.enterMore)
        numberField.setText(houses.number)
        typeField.setText(houses.type)
        rentField.setText(houses.rent)
        tenantField.setText(houses.tenant)
        moreField.setText(houses.moreInfor)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit houses")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton(
            "EDIT Houses"
        ) { _, _ ->
            val number = numberField.text.toString()
            val type = typeField.text.toString()
            val rent = rentField.text.toString()
            val tenant = tenantField.text.toString()
            val more = moreField.text.toString()
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(
                    context,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                mDatabase.updateHouses(
                    Houses(
                        Objects.requireNonNull<Any>(houses.id) as Int,
                        number,
                        type,
                        rent,
                        tenant,
                        more
                    )
                )
                (context as Activity).finish()
                context.startActivity(context.intent)
            }
        }
        builder.setNegativeButton(
            "CANCEL"
        ) { _, _ -> Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show() }
        builder.show()
    }
}