package com.example.newsapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getDate(): String {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(date)
}

@SuppressLint("SimpleDateFormat")
fun getPreviousDate(days: Int): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.add(Calendar.DAY_OF_YEAR, -days)
    val previousDate = calendar.time
    return dateFormat.format(previousDate)

}
