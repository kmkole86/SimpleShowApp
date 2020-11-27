package com.example.simpleshow.business.domain.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtil @Inject constructor() {

    private val dateFormat: SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)

    fun getCurrentTimestamp(): String {
        return dateFormat.format(Date())
    }

    fun getUnixTimestamp(unixTime: Long): String {
        return dateFormat.format(Date(unixTime))
    }
}