package com.gochoa.localizaciontest.utils

import android.content.Context
import com.gochoa.localizaciontest.R
import java.text.SimpleDateFormat
import java.util.Date

object DateFormatted {

    fun getDate(context: Context): String {
        val date = Date()
        val formatted = SimpleDateFormat(context.getString(R.string.date_format))
        return context.getString(R.string.date_created, formatted.format(date))
    }
}