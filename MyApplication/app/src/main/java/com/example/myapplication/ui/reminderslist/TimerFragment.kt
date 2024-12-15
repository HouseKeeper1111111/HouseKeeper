package com.example.myapplication.ui.reminderslist

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.util.Locale

class TimerFragment : Fragment() {

    private var timer: CountDownTimer? = null
    private lateinit var textViewTimer: TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonStop: Button
    private lateinit var pickerHours: NumberPicker
    private lateinit var pickerMinutes: NumberPicker
    private lateinit var pickerSeconds: NumberPicker

    private var remainingTimeInMillis: Long = 0
    private var isPaused = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        textViewTimer = view.findViewById(R.id.textView_timer)
        buttonStart = view.findViewById(R.id.button_start_timer)
        buttonPause = view.findViewById(R.id.button_pause_timer)
        buttonStop = view.findViewById(R.id.button_stop_timer)
        pickerHours = view.findViewById(R.id.number_picker_hours)
        pickerMinutes = view.findViewById(R.id.number_picker_minutes)
        pickerSeconds = view.findViewById(R.id.number_picker_seconds)

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
    }

    private fun startTimer(durationInMillis: Long) {
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
            }
        }.start()
    }

    private fun pauseTimer() {
        timer?.cancel()
        isPaused = true
    }

    private fun stopTimer() {
        timer?.cancel()
        remainingTimeInMillis = 0
        isPaused = false
        textViewTimer.text = "00:00:00"
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
