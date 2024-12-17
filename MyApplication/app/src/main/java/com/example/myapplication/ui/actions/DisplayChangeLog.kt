package com.example.myapplication.ui.actions

import android.content.Context
import com.example.myapplication.ui.util.HtmlDialogFragment
import de.cketti.library.changelog.ChangeLog

/**
 * Displays the change log.
 */
class DisplayChangeLog : HtmlDialogFragment.Action {
    override fun getName(): String {
        return "display_changelog"
    }

    override fun run(args: List<String>, context: Context) {
        val changeLog = ChangeLog(context)
        changeLog.fullLogDialog.show()
    }
}