package com.example.myapplication.ui.reminderslist

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewStub
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.util.*

/**
 * A base item view holder for reminders lists.
 */
open class ReminderViewHolder(@LayoutRes datefieldRes: Int, itemView: CardView) :
    RecyclerView.ViewHolder(itemView) {
    private val itemCardView: CardView = itemView
    private val cardBackgroundColor: ColorStateList = itemView.cardBackgroundColor
    val descriptionView: TextView = itemView.findViewById(R.id.description)
    val datefieldView: View
    val timeView: TextView

    init {
        val datefieldViewStub = itemView.findViewById<ViewStub>(R.id.datefield_stub)
        datefieldViewStub.layoutResource = datefieldRes
        datefieldView = datefieldViewStub.inflate()
        timeView = itemView.findViewById(R.id.time) // first present after above inflation
    }

    fun isSelected(): Boolean = itemView.isSelected

    fun setSelected(context: Context) {
        itemCardView.isSelected = true
        itemCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_selected))
    }

    fun setUnselected() {
        if (isSelected()) {
            itemCardView.isSelected = false
            itemCardView.setCardBackgroundColor(cardBackgroundColor)
        }
    }


    /**
     * If the view holder has a date view, this method should initialize it with the given parameters.
     */
    open fun initializeDateView(date: Date, context: Context) = Unit
}