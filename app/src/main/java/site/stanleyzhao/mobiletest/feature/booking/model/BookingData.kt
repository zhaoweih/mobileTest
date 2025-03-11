package site.stanleyzhao.mobiletest.feature.booking.model

/**
 * Booking数据类
 * @Stanley
 */
data class BookingData(
    val shipReference: String,
    val shipToken: String,
    val canIssueTicketChecking: Boolean,
    val expiryTime: Long,
    val duration: Int,
    val segments: List<Segment>
)

data class Segment(
    val id: Int,
    val originAndDestinationPair: OriginAndDestinationPair
)

data class OriginAndDestinationPair(
    val origin: Location,
    val originCity: String,
    val destination: Location,
    val destinationCity: String
)

data class Location(
    val code: String,
    val displayName: String,
    val url: String
)