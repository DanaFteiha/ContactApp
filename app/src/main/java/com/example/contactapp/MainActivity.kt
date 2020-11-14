package com.example.contactapp

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.ClassCastException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ContactAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewAdapter = ContactAdapter(this)
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.contactsRecyclerView).apply {
            setHasFixedSize(true)
            // use a layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        binding.btnAdd.setOnClickListener() {
            val name = binding.etName.text.toString()
            val phone = binding.etPhone.text.toString()
            val newContact = Contact(name, phone)
            viewAdapter.addContact(contact = newContact)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode ==1 && resultCode == Activity.RESULT_OK){
            val contact = data?.getParcelableExtra<Contact>("contact")
            contact?.let {
                viewAdapter.delete(contact)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if (menuItem != null) {
                val searchView = menuItem.actionView as? SearchView

            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewAdapter.search(newText.orEmpty())
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}


