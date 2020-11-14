package com.example.contactapp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.logging.Filter
import java.util.logging.LogRecord
import kotlin.collections.ArrayList

class ContactAdapter(private val activity: AppCompatActivity) : RecyclerView.Adapter<ContactViewHolder>() {
    private var contactsList = ArrayList<Contact>()
    private val displayList = ArrayList<Contact>()

    init {
        //dummy data
        contactsList.add(Contact("Dana fteiha", "0522662655"))
        contactsList.add(Contact("Lama Daoudi", "0522662655"))
        contactsList.add(Contact("Nizar", "0522662655"))
        displayList.addAll(contactsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_contact,
                parent,
                false
            ),
        activity
        )
    }

    override fun getItemCount() = displayList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = displayList[position]
        holder.bind(contact)
    }

    fun addContact(contact: Contact) {
        contactsList.add(contact)
        displayList.add(contact)
        notifyDataSetChanged()
    }

    fun search(newText : String){
        if (newText.isNotEmpty()) {
            displayList.clear()
            val search = newText.toLowerCase(Locale.getDefault())
            contactsList.forEach {
                if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                    displayList.add(it)
                }
            }
            notifyDataSetChanged()
        } else {
            displayList.clear()
            displayList.addAll(contactsList)
            notifyDataSetChanged()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun delete(contact: Contact){
        contactsList.removeIf {
            it.name == contact.name && it.phoneNumber == contact.phoneNumber
        }
        displayList.removeIf {
            it.name == contact.name && it.phoneNumber == contact.phoneNumber
        }
        notifyDataSetChanged()
    }


}





