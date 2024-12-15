package com.example.myapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.ReminderManager.updateReminder
import com.example.myapplication.ReminderStorage.ReminderNotFoundException
import com.example.myapplication.ReminderStorage.getReminder
import com.example.myapplication.data.Reminder

/**
 * Shows a dialog allowing to edit a reminder. Finishes with [.RESULT_OK] if the reminder has been edited.
 *
 *
 * Has to be started with the intent provided by [.getIntentEditReminder].
 */
class EditReminderDialogActivity : ReminderDialogActivity() {
    /**
     * The ID of the reminder to be updated.
     */
    private var reminderToUpdate = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.edit_reminder_title)
        setAddButtonText(R.string.edit_reminder_add_button)
        setupActivityWithReminder(intent)
    }

    /**
     * Process a new intent to this activity, replacing the current content with that of the reminder referenced by the new intent.
     *
     * @param intent
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setupActivityWithReminder(intent)
    }

    /**
     * Setup the state and UI of the activity based on the reminder referenced by the given intent.
     *
     * @param intent
     * @throws IllegalArgumentException if the intent has not the required extra
     */
    private fun setupActivityWithReminder(intent: Intent) {
        require(intent.hasExtra(EXTRA_REMINDER_ID)) { "EditReminderDialogActivity received intent without reminder ID extra." }
        val reminderId = intent.getIntExtra(EXTRA_REMINDER_ID, -1)
        try {
            val reminder = getReminder(this, reminderId)
            nameTextView.setText(reminder.text)
            // Move cursor to end of text
            nameTextView.setSelection(nameTextView.length())
            // For scheduled reminders, set the selected time to the due date, otherwise leave it at the current time
            if (reminder.status == Reminder.Status.SCHEDULED) {
                setSelectedDateTimeAndSelectionMode(reminder.calendar)
            }
            naggingSwitch.isChecked = reminder.isNagging
            if (reminder.isNagging) {
                naggingRepeatInterval = reminder.naggingRepeatInterval
            }
            reminderToUpdate = reminderId
        } catch (e: ReminderNotFoundException) {
            Log.w("AddReminder", "Intent contains invalid reminder ID.")
            Toast.makeText(this, R.string.error_msg_reminder_not_found, Toast.LENGTH_LONG).show()
            completeActivity()
        }
    }

    override fun onDone() {
        val reminderBuilder = buildReminderWithTimeTextNagging()
        reminderBuilder.id = reminderToUpdate
        val reminder = reminderBuilder.build()
        updateReminder(this, reminder, true)
        makeToast(reminder)
        completeActivity()
    }

    companion object {
        private const val EXTRA_REMINDER_ID = "com.example.myapplication.ui.AddReminderDialogActivity.extra.ID"
        fun getIntentEditReminder(context: Context?, reminderId: Int): Intent {
            return Intent(context, EditReminderDialogActivity::class.java)
                .putExtra(EXTRA_REMINDER_ID, reminderId)
        }
    }
}
