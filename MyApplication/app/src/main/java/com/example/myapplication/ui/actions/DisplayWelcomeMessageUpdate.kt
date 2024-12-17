package com.example.myapplication.ui.actions

import android.content.Context
import com.example.myapplication.Main.Companion.showWelcomeMessageUpdate
import com.example.myapplication.ui.util.HtmlDialogFragment

/**
 * Displays the update welcome message.
 */
class DisplayWelcomeMessageUpdate : HtmlDialogFragment.Action {
    override fun getName(): String {
        return "display_welcome_message_update"
    }

    override fun run(args: List<String>, context: Context) {
        showWelcomeMessageUpdate(context)
    }
}