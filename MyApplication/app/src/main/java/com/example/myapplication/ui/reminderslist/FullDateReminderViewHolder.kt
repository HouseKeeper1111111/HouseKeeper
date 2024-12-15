package com.example.myapplication.ui.reminderslist

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.util.DateTimeUtil
import java.util.*

/**
 * View holder for items where time and date is to be shown.
 */
class FullDateReminderViewHolder(itemView: View) :
    ReminderViewHolder(R.layout.reminder_card_datefield_full_date, (itemView as CardView)) {
    private val dateView: TextView = itemView.findViewById(R.id.date)
    override fun initializeDateView(date: Date, context: Context) {
        dateView.text = DateTimeUtil.formatDate(context, date)
    }
}