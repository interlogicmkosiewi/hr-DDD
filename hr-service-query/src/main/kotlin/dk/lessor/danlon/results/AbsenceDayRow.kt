package dk.lessor.danlon.results

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

class AbsenceDayRow(
    var dayRowId: Int? = -1,
    var dateISO: String,
    val dateText: String?,
    val dayName: String?,
    var dayType: DayTypeEnum,
    var hourValue: BigDecimal,
    var workHoursInDay: BigDecimal = BigDecimal(7.4).setScale(2, RoundingMode.HALF_EVEN),
    var transferDateTime: LocalDateTime?
) {
    override fun toString(): String {
        return "{dayRowId: $dayRowId, dateISO: $dateISO, transferDateTime: $transferDateTime, " +
                "dateText: $dateText, dayName: $dayName, dayType: $dayType, hourValue: $hourValue, workHoursInDay: $workHoursInDay}"
    }
}
