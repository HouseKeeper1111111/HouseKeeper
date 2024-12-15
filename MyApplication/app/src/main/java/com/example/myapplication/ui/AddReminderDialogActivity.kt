package com.example.myapplication.ui

import android.os.Bundle
import com.example.myapplication.Prefs
import com.example.myapplication.R
import com.example.myapplication.ReminderManager.addReminder

/**
 * Shows a dialog allowing to add a reminder. Finishes with [.RESULT_OK] if the reminder has been added.
 */
class AddReminderDialogActivity : ReminderDialogActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.add_reminder_title)
    }

    override fun onDone() {
        val reminder = addReminder(this, buildReminderWithTimeTextNagging())
        makeToast(reminder)
        completeActivity()
        Prefs.setAddReminderDialogUsed(this)
    }
}
