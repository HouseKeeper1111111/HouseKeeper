package com.example.myapplication.ui.actions;

import android.content.Context;

import com.example.myapplication.ui.util.HtmlDialogFragment;

import java.util.List;

import de.cketti.library.changelog.ChangeLog;

/**
 * Displays the change log.
 */
public class DisplayChangeLog implements HtmlDialogFragment.Action {
    @Override
    public String getName() {
        return "display_changelog";
    }

    @Override
    public void run(List<String> args, Context context) {
        ChangeLog changeLog = new ChangeLog(context);
        changeLog.getFullLogDialog().show();
    }
}
