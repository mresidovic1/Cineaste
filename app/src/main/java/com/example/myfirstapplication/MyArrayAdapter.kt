package com.example.myfirstapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class MyArrayAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val elements: ArrayList<String>):
    ArrayAdapter<String>(context, layoutResource, elements) {
    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, newView: View?, parent: ViewGroup): View {
        val newView =  LayoutInflater.from(context).inflate(R.layout.element_list, parent, false)
        val textView = newView.findViewById<TextView>(R.id.textElement)
        val element = elements[position]
        textView.text=element
        return newView
    }
}