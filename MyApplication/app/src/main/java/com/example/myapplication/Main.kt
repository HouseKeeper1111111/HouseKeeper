package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.myapplication.ReminderManager.createNotificationChannel
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

        ReminderManager.scheduleAndReshowAllReminders(this)
    }
    

    // crash reports
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        initAcra {
            buildConfigClass = BuildConfig::class.java
            mailSender {
                mailTo = "ZVika_2004@mail.ru" // developer email ^_^ (maybe later change it to organization email?)
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
    }
}