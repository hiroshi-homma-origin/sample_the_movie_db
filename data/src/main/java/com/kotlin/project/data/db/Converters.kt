package com.kotlin.project.data.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun listToJson(value: ArrayList<Int>): String = value.joinToString(separator = ",")

    @TypeConverter
    fun jsonToList(value: String): ArrayList<Int> {
        val array = arrayListOf<Int>()
        value.split(",").forEach { array.add(it.toInt()) }
        return array
    }

    @TypeConverter
    fun listStringToJson(value: ArrayList<String>): String = value.joinToString(separator = ",")

    @TypeConverter
    fun jsonToListString(value: String): ArrayList<String> {
        val array = arrayListOf<String>()
        value.split(",").forEach { array.add(it) }
        return array
    }
}
