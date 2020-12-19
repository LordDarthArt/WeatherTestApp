package tk.lorddarthart.weathertest.app.logic.translator

import io.reactivex.schedulers.Schedulers
import tk.lorddarthart.weathertest.util.network.translator.TranslatorApi

class TranslatorRepositoryImpl(private val translatorApi: TranslatorApi): TranslatorRepository {
    override fun getTranslatedString(textToTranslate: String, preferredLocale: String) =
        translatorApi.translate(textToTranslate, preferredLocale).subscribeOn(Schedulers.io())
            .map { it }
            .onErrorReturn { textToTranslate }
}