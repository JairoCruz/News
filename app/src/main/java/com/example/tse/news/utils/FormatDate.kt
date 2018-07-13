package com.example.tse.news.utils

import java.text.SimpleDateFormat
import java.util.*

class FormatDate {
    companion object {
        fun toSimpleString(date: Date): String{
            val format = SimpleDateFormat("EEE, d MMM yyyy")
            return format.format(date)
        }
    }
    //HH:mm:ss
}