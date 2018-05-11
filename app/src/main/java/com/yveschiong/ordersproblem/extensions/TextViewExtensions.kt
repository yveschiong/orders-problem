package com.yveschiong.ordersproblem.extensions

import android.content.Context
import android.widget.TextView

fun TextView.setFormattedText(context: Context, id: Int, vararg args: Any?) {
    text = String.format(context.getString(id), args)
}