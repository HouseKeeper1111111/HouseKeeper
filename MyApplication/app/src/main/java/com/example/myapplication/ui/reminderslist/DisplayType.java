package com.example.myapplication.ui.reminderslist;

/**
 * Ways of displaying the date of a reminder item.
 */
public enum DisplayType {
    /**
     * Display only hour and minute.
     */
    TIME_ONLY,
    /**
     * Display day, month, year, hour and minute.
     */
    FULL,
    /**
     * Like {@link #TIME_ONLY} if the date is the current day, like {@link #FULL} otherwise.
     */
    TIME_ONLY_IF_TODAY
}
