package com.example.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.*
import kotlinx.android.synthetic.main.item_view.view.*

class ContactAdapter(var contactList: ArrayList<Contact>,val callback: ContactCallback):
    RecyclerView.Adapter<ContactAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun populatePersonItem(contact: Contact){
            itemView.tvName.text = contact.firstName
            itemView.tvLastName.text = contact.lastName
            itemView.tvNumber.text = contact.number
            itemView.setOnClickListener {callback.passContact(contact)}
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int)
        = holder.populatePersonItem((contactList[position]))

    fun updateList(passedList : ArrayList<Contact>) {
        contactList = passedList
        notifyDataSetChanged()
    }

}