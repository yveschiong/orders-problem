package com.yveschiong.ordersproblem.extensions

import android.content.Context
import android.content.Intent
import com.yveschiong.ordersproblem.activities.BaseActivity

fun Context.launchActivity(clazz: Class<out BaseActivity>) {
    startActivity(Intent(this, clazz))
}