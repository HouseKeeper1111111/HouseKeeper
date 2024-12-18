package com.example.myapplication.ui.reminderslist

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.util.Locale

class TimerFragment : Fragment() {

    private val CHANNEL_ID = "timer_channel_id"

    private var timer: CountDownTimer? = null
    lateinit var textViewTimer: TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonStop: Button
    private lateinit var buttonSave: Button
    lateinit var pickerHours: NumberPicker
    lateinit var pickerMinutes: NumberPicker
    lateinit var pickerSeconds: NumberPicker
    private lateinit var quickTimersContainer: LinearLayout

    val savedTimers = mutableListOf<Long>()
    var remainingTimeInMillis: Long = 0
    private var isPaused = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createNotificationChannel()

        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        textViewTimer = view.findViewById(R.id.textView_timer)
        buttonStart = view.findViewById(R.id.button_start_timer)
        buttonPause = view.findViewById(R.id.button_pause_timer)
        buttonStop = view.findViewById(R.id.button_stop_timer)
        buttonSave = view.findViewById(R.id.button_save_timer)
        pickerHours = view.findViewById(R.id.number_picker_hours)
        pickerMinutes = view.findViewById(R.id.number_picker_minutes)
        pickerSeconds = view.findViewById(R.id.number_picker_seconds)
        quickTimersContainer = view.findViewById(R.id.quick_timers_container)

        setupNumberPickers()
        setupButtons()

        return view
    }

    private fun setupNumberPickers() {
        pickerHours.minValue = 0
        pickerHours.maxValue = 23
        pickerMinutes.minValue = 0
        pickerMinutes.maxValue = 59
        pickerSeconds.minValue = 0
        pickerSeconds.maxValue = 59
    }

    private fun setupButtons() {
        buttonStart.setOnClickListener {
            if (isPaused) {
                startTimer(remainingTimeInMillis)
                isPaused = false
            } else {
                val hours = pickerHours.value
                val minutes = pickerMinutes.value
                val seconds = pickerSeconds.value
                val totalTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000L
                startTimer(totalTimeInMillis)
            }
        }

        buttonPause.setOnClickListener {
            pauseTimer()
        }

        buttonStop.setOnClickListener {
            stopTimer()
        }

        buttonSave.setOnClickListener {
            saveTimer()
        }
    }

    fun startTimer(durationInMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(durationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / 1000) / 60 % 60
                val hours = (millisUntilFinished / 1000) / 3600
                textViewTimer.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
            }

            override fun onFinish() {
                textViewTimer.text = getString(R.string.default_timer)
                sendTimerNotification()
            }
        }.start()
    }

    fun pauseTimer() {
        timer?.cancel()
        isPaused = true
    }

    fun stopTimer() {
        timer?.cancel()
        remainingTimeInMillis = 0
        isPaused = false
        textViewTimer.text = getString(R.string.default_timer)
    }

    fun saveTimer() {
        val hours = pickerHours.value
        val minutes = pickerMinutes.value
        val seconds = pickerSeconds.value
        val totalTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000L

        if (savedTimers.size < 4) {
            savedTimers.add(totalTimeInMillis)
            addQuickTimerButton(totalTimeInMillis)
        }
    }

    fun addQuickTimerButton(timeInMillis: Long) {
        val button = Button(requireContext())
        val seconds = (timeInMillis / 1000) % 60
        val minutes = (timeInMillis / 1000) / 60 % 60
        val hours = (timeInMillis / 1000) / 3600

        button.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
        button.setOnClickListener {
            setTimerFromQuickAccess(timeInMillis)
        }

        button.setOnLongClickListener {
            showDeleteDialog(button, timeInMillis)
            true
        }

        quickTimersContainer.addView(button)
    }

    fun setTimerFromQuickAccess(timeInMillis: Long) {
        val seconds = (timeInMillis / 1000) % 60
        val minutes = (timeInMillis / 1000) / 60 % 60
        val hours = (timeInMillis / 1000) / 3600

        pickerHours.value = hours.toInt()
        pickerMinutes.value = minutes.toInt()
        pickerSeconds.value = seconds.toInt()
    }

    private fun showDeleteDialog(button: Button, timeInMillis: Long) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete timer")
            .setMessage("Are you sure that you want to delete this timer?")
            .setPositiveButton("Delete") { _, _ ->
                quickTimersContainer.removeView(button)
                savedTimers.remove(timeInMillis)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun createNotificationChannel() {
        val name = "Timer Notifications"
        val descriptionText = "Уведомления по таймеру"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun sendTimerNotification() {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val vibrationPattern = longArrayOf(0, 500, 1000, 500)

        checkAndRequestNotificationPermission()

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.housekeeper_icon)
            .setContentTitle("Таймер завершён")
            .setContentText("Ваш таймер подошёл к концу.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setVibrate(vibrationPattern)

        NotificationManagerCompat.from(requireContext()).notify(1, builder.build())
    }

    fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }

    companion object {
        fun newInstance(): TimerFragment {
            return TimerFragment()
        }
    }
}
