package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ItemContactBinding

class ContactViewHolder(
    private var binding: ItemContactBinding,
    private var activity : AppCompatActivity
): RecyclerView.ViewHolder(binding.root){


    fun bind(contact: Contact) {
        binding.name = contact.name
        binding.number = contact.phoneNumber
        itemView.setOnClickListener{
            val intent = Intent(activity,ContactActivity::class.java).putExtra("contact",contact)
            activity.startActivityForResult(intent,1)
        }
    }


}