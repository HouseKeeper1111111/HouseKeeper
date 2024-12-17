package com.example.myapplication.ui.actions;

import android.content.Context;

import com.example.myapplication.ui.util.HtmlDialogFragment;

import java.util.List;

import com.example.myapplication.Main;

/**
 * Displays the general welcome message.
 */
public class DisplayWelcomeMessage implements HtmlDialogFragment.Action {
    @Override
    public String getName() {
        return "display_welcome_message";
    }

    @Override
    public void run(List<String> args, Context context) {
        Main.showWelcomeMessage(context);
    }
}
