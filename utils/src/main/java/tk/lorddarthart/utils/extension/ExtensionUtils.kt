package tk.lorddarthart.utils.extension

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import org.jetbrains.anko.contentView
import java.util.regex.Pattern

fun String.isThisEmail(): Boolean {
    val mailPattern = Patterns.EMAIL_ADDRESS
    val mailMatcher = mailPattern.matcher(this)

    val foundEmails = mutableListOf<String>()
    while (mailMatcher.find()) {
        foundEmails.add(this.substring(mailMatcher.start(), mailMatcher.end()))
    }

    val isItSingleEmail = foundEmails.size == 1

    if (isItSingleEmail) {
        return this == foundEmails[0]
    }

    return false
}

fun String.isThisPassword(): Boolean {
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher = pattern.matcher(this)

    return matcher.matches()
}

fun View.setVisibility(visibility: () -> Boolean) {
    this.isVisible = visibility.invoke()
}


/**
 * Created by LordDarthArt at 26.10.2019
 * Sometimes software keyboard not showing automatically, and we must close it programmatically.
 * This fun's target is to do such a thing.
 */
fun Activity.showSoftKeyboard() {
    try {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(contentView?.windowToken, InputMethodManager.SHOW_FORCED, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Created by LordDarthArt at 26.10.2019
 * Sometimes software keyboard not hiding automatically, and we must close it programmatically.
 * This fun's target is to do such a thing.
 */
fun Activity.hideSoftKeyboard() {
    try {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(contentView?.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}