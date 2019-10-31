package tk.lorddarthart.weathertest.util.extension

import android.util.Patterns
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