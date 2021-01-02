package tk.lorddarthart.domain.translator

import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.domain.network.translator.TranslatorApi

class TranslatorRepositoryImpl(private val translatorApi: TranslatorApi): TranslatorRepository {
    override fun getTranslatedString(textToTranslate: String, preferredLocale: String) =
        translatorApi.translate(textToTranslate, preferredLocale).subscribeOn(Schedulers.io())
            .map { it }
            .onErrorReturn { textToTranslate }
}