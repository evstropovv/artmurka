package com.artmurka.artmurkaapp.other

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkCheckUtil @Inject constructor(var context: Context) {

    val isOnline: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
}