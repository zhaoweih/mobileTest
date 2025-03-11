package site.stanleyzhao.mobiletest.feature.booking.model


/**
 * Booking Manager
 * 用于管理数据相关的类，例如从后台拉数据和缓存
 */
class BookingManager {

    private val bookingService: BookingService by lazy {
        BookingService()
    }
    private val cacheManager: CacheManager by lazy {
        CacheManager()
    }

    /**
     * 更新缓存的数据，同时返回最新的数据
     */
    fun refreshAndGetBookings(): BookingData {
        val freshBookings = bookingService.getBookings()
        cacheManager.saveCache(freshBookings)
        return freshBookings
    }

    /**
     * 从缓存提取数据
     */
    fun getBookings(): BookingData? {
        val cachedBookings = cacheManager.loadCache()
        return cachedBookings
    }
}