package tk.lorddarthart.domain.translator

import io.reactivex.Observable

interface TranslatorRepository {
    fun getTranslatedString(textToTranslate: String, preferredLocale: String): Observable<String>
}