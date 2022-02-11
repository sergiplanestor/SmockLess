package com.revolhope.ui.utils

import android.os.Handler
import android.os.Looper

fun looper(isMainThread: Boolean = true): Looper =
    Looper.myLooper()?.takeUnless { isMainThread } ?: Looper.getMainLooper()

fun handler(isMainThread: Boolean = true): Handler =
    Handler(looper(isMainThread))

inline fun post(delay: Long = 0, isMainThread: Boolean = true, crossinline block: () -> Unit) {
    handler(isMainThread).run {
        if (delay > 0) {
            this.post { block() }
        } else {
            postDelayed({ block() }, delay.coerceAtLeast(0))
        }
    }
}