package com.example.investigacion2dsi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contacts: List<Contact>, private
val listener: (Contact) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val name: TextView = view.findViewById(R.id.contact_name)
        val icon: ImageView =
            view.findViewById(R.id.contact_icon)

        fun bind(contact: Contact, listener: (Contact) -> Unit) {
            name.text = contact.name
            icon.setImageResource(R.drawable.ic_contact)
            itemView.setOnClickListener { listener(contact) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact
                , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:
    Int) {
        holder.bind(contacts[position], listener)
    }

    override fun getItemCount() = contacts.size
}