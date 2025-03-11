package site.stanleyzhao.mobiletest.feature.booking.model

import com.blankj.utilcode.util.Utils
import com.google.gson.Gson
import java.io.InputStreamReader

/**
 * Booking Service
 * 从文件读取booking数据
 *
 */
class BookingService {

    fun getBookings(): BookingData {
        val newBookings = fetchBookingsFromJson()
        return newBookings
    }

    private fun fetchBookingsFromJson(): BookingData {
        val jsonData = loadJsonDataFromFile()
        val bookingData = Gson().fromJson(jsonData, BookingData::class.java)
        return bookingData
    }

    private fun loadJsonDataFromFile(): String {
        val inputStream = Utils.getApp().assets.open("booking.json")
        val inputStreamReader = InputStreamReader(inputStream)
        return inputStreamReader.readText()
    }
}