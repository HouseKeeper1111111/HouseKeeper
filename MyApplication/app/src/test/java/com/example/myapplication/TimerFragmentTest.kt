package com.example.myapplication

import com.example.myapplication.ui.reminderslist.TimerFragment
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class TimerFragmentTest {

    @Test
    fun testStartTimer() {
        val fragment = TimerFragment()

        fragment.pickerHours.value = 1
        fragment.pickerMinutes.value = 30
        fragment.pickerSeconds.value = 45

        fragment.startTimer((1 * 3600 + 30 * 60 + 45) * 1000L)

        val expectedTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", 1, 30, 45)
        assertEquals(expectedTime, fragment.textViewTimer.text)
    }

    @Test
    fun testStopTimer() {
        val fragment = TimerFragment()

        fragment.pickerMinutes.value = 2
        fragment.pickerSeconds.value = 0
        fragment.startTimer((2 * 60) * 1000L)

        fragment.stopTimer()

        assertEquals("00:00:00", fragment.textViewTimer.text)
        assertEquals(0L, fragment.remainingTimeInMillis)
    }

    @Test
    fun testPauseTimer() {
        val fragment = TimerFragment()

        fragment.pickerMinutes.value = 1
        fragment.pickerSeconds.value = 0

        fragment.startTimer(60 * 1000L)

        fragment.pauseTimer()

        assertEquals(60000L, fragment.remainingTimeInMillis)
    }

    @Test
    fun testSaveTimer() {
        val fragment = TimerFragment()

        fragment.pickerMinutes.value = 5
        fragment.pickerSeconds.value = 30

        fragment.saveTimer()

        assertEquals(1, fragment.savedTimers.size)
        assertEquals(5 * 60 * 1000L + 30 * 1000L, fragment.savedTimers[0])
    }

    @Test
    fun testSetTimerFromQuickAccess() {
        val fragment = TimerFragment()

        val timeInMillis = 10 * 60 * 1000L
        fragment.addQuickTimerButton(timeInMillis)

        fragment.setTimerFromQuickAccess(timeInMillis)

        assertEquals(10, fragment.pickerMinutes.value)
        assertEquals(0, fragment.pickerSeconds.value)
    }

    @Test
    fun testSendTimerNotification() {
        val fragment = TimerFragment()

        fragment.sendTimerNotification()
    }

    @Test
    fun testCheckAndRequestNotificationPermission() {
        val fragment = TimerFragment()

        fragment.checkAndRequestNotificationPermission()
    }
}
