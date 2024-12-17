package com.example.myapplication.ui.actions;

import android.content.Context;

import com.example.myapplication.ui.util.HtmlDialogFragment;

import java.util.List;

import com.example.myapplication.Main;

/**
 * Displays the update welcome message.
 */
public class DisplayWelcomeMessageUpdate implements HtmlDialogFragment.Action {
    @Override
    public String getName() {
        return "display_welcome_message_update";
    }

    @Override
    public void run(List<String> args, Context context) {
        Main.showWelcomeMessageUpdate(context);
    }
}
