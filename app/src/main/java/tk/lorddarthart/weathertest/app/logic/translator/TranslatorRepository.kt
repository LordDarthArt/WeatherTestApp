package tk.lorddarthart.weathertest.app.logic.translator

import io.reactivex.Observable

interface TranslatorRepository {
    fun getTranslatedString(textToTranslate: String, preferredLocale: String): Observable<String>
}