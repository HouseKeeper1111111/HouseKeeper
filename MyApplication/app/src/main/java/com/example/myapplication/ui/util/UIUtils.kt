package com.example.myapplication.ui.util

import android.content.Context
import android.content.DialogInterface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.myapplication.R

object UIUtils {
    fun showMessageDialog(@StringRes resTitle: Int, @StringRes resMessage: Int, context: Context, onDismiss : DialogInterface.OnDismissListener? = null) {
        AlertDialog.Builder(context)
            .setPositiveButton(android.R.string.ok) { dialog: DialogInterface, _ -> dialog.dismiss() }
            .setTitle(resTitle)
            .setMessage(resMessage)
            .setOnDismissListener(onDismiss)
            .show()
    }

    fun makeAlertText(source: CharSequence?, context: Context): SpannableString {
        val color = ContextCompat.getColor(context, R.color.text_alert)
        val spannableString = SpannableString(source)
        spannableString.setSpan(ForegroundColorSpan(color), 0, spannableString.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return spannableString
    }

    fun makeAlertText(@StringRes resId: Int, context: Context): SpannableString = makeAlertText(context.getString(resId), context)
}