package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.myapplication.ReminderManager.createNotificationChannel
import com.example.myapplication.ui.util.UIUtils
import org.acra.config.dialog
import org.acra.config.mailSender
import org.acra.ktx.initAcra
import org.acra.mail.BuildConfig

class Main : Application() {
    override fun onCreate() {
        super.onCreate()

        PreferenceManager.setDefaultValues(this, R.xml.preferences, true)
        Prefs.getStoredRemindersListFormatVersion(this) // Initialize if not set
        createNotificationChannel(this)

        // Reschedule reminders and show due reminders on app startup. This ensures that reminders are scheduled and re-shown
        // automatically after reboot (if this is enabled in settings) and when starting the app again after a force-close which cancels
        // AlarmManager alarms and notifications.
        // This might also be called in situations where it is not necessary, for example after the system or user killed the app process
        // without cancelling notifications and alarms. However, there is no handy way of detecting whether this is the case.
        ReminderManager.scheduleAndReshowAllReminders(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        initAcra {
            buildConfigClass = BuildConfig::class.java
            mailSender {
                mailTo = "ZVika_2004@mail.ru"
                reportAsFile = false // The report being the content of the mail makes it more obvious to the user what data is sent.
            }
            dialog {
                title = getString(R.string.acra_title)
                text = getString(R.string.acra_prompt)
                commentPrompt = getString(R.string.acra_comment_prompt)
            }
        }
    }

    companion object {

        @JvmField
        var REMINDERS_LIST_FORMAT_VERSION = 1

        @JvmStatic
        fun showWelcomeMessage(context: Context) {
            UIUtils.showMessageDialog(R.string.dialog_welcome_title, R.string.welcome_message, context)
        }

        @JvmStatic
        fun showWelcomeMessageUpdate(context: Context) {
            UIUtils.showMessageDialog(R.string.dialog_welcome_title, R.string.welcome_message_update, context)
        }
    }
}