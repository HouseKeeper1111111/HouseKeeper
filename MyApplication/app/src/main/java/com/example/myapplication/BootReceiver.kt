package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class BootReceiver : BroadcastReceiver() {

    /**
     * This receiver does not do anything, but the corresponding broadcasts are received to execute code in [Main.onCreate],
     * which is executed on every startup of the application.
     */
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
    }

    companion object {
        @JvmStatic
        fun isPermissionGranted(applicationContext: Context) =
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.RECEIVE_BOOT_COMPLETED
            ) == PackageManager.PERMISSION_GRANTED

        @JvmStatic
        fun setBootReceiverEnabled(context: Context, enabled: Boolean) {
            val receiver = ComponentName(context, BootReceiver::class.java)
            val newState: Int = if (enabled) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            context.packageManager.setComponentEnabledSetting(receiver, newState, PackageManager.DONT_KILL_APP)
        }
    }
}