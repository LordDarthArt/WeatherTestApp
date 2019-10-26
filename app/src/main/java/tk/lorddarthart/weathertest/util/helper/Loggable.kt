package tk.lorddarthart.weathertest.util.helper

import android.util.Log
import tk.lorddarthart.weathertest.BuildConfig

/**
 * Created by LordDarthArt at 23.01.2019
 */
fun logDebug(thing: Any, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(getCurrentTag(thing), message)
    }
}

fun logInfo(thing: Any, message: String) {
    if (BuildConfig.DEBUG) {
        Log.i(getCurrentTag(thing), message)
    }
}

fun logVerbose(thing: Any, message: String) {
    if (BuildConfig.DEBUG) {
        Log.v(getCurrentTag(thing), message)
    }
}

fun logWarn(thing: Any, message: String) {
    if (BuildConfig.DEBUG) {
        Log.w(getCurrentTag(thing), message)
    }
}

fun logError(thing: Any, message: String, throwable: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) {
            Log.e(getCurrentTag(thing), message, throwable)
        } else {
            Log.e(getCurrentTag(thing), message)
        }
    }
}

fun logAssert(thing: Any, message: String, throwable: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) {
            Log.wtf(getCurrentTag(thing), message, throwable)
        } else {
            Log.wtf(getCurrentTag(thing), message)
        }
    }
}