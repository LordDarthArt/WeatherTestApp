package tk.lorddarthart.domain.repository.translator

import io.reactivex.Observable

interface TranslatorRepository {
    fun getTranslatedString(textToTranslate: String, preferredLocale: String): Observable<String>
}