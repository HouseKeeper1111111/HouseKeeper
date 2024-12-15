package com.example.myapplication.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.R

class NotificationSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_notifications, rootKey)
    }
}