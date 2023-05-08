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


internal class HouseAdapter(private val context: Context, listHouses: ArrayList<Houses>) :
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
        val contacts = listHouses[position]
        holder.tvName.text = contacts.name
        holder.tvPhoneNum.text = contacts.phno
        holder.editContact.setOnClickListener { editTaskDialog(contacts) }
        holder.deleteContact.setOnClickListener {
            mDatabase.deleteContact(contacts.id)
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
                        if (houses.name.toLowerCase().contains(charString)) {
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
    private fun editTaskDialog(contacts: Houses) {
        val inflater = LayoutInflater.from(context)
        val subView = inflater.inflate(R.layout.add_houses, null)
        val nameField: EditText = subView.findViewById(R.id.enterName)
        val contactField: EditText = subView.findViewById(R.id.enterPhoneNum)
        nameField.setText(contacts.name)
        contactField.setText(contacts.phno)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit houses")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton(
            "EDIT Houses"
        ) { _, _ ->
            val name = nameField.text.toString()
            val phNo = contactField.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(
                    context,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                mDatabase.updateHouses(
                    Houses(
                        Objects.requireNonNull<Any>(contacts.id) as Int,
                        name,
                        phNo
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