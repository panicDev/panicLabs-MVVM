package id.panicLabs.core.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
class RoomTypeConverters {

    @TypeConverter
    fun fromStringToListString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listStringToString(list: List<String>): String {
        return Gson().toJson(list)
    }

}
