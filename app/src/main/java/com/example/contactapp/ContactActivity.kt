package com.example.contactapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.contactapp.databinding.ActivityContactBinding
import com.example.contactapp.databinding.ActivityMainBinding

class ContactActivity : AppCompatActivity(){
    private lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact)
        val contact = intent.getParcelableExtra<Contact>("contact")
        binding.contact = contact
        binding.btnRemove.setOnClickListener {
            val intent = Intent()
            intent.putExtra("contact",contact)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

}
