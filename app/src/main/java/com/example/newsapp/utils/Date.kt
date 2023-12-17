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

@SuppressLint("SimpleDateFormat")
fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("dd. MM. yyyy")
    return try {
        val parsedDate = inputFormat.parse(date)
        outputFormat.format(parsedDate)
    } catch (e: Exception) {
        e.printStackTrace()
        date
    }
}