package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // All actions should with a reasonable storage size be fast enough to execute in the allowed 10 seconds for this method.
        ReminderManager.processReminderAction(context, intent)
    }
}