package com.example.dota

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DataConverter {
    @SuppressLint("SimpleDateFormat")
    fun convertTime(unixTime: MutableList<Long>): MutableList<String> {
        val newTime = mutableListOf<String>()
        val newFormat = SimpleDateFormat("MMM dd, yyyy")
        unixTime.forEach {
            //newTime.add(newFormat.format(it))
            Log.d("api_test", newFormat.format(it))
        }
        return newTime
    }
}