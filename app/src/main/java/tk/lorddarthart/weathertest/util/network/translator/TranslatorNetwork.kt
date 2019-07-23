package tk.lorddarthart.weathertest.util.network.translator

import android.content.Context
import java.io.InputStream
import java.util.*

interface TranslatorNetwork {
    /**
     * Main translation method
     *
     * @param text Text for translation
     */
    fun translateToLocale(text: String): String

    /**
     * Getting formed url
     *
     * @param text Text for translation
     */
    fun translateUrl(text: String): String

    /**
     * Getting current system locale
     */
    fun getCurrentLocale(): Locale

    /**
     * Converting inputStream to string
     *
     * @param inputStream Received inputStream
     */
    fun inputStreamToString(inputStream: InputStream): String

    /**
     * Getting translation from JSON
     *
     * @param stringResponse Getting the translation out of JSON
     */
    fun readTranslation(stringResponse: String): String
}