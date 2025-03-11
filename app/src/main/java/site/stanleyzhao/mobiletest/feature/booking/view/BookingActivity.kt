package site.stanleyzhao.mobiletest.feature.booking.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import site.stanleyzhao.mobiletest.R
import site.stanleyzhao.mobiletest.feature.booking.model.BookingManager

/**
 * 展示航班信息的页面
 * @Stanley
 */
class BookingActivity : AppCompatActivity() {

    private val bookingManager: BookingManager by lazy {
        BookingManager()
    }
    private var bookingRecyclerView: RecyclerView? = null
    private val bookingAdapter: BookingAdapter by lazy {
        BookingAdapter()
    }

    private var isFirstOnResume = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        bookingRecyclerView = findViewById(R.id.recyclerView)
        bookingRecyclerView?.layoutManager = LinearLayoutManager(this)

        bookingRecyclerView?.adapter = bookingAdapter

        refreshData()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstOnResume){
            //避免第一次重复调用
            isFirstOnResume = false
            return
        }
        refreshData() // 每次页面显示时刷新数据
    }

    private fun refreshData() {
        //正常这段逻辑是放到viewmodel层处理，这里demo先放view层处理
        val bookingData = bookingManager.getBookings()
        val currentTime = System.currentTimeMillis() / 1000
        //如果缓存的数据时间没有超过过期时间，则直接读缓存，否则从service重新拿一遍数据同时存到缓存中
        val realBookingData = if (bookingData != null && currentTime < bookingData.expiryTime) {
            bookingData
        } else {
            bookingManager.refreshAndGetBookings()
        }

        Log.d("BookingActivity", "Booking data: $realBookingData")

        //错误处理，如果返回的数据为空或者其他的错误导致数据为空则显示错误提示view
        bookingAdapter.isStateViewEnable = true
        bookingAdapter.setStateViewLayout(this, R.layout.error_view)

        realBookingData.let {
            bookingAdapter.submitList(it.segments)
        }
    }
}