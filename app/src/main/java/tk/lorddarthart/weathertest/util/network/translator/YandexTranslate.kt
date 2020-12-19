package tk.lorddarthart.weathertest.util.network.translator

import android.os.Build
import org.json.JSONException
import org.json.JSONObject
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.WeatherTestApp
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class YandexTranslate : TranslatorNetwork {
    private val YANDEX_API_KEY = "trnsl.1.1.20190605T100208Z.23cac5092dc349e5.105d609ed126f004c10e3733486a26ae606c1e66"

    override fun translateToLocale(text: String): String {
        val urlString = translateUrl(text)

        val obj = URL(urlString)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = "GET"

        con.setRequestProperty("connection",
                WeatherTestApp.instance.getString(R.string.requestPropertyConnection)
        )
        con.setRequestProperty("content-type",
                WeatherTestApp.instance.getString(R.string.requestPropertyContentType)

        )
        con.connectTimeout = 5000
        con.readTimeout = 5000

        val responseCode = con.responseCode

        return if (responseCode != 401 && responseCode != 403) {
            val inputStream = con.inputStream
            val stringResponse = inputStreamToString(inputStream)

            readTranslation(stringResponse)
        } else {
            "ERROR"
        }
    }

    override fun translateUrl(text: String): String {
        return "https://translate.yandex.net/api/v1.5/tr.json/translate?key=$YANDEX_API_KEY&text=$text&lang=${getCurrentLocale().language}"
    }

    override fun getCurrentLocale(): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WeatherTestApp.instance.resources.configuration.locales.get(0)
        } else {
            WeatherTestApp.instance.resources.configuration.locale
        }
    }

    @Throws(IOException::class)
    override fun inputStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(inputStream.reader())
        val content = StringBuilder()
        reader.use { inputStreamReader ->
            var line = inputStreamReader.readLine()
            while (line != null) {
                content.append(line)
                line = inputStreamReader.readLine()
            }
        }
        return content.toString()
    }

    @Throws(JSONException::class)
    override fun readTranslation(stringResponse: String): String {
        val translationsArray = JSONObject(stringResponse).getJSONArray("text")
        return JSONObject(stringResponse).getJSONArray("text")
                .get(translationsArray.length() - 1) as String
    }
}