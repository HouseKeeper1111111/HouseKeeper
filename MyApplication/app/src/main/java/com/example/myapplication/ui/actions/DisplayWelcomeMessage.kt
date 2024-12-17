package com.example.myapplication.ui.actions

import android.content.Context
import com.example.myapplication.Main.Companion.showWelcomeMessage
import com.example.myapplication.ui.util.HtmlDialogFragment

/**
 * Displays the general welcome message.
 */
class DisplayWelcomeMessage : HtmlDialogFragment.Action {
    override fun getName(): String {
        return "display_welcome_message"
    }

    override fun run(args: List<String>, context: Context) {
        showWelcomeMessage(context)
    }
}