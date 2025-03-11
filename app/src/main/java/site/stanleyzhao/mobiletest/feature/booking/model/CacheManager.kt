package site.stanleyzhao.mobiletest.feature.booking.model

import android.content.Context
import android.content.SharedPreferences
import com.blankj.utilcode.util.Utils
import com.google.gson.Gson

/**
 * 缓存管理类
 * 用sp简单实现
 */
class CacheManager {
    private val preferences: SharedPreferences by lazy {
        Utils.getApp().getSharedPreferences("BookingCache", Context.MODE_PRIVATE)
    }
    private val CACHE_KEY = "BOOKINGS_CACHE"

    fun saveCache(data: BookingData) {
        val gson = Gson()
        val jsonData = gson.toJson(data)
        preferences.edit().putString(CACHE_KEY, jsonData).apply()
    }

    fun loadCache(): BookingData? {
        val jsonData = preferences.getString(CACHE_KEY, null)
        return if (jsonData != null) {
            val gson = Gson()
            gson.fromJson(jsonData, BookingData::class.java)
        } else {
            null
        }
    }
}