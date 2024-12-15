package com.example.myapplication

import com.example.myapplication.util.DateTimeUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite
import java.util.Calendar

@RunWith(Suite::class)
@Suite.SuiteClasses(DateTimeUtilTest.TestHoursMinutesBetween::class)
class DateTimeUtilTest {

    class TestHoursMinutesBetween {

        @Test
        fun testHoursMinutesBetween1() {
            testPositiveNegative(0, 0, DateTimeUtil.Duration(0, 0, true))
        }

        @Test
        fun testHoursMinutesBetween2() {
            testPositiveNegative(1234567890, 1234567890, DateTimeUtil.Duration(0, 0, true))
        }

        @Test
        fun testHoursMinutesBetween3() {
            testPositiveNegative(0, 1000, DateTimeUtil.Duration(0, 0, true))
        }

        @Test
        fun testHoursMinutesBetween4() {
            testPositiveNegative(123400000, 123459999, DateTimeUtil.Duration(0, 0, true))
        }

        @Test
        fun testHoursMinutesBetween5() {
            testPositiveNegative(123400000, 123460000, DateTimeUtil.Duration(0, 1, true))
        }

        @Test
        fun testHoursMinutesBetween6() {
            testPositiveNegative(0, 1000 * 60 * 60 - 1, DateTimeUtil.Duration(0, 59, true))
        }

        @Test
        fun testHoursMinutesBetween7() {
            testPositiveNegative(0, 1000 * 60 * 60, DateTimeUtil.Duration(1, 0, true))
        }

        @Test
        fun testHoursMinutesBetween8() {
            testPositiveNegative(0, 1000 * 61 * 60, DateTimeUtil.Duration(1, 1, true))
        }

        @Test
        fun testHoursMinutesBetween9() {
            testPositiveNegative(0, 1000L * 2000 * 60 * 60 + 1000 * 60 * 5, DateTimeUtil.Duration(2000, 5, true))
        }

        @Test
        fun testHoursBetween10() {
            val start = Calendar.getInstance()
            val end = Calendar.getInstance()
            end.time = start.time
            end.add(Calendar.MINUTE, 1)
            testWithCalendar(start, end, 0, 1)
        }

        @Test
        fun testHoursBetween11() {
            val start = Calendar.getInstance()
            val end = Calendar.getInstance()
            end.time = start.time
            end.add(Calendar.HOUR_OF_DAY, 1)
            testWithCalendar(start, end, 1, 0)
        }

        @Test
        fun testHoursBetween12() {
            val start = Calendar.getInstance()
            val end = Calendar.getInstance()
            end.time = start.time
            end.add(Calendar.SECOND, 59)
            testWithCalendar(start, end, 0, 0)
        }

        @Test
        fun testHoursBetween13() {
            val start = Calendar.getInstance()
            testWithCalendar(start, start, 0, 0)
        }

        @Test
        fun testHoursBetween14() {
            val start = Calendar.getInstance()
            val end = Calendar.getInstance()
            end.time = start.time
            end.add(Calendar.SECOND, 60 * 70)
            // This should hold even with leap seconds etc.
            testWithCalendar(start, end, 1, 10)
        }

        private fun testPositiveNegative(start: Long, end: Long, expected: DateTimeUtil.Duration) {
            assertEquals(expected, DateTimeUtil.hoursMinutesBetween(start, end))
            if (start != end) {
                assertEquals(
                    DateTimeUtil.Duration(expected.hours, expected.minutes, false),
                    DateTimeUtil.hoursMinutesBetween(end, start)
                )
            }
        }

        private fun testWithCalendar(start: Calendar, end: Calendar, hours: Long, minutes: Int) {
            testPositiveNegative(start.time.time, end.time.time, DateTimeUtil.Duration(hours, minutes, true))
        }
    }
}
