package com.example.myapplication.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.myapplication.Prefs
import com.example.myapplication.R

class UISettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_ui, rootKey)

        // The toggle button does not exist before Android 8.0, so remove the corresponding preference
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            findPreference<Preference>(getString(R.string.prefkey_reminder_dialog_timepicker_show_keyboard_button))?.apply {
                parent?.removePreference(this)
            }
        }

        // Automatic closing of keyboard when using clock does not work on Android 5, so remove the corresponding preference
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            findPreference<Preference>(getString(R.string.prefkey_reminder_dialog_close_keyboard_on_timepicker_use))?.apply {
                parent?.removePreference(this)
            }
        }

        val timePickerHeightPref = findPreference<EditTextPreference>(getString(R.string.prefkey_reminder_dialog_timepicker_height))!!
        val timePickerTextSizePref = findPreference<EditTextPreference>(getString(R.string.prefkey_reminder_dialog_timepicker_text_size))!!
        val customizeSizePref = findPreference<SwitchPreferenceCompat>(getString(R.string.prefkey_reminder_dialog_timepicker_customize_size))!!

        customizeSizePref.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _: Preference?, newValue: Any ->
                newValue as Boolean
                timePickerHeightPref.isEnabled = newValue
                timePickerTextSizePref.isEnabled = newValue
                return@OnPreferenceChangeListener true
            }

        with(timePickerHeightPref) {
            isEnabled = customizeSizePref.isChecked
            setOnBindEditTextListener { editText: EditText ->
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }
            summaryProvider = Preference.SummaryProvider { _: Preference? ->
                "${Prefs.getReminderDialogTimePickerHeight(context)} ${getString(R.string.preference_reminder_dialog_timepicker_sizes_recommended,
                    Prefs.Defaults.REMINDER_DIALOG_TIMEPICKER_HEIGHT.toString())}"
            }
            // Validation
            onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _: Preference?, newValue: Any ->
                    try {
                        val i = newValue.toString().toInt()
                        if (i > 0) {
                            startAddReminderActivity()
                            return@OnPreferenceChangeListener true
                        }
                    } catch (ex: NumberFormatException) {
                        // Incorrect format, handled below
                    }
                    Toast.makeText(context, R.string.preference_reminder_dialog_timepicker_height_format_error, Toast.LENGTH_LONG).show()
                    false
                }
        }

        with(timePickerTextSizePref) {
            isEnabled = customizeSizePref.isChecked
            setOnBindEditTextListener { editText: EditText ->
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }
            summaryProvider = Preference.SummaryProvider { _: Preference? ->
                "${Prefs.getReminderDialogTimePickerTextSize(context)} ${getString(R.string.preference_reminder_dialog_timepicker_sizes_recommended,
                    Prefs.Defaults.REMINDER_DIALOG_TIMEPICKER_TEXTSIZE.toString())}"
            }
            // Validation
            onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _: Preference?, newValue: Any ->
                    try {
                        val i = newValue.toString().toInt()
                        if (i > 0) {
                            startAddReminderActivity()
                            return@OnPreferenceChangeListener true
                        }
                    } catch (ex: NumberFormatException) {
                        // Incorrect format, handled below
                    }
                    Toast.makeText(context, R.string.preference_reminder_dialog_timepicker_text_size_format_error, Toast.LENGTH_LONG)
                        .show()
                    false
                }
        }

        findPreference<Preference>(getString(R.string.prefkey_reminder_dialog_show))?.onPreferenceClickListener =
            Preference.OnPreferenceClickListener {
                startAddReminderActivity()
                true
            }
    }

    private fun startAddReminderActivity() {
        startActivity(
            Intent(context, AddReminderDialogActivity::class.java)
                .addFlags(
                    // Independently start activity, but if it already exists, navigate to it
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
        )
    }
}