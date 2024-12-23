package com.example.myapplication.ui.reminderslist

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.AlarmManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Prefs
import com.example.myapplication.R
import com.example.myapplication.ui.actions.DisplayChangeLog
import com.example.myapplication.ui.util.HtmlDialogFragment
import com.example.myapplication.util.ImplementationError
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.cketti.library.changelog.ChangeLog
import com.example.myapplication.BootReceiver
import com.example.myapplication.ui.AddReminderDialogActivity
import com.example.myapplication.ui.SettingsActivity
import com.example.myapplication.ui.util.UIUtils

class RemindersListActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders_list)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.container)
        viewPager.adapter = ViewPagerAdapter()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val context = this@RemindersListActivity
            val linearLayout = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                setPadding(8, 8, 8, 8)
            }

            val imageView = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(80, 80).apply {
                    marginEnd = 8
                }
                setImageResource(
                    when (position) {
                        0 -> R.drawable.icon_reminder
                        1 -> R.drawable.icon_timer
                        else -> throw ImplementationError("Invalid tab number $position")
                    }
                )
            }

            val textView = TextView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = when (position) {
                    0 -> getString(R.string.tab_reminders)
                    1 -> getString(R.string.tab_timers)
                    else -> throw ImplementationError("Invalid tab number $position")
                }
                textSize = 16f
            }

            linearLayout.addView(imageView)
            linearLayout.addView(textView)
            tab.customView = linearLayout
        }.attach()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            if (!Prefs.isAddReminderDialogUsed(this@RemindersListActivity)) {
                Toast.makeText(
                    this@RemindersListActivity,
                    R.string.toast_info_add_reminder_dialog,
                    Toast.LENGTH_LONG
                ).show()
            }
            startActivity(Intent(this, AddReminderDialogActivity::class.java))
        }
        showStartupDialogs()
    }

    /**
     * Show dialogs with messages or question to be shown to the user on startup.
     */
    private fun showStartupDialogs() {
        val showGeneralWelcomeMessage = !Prefs.checkAndUpdateWelcomeMessageShown(this)

        val changeLog = ChangeLog(this)
        val isFirstRunOfVersion = changeLog.isFirstRun
        if (isFirstRunOfVersion) {
            changeLog.logDialog.show()
            Prefs.resetAllDontShowAgain(this)
        }

        // Check whether have permission to schedule exact alarms (needed for APIs 31-32),
        // as user can revoke the permission even if it is pre-granted.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            checkScheduleExactPermission()
        }

        // Depending on platform version, check whether battery optimization is disabled and show dialog to disable it otherwise.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkBatteryOptimization()
        }

        // Check whether run on boot is enabled and whether should ask user to enable it.
        checkRunOnBoot()

        // Check whether the notification permission is granted and request it if not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkNotificationPermission()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_reminders_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }

            R.id.action_help -> {
                HtmlDialogFragment.displayHtmlDialogFragment(
                    supportFragmentManager,
                    R.string.menu_entry_help,
                    R.raw.help
                )
            }

            R.id.action_about -> {
                val title = getString(R.string.app_name) + " 1.0.0"
                HtmlDialogFragment.displayHtmlDialogFragment(
                    supportFragmentManager,title, R.raw.about,
                    DisplayChangeLog::class.java
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(33)
    private fun checkNotificationPermission() {
        if (checkSelfPermission(POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Note: shouldShowRequestPermissionRationale(POST_NOTIFICATIONS) is not used because we always want to show our message
            UIUtils.showMessageDialog(
                R.string.dialog_startup_show_notifications_title,
                R.string.dialog_startup_show_notifications_message,
                this
            )
            { requestPermissions(arrayOf(POST_NOTIFICATIONS), 0) }
        }
    }

    /**
     * Checks whether run on boot setting is enabled. If not, asks to enable it.
     * If yes, checks whether permission is granted and warns and disables setting if this is not the case.
     */
    private fun checkRunOnBoot() {
        if (Prefs.isRunOnBootDontShowAgain(this))
            return

        if (Prefs.isRunOnBoot(this)) {
            if (!BootReceiver.isPermissionGranted(applicationContext)) {
                Prefs.setRunOnBoot(this, false)
                Toast.makeText(this, R.string.toast_run_on_boot_revoked_therefore_disabled, Toast.LENGTH_LONG).show()
            }
        } else {
            AlertDialog.Builder(this)
                .setTitle(R.string.dialog_startup_run_on_boot_title)
                .setMessage(R.string.dialog_startup_run_on_boot_message)
                .setPositiveButton(R.string.dialog_startup_run_on_boot_enable) { _: DialogInterface?, _: Int ->
                    Prefs.enableRunOnBoot(
                        this,
                        this
                    )
                }
                .setNeutralButton(R.string.dialog_startup_later, null)
                .setNegativeButton(R.string.dialog_startup_dont_show_again) { _: DialogInterface?, _: Int ->
                    Prefs.setRunOnBootDontShowAgain(
                        this
                    )
                }
                .show()
        }
    }

    /**
     * On Android 12 and 12L, check whether [AlarmManager.canScheduleExactAlarms]. Before and after
     * these versions, this is not needed (from API 33+ have USE_EXACT_ALARM (non-revocable)).
     */
    @RequiresApi(api = Build.VERSION_CODES.S)
    private fun checkScheduleExactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            || Prefs.isScheduleExactPermissionDontShowAgain(this)
            || (getSystemService(Context.ALARM_SERVICE) as AlarmManager).canScheduleExactAlarms()
        ) return

        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_startup_schedule_exact_title)
            .setMessage(R.string.dialog_startup_schedule_exact_message)
            .setPositiveButton(
                R.string.dialog_startup_grant_permission
            ) { _: DialogInterface?, _: Int ->
                startActivity(Prefs.getIntentScheduleExactSettings(this))
            }
            .setNeutralButton(R.string.dialog_startup_later, null)
            .setNegativeButton(
                R.string.dialog_startup_dont_show_again
            ) { _: DialogInterface?, _: Int -> Prefs.setScheduleExactPermissionDontShowAgain(this) }
            .show()
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun checkBatteryOptimization() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU // Here we don't want to ask explicitly for it anymore
            || Prefs.isBatteryOptimizationDontShowAgain(this)
            || Prefs.isIgnoringBatteryOptimization(this)
        ) return

        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_startup_disable_battery_optimization_title)
            .setMessage(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                    R.string.dialog_startup_disable_battery_optimization_message_API31
                else R.string.dialog_startup_disable_battery_optimization_message
            ).setPositiveButton(
                R.string.dialog_startup_disable_battery_optimization_turn_off
            ) { _: DialogInterface?, _: Int ->
                startActivity(
                    Prefs.getIntentDisableBatteryOptimization(this)
                )
            }
            .setNeutralButton(R.string.dialog_startup_later, null)
            .setNegativeButton(
                R.string.dialog_startup_dont_show_again
            ) { _: DialogInterface?, _: Int -> Prefs.setBatteryOptimizationDontShowAgain(this) }
            .show()
    }

    inner class ViewPagerAdapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> RemindersListFragment.newInstance()
                1 -> TimerFragment.newInstance()
                else -> throw ImplementationError("Invalid tab number $position")
            }
        }
    }
}