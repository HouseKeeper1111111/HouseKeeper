package com.example.myapplication.ui.reminderslist

import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.ui.reminderslist.ReminderViewHolder

/**
 * View holder for items where only time is to be shown
 */
class TimeOnlyReminderViewHolder(itemView: CardView) :
    ReminderViewHolder(R.layout.reminder_card_datefield_time_only, itemView)