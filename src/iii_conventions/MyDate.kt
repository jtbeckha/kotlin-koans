package iii_conventions

import iv_properties.toMillis

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        if (this.toMillis() > other.toMillis()) {
            return 1
        }

        if (this.toMillis() < other.toMillis()) {
            return -1
        }

        return 0
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)
