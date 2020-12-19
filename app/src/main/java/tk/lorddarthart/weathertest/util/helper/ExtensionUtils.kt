package tk.lorddarthart.weathertest.util.helper

import android.util.Log
import tk.lorddarthart.weathertest.BuildConfig

/**
 * Created by LordDarthArt at 23.10.2019
 */
inline fun <reified T: Any> T.logDebug(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(this::class.java.simpleName, message)
    }
}

inline fun <reified T: Any> T.logInfo(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i(this::class.java.simpleName, message)
    }
}

inline fun <reified T: Any> T.logVerbose(message: String) {
    if (BuildConfig.DEBUG) {
        Log.v(this::class.java.simpleName, message)
    }
}

inline fun <reified T: Any> T.logWarn(message: String) {
    if (BuildConfig.DEBUG) {
        Log.w(this::class.java.simpleName, message)
    }
}

inline fun <reified T: Any> T.logError(message: String, throwable: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) {
            Log.e(this::class.java.simpleName, message, throwable)
        } else {
            Log.e(this::class.java.simpleName, message)
        }
    }
}

inline fun <reified T: Any> T.logAssert(message: String, throwable: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        if (throwable != null) {
            Log.wtf(this::class.java.simpleName, message, throwable)
        } else {
            Log.wtf(this::class.java.simpleName, message)
        }
    }
}

fun Any.tag(): String {
    return this::class.java.simpleName
}