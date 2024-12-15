package com.example.myapplication.util

import android.view.View

class OneTimeClickListener(private val block: () -> Unit) : View.OnClickListener {
    private var clicked = false

    override fun onClick(view: View) {
        if (clicked) {
            return
        }
        clicked = true

        block()
    }

}

fun View.setOneTimeClickListener(block: () -> Unit) {
    setOnClickListener(OneTimeClickListener(block))
}
