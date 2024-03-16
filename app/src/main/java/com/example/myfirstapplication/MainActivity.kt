package com.example.myfirstapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private val lista= arrayListOf<String>()
    private lateinit var adapter : MyArrayAdapter
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button1)
        editText=findViewById(R.id.editText1)
        listView=findViewById(R.id.listView1)
        adapter= MyArrayAdapter(this,R.layout.element_list,lista)
        listView.adapter=adapter
        button.setOnClickListener{
            dodajUListu()
        }
    }
    private fun dodajUListu() {
        lista.add(0,editText.text.toString())
        adapter.notifyDataSetChanged()
        editText.setText("")
    }
}