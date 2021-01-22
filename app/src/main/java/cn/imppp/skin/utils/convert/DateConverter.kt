package cn.imppp.skin.utils.convert

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DateConverter {
    @TypeConverter
    fun revertDate(value: Long): Date {
        Log.i("dateRevert", "success")
        return Date(value)
    }

    @TypeConverter
    fun converterDate(value: Date): Long {
        Log.i("dateConvert", "success")
        return value.time
    }
}

class ListConverter {

    @TypeConverter
    fun revertDate(value: List<String>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun converterDate(value: String): List<String> {
        val gson = Gson()
        return gson.fromJson(value, object :
            TypeToken<List<String?>?>() {}.type)
    }
}
