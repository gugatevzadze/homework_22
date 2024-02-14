package com.example.homework_22.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    fun epochToReadableDate(epoch: Long): String {
        val date = Date(epoch * 1000L)
        val format = SimpleDateFormat("dd MMMM 'at' hh:mm a", Locale.getDefault())
        return format.format(date)
    }
}