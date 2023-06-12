package net.hwyz.iov.data.user.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    val gson by lazy {
        Gson()
    }

    @TypeConverter
    fun fromListOfStringToString(list: List<String>): String {
        return list.joinToString(separator = "|")
    }

}