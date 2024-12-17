package com.example.myapplication.util

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import com.example.myapplication.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * Utilities for formatting and calculation with date and time.
 * Note that we cannot use java.time because it requires API 26.
 */
object DateTimeUtil {
    private var dfDateTime: DateFormat? = null
    private var dfDate: DateFormat? = null
    private var dfTime: DateFormat? = null

    /**
     * Format the given amount of minutes (e.g. "0 minutes", "1 minute").
     * Uses singular form for input "1" and plural form otherwise.
     *
     * @param minutes
     * @param context
     * @return
     */
    fun formatMinutes(minutes: Long, context: Context): String {
        return if (minutes == 1L) {
            context.getString(R.string.duration_minute, minutes)
        } else {
            context.getString(R.string.duration_minutes, minutes)
        }
    }

    /**
     * Format the given amount of hours (e.g. "0 hours", "1 hour").
     * Uses singular form for input "1" and plural form otherwise.
     *
     * @param hours
     * @param context
     * @return
     */
    fun formatHours(hours: Long, context: Context): String {
        return if (hours == 1L) {
            context.getString(R.string.duration_hour, hours)
        } else {
            context.getString(R.string.duration_hours, hours)
        }
    }

    /**
     * Format the given amount of days (e.g. "0 days", "1 day").
     * Uses singular form for input "1" and plural form otherwise.
     *
     * @param days
     * @param context
     * @return
     */
    fun formatDays(days: Long, context: Context): String {
        return if (days == 1L) {
            context.getString(R.string.duration_day, days)
        } else {
            context.getString(R.string.duration_days, days)
        }
    }

    private val dateTimeFormat: DateFormat?
        get() {
            if (dfDateTime == null) {
                dfDateTime = DateFormat.getDateTimeInstance()
            }
            return dfDateTime
        }


    @get:SuppressLint("SimpleDateFormat")
    private val timeFormat: DateFormat?
        get() {
            if (dfTime == null) {
                dfTime = SimpleDateFormat("HH:mm")
                DateFormat.getTimeInstance(DateFormat.MEDIUM)
            }
            return dfTime
        }


    private val dateFormat: DateFormat?
        get() {
            if (dfDate == null) {
                dfDate = DateFormat.getDateInstance(DateFormat.MEDIUM)
            }
            return dfDate
        }

    /**
     * Used to compare whether two dates are on the same day:
     */
    private var dfCompareDay: SimpleDateFormat? = null

    @SuppressLint("SimpleDateFormat")
    private fun getDfCompareDay(): SimpleDateFormat {
        if (dfCompareDay == null) {
            dfCompareDay = SimpleDateFormat("ddMMyyyy")
        }
        return dfCompareDay!!
    }

    fun formatDateTime(date: Date?): String {
        return dateTimeFormat!!.format(date)
    }

    fun formatDate(context: Context?, date: Date): String {
        return DateUtils.formatDateTime(
            context,
            date.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NO_YEAR or DateUtils.FORMAT_ABBREV_ALL
        )
    }

    fun formatDateWithDayOfWeek(context: Context?, date: Date): String {
        return DateUtils.formatDateTime(
            context,
            date.time,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_NO_YEAR or DateUtils.FORMAT_ABBREV_ALL
        )
    }

    fun formatTime(date: Date?): String {
        return timeFormat!!.format(date)
    }

    /**
     * Check whether two dates are on the same day.
     *
     * @param d1
     * @param d2
     * @return
     */
    fun isSameDay(d1: Date?, d2: Date?): Boolean {
        return getDfCompareDay().format(d1) == dfCompareDay!!.format(d2)
    }

    /**
     * Check whether the given date is at the current day.
     *
     * @param d
     * @return
     */
    fun isToday(d: Date?): Boolean {
        return isSameDay(d, Date())
    }

    /**
     * Get a copy of the given date where hour, minute, second and millisecond are set to 0.
     *
     * @param date
     * @return
     */
    fun getDateAtMidnight(date: Calendar): Calendar {
        val cal = Calendar.getInstance()
        cal.time = date.time
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal
    }

    /**
     * Given two dates, calculates how often the day (number) is incremented from the first to the second.
     * Based on https://stackoverflow.com/a/6406294.
     */
    fun dayChangesBetween(start: Calendar, end: Calendar): Long {
        if (isSameDay(start.time, end.time)) {
            return 0
        }

        val d = getDateAtMidnight(start)
        val e = getDateAtMidnight(end)
        var dayChanges: Long = 0
        while (d.before(e)) {
            d.add(Calendar.DAY_OF_MONTH, 1)
            dayChanges++
        }
        return dayChanges
    }

    /**
     * Return the duration of hours and minutes between two timestamps.
     * If end < start, then the result is a negative duration. There is no rounding,
     * exceeding seconds are cut off.
     *
     * @param start start time in milliseconds
     * @param end   end time in milliseconds
     * @return
     */
    fun hoursMinutesBetween(start: Long, end: Long): Duration {
        return if (start <= end) {
            hoursMinutesBetween_(start, end, true)
        } else {
            hoursMinutesBetween_(end, start, false)
        }
    }

    private fun hoursMinutesBetween_(start: Long, end: Long, positive: Boolean): Duration {
        val seconds = (end - start) / 1000
        val hours = seconds / 3600
        val minutes = (seconds - hours * 3600) / 60
        return Duration(hours, minutes.toInt(), positive)
    }

    /**
     * Return the duration of days, hours and minutes between two dates. There is no rounding, exceeding seconds are cut off.
     * Days are in terms of calendar days, except for the last day change and the remaining hours.
     * This only has relevance regarding daylight saving time and means that the last 24 hours are interpreted as one day and that when clocks are set back by one hour it is possible to get a duration like "1 day, 24 hours, 50 minutes".
     *
     * @param start
     * @param end   Must be after `start` and the day changes between the two dates must not exceed [Integer.MAX_VALUE].
     * @return
     * @throws IllegalArgumentException if any precondition is violated
     */
    @Throws(IllegalArgumentException::class)
    fun daysHoursMinutesBetween(start: Calendar, end: Calendar): Duration {
        /*
         * Check preconditions
         */
        require(start.before(end)) { "Negative duration" }

        val dayChangesLong = dayChangesBetween(start, end)
        require(dayChangesLong <= Int.MAX_VALUE) { "Duration too large" }
        val dayChanges = dayChangesLong.toInt()

        /*
         * Calculate number of days, hours and minutes.
         */
        var days = 0
        // 'end' after subtracting days so that dayChanges <= 1; i.e., duration between start and newEnd is less than 1 day and 24 hours (except for days with more than 24 hours)
        val newEnd = Calendar.getInstance()
        newEnd.time = end.time
        if (dayChanges > 1) {
            days = dayChanges - 1
            newEnd.add(Calendar.DAY_OF_MONTH, -days)
        }

        val duration = hoursMinutesBetween(start.time.time, newEnd.time.time)
        var hours = duration.hours.toInt() // always less than the equivalent of two days
        val minutes = duration.minutes
        // Here we call potentially remaining 24 hours one day.
        if (hours >= 24) {
            days++
            hours -= 24
        }
        return Duration(
            days.toLong(), hours.toLong(), minutes,
            duration.isPositive
        )
    }

    /**
     * Represents a duration in days, hours and minutes and whether it is a positive or negative duration.
     */
    class Duration
    /**
     * Create a positive duration.
     *
     * @param days
     * @param hours
     * @param minutes
     * @see .Duration
     */ @JvmOverloads constructor(
        val days: Long, val hours: Long, val minutes: Int,
        /**
         * Whether the duration is declared positive.
         *
         * @return
         */
        val isPositive: Boolean = true
    ) {
        /**
         * Which unit to round to.
         */
        enum class Resolution {
            DAYS,
            HOURS,
            MINUTES,

            /**
             * Like [.MINUTES] if number of days is zero, like [.HOURS] otherwise.
             */
            MINUTES_IF_0_DAYS
        }

        /**
         * How to round to the next unit.
         */
        enum class RoundingMode {
            /**
             * Round towards closest (up when same difference).
             */
            CLOSEST,
            DOWN,
            UP
        }

        /**
         * Create a positive duration with 0 days.
         *
         * @param hours
         * @param minutes
         * @see .Duration
         */
        constructor(hours: Long, minutes: Int) : this(0, hours, minutes, true)

        /**
         * Create a duration with 0 days.
         * @param hours
         * @param minutes
         * @param positive
         * @see .Duration
         */
        constructor(hours: Long, minutes: Int, positive: Boolean) : this(
            0,
            hours,
            minutes,
            positive
        )

        /**
         * Create a duration. All fields must be non-negative numbers.
         *
         * @param days
         * @param hours
         * @param minutes
         * @param isPositive
         */

        val isZero: Boolean
            /**
             * Whether days, hours and minutes are all zero.
             * @return
             */
            get() = days == 0L && hours == 0L && minutes == 0


        /**
         * Get a textual description of the duration with the possibility to round.
         * If `isZero() == true`, the empty string is returned.
         *
         * @param resolution which units to show, see [Resolution]
         * @param roundingMode how to round, see [RoundingMode]
         * @param context
         */
        fun toString(resolution: Resolution, roundingMode: RoundingMode, context: Context): String {
            var printMinutes = minutes
            var printHours = hours
            var printDays = days
            // Apply rounding: set those units to 0 which should be rounded away.
            if (resolution == Resolution.HOURS || resolution == Resolution.MINUTES_IF_0_DAYS && days > 0) {
                printMinutes = 0
                if (roundingMode == RoundingMode.UP && minutes > 0 || roundingMode == RoundingMode.CLOSEST && minutes >= 30) {
                    printHours++
                    if (printHours == 24L) {
                        printDays += 1
                        printHours -= 24
                    }
                }
            } else if (resolution == Resolution.DAYS) {
                printMinutes = 0
                printHours = 0
                if (roundingMode == RoundingMode.UP && hours > 0 || roundingMode == RoundingMode.CLOSEST && hours >= 12) {
                    printDays++
                }
            }
            val sb = StringBuilder()
            var lastConjunctionUsed =
                false // Whether the conjunction to be used between the last two units has been used
            if (printMinutes != 0) {
                sb.insert(0, formatMinutes(printMinutes.toLong(), context))
            }
            if (printHours != 0L) {
                lastConjunctionUsed = insertConjunction(lastConjunctionUsed, sb, context)
                sb.insert(0, formatHours(printHours, context))
            }
            if (printDays != 0L) {
                lastConjunctionUsed = insertConjunction(lastConjunctionUsed, sb, context)
                sb.insert(0, formatDays(printDays, context))
            }
            return sb.toString()
        }

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false

            val that = o as Duration

            if (days != that.days) return false
            if (hours != that.hours) return false
            if (minutes != that.minutes) return false
            return isPositive == that.isPositive
        }

        // Generated by IntelliJ
        override fun hashCode(): Int {
            var result = (days xor (days ushr 32)).toInt()
            result = 31 * result + (hours xor (hours ushr 32)).toInt()
            result = 31 * result + minutes
            result = 31 * result + (if (isPositive) 1 else 0)
            return result
        }

        override fun toString(): String {
            return "DurationHoursMinutes{" +
                    "days=" + days +
                    ", hours=" + hours +
                    ", minutes=" + minutes +
                    ", positive=" + isPositive +
                    '}'
        }

        companion object {
            private fun insertConjunction(
                lastConjunctionUsed: Boolean,
                sb: StringBuilder,
                context: Context
            ): Boolean {
                if (sb.isNotEmpty()) {
                    if (lastConjunctionUsed) {
                        sb.insert(0, context.getString(R.string.duration_conjunction))
                    } else {
                        sb.insert(0, context.getString(R.string.duration_conjunction_last))
                    }
                    return true
                }
                return false
            }
        }
    }
}