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

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(interval: TimeInterval): MyDate {
    return addTimeIntervals(interval, 1)
}

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate {
    return addTimeIntervals(interval.interval, interval.times)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(times: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this, times)
}

class RepeatedTimeInterval(val interval: TimeInterval, val times: Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {

            var date = start

            override fun hasNext(): Boolean = this.date <= endInclusive
            override fun next(): MyDate {
                val retVal = date
                date = date.nextDay()
                return retVal
            }
        }
    }
}
