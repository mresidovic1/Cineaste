package com.example.cineaste

import android.content.BroadcastReceiver
import android.net.ConnectivityManager
import android.widget.Toast
import android.content.Context
import android.content.Intent


class ConnectivityBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mreza = connManager.activeNetwork
        val conn = connManager.getNetworkCapabilities(mreza)
        if (conn == null) {
            val toast = Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            val toast = Toast.makeText(context, "Connected", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}