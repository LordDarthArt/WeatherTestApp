package tk.lorddarthart.weathertest

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import org.json.JSONException
import tk.lorddarthart.weathertest.util.constant.Format.SDF_FOR_HTTP
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HttpServiceHelper {

    @Throws(IOException::class, JSONException::class, ParseException::class)
    fun getTasks(mSqLiteDatabase: SQLiteDatabase, city: String): Int {
        val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22$city%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"

        val obj = URL(url)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = "GET"

        con.setRequestProperty("connection", "keep-alive")
        con.setRequestProperty("content-type", "application/octet-stream")
        con.connectTimeout = 10000
        con.readTimeout = 10000

        val responseCode = con.responseCode
        println("\nSending 'GET' request to URL : $url")
        println("Response Code : $responseCode")

        if (responseCode != 401) {
            val inputStream = con.inputStream
            val stringResponse = inputStreamToString(inputStream)

            //JSONArray jsonTasks = new JSONObject(stringResponse).getJSONArray("#value").getJSONObject(0).getJSONObject("Value").getJSONArray("#value");

            val weathers = readWeatherArray(stringResponse, city)
            println(weathers)
            for (weather in weathers) {
                DatabaseHelper.addWeather(mSqLiteDatabase, weather.weather_date, weather.weather_filterName!!, weather.weather_now, weather.weather_city!!,
                        weather.weather_high, weather.weather_low, weather.weather_text!!, weather.weather_description!!, weather.weather_humidity,
                        weather.weather_pressure, weather.weather_rising, weather.weather_visibility, weather.weather_sunrise!!, weather.weather_sunset!!,
                        weather.weather_d1!!, weather.weather_d2!!, weather.weather_d3!!, weather.weather_d4!!, weather.weather_d5!!, weather.weather_d6!!, weather.weather_d7!!)
            }
        }
        return responseCode
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class, JSONException::class, ParseException::class)
    fun readTask(stringResponse: String, filterName: String): Weather {
        val sdf = SimpleDateFormat(SDF_FOR_HTTP)
        sdf.timeZone = TimeZone.getDefault()
//        val weather_date = sdf.parse(JSONObject(stringResponse).getJSONObject("query").get("created").toString()).time
//        val weather_now = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").get("temp") as String)
//        val weather_city = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("location").get("city") as String
//        val weather_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").get("text") as String
//        val weather_description = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").get("description") as String
//        val weather_humidity = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("humidity") as String)
//        val weather_pressure = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("pressure") as String)
//        val weather_rising = java.lang.Long.parseLong(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("rising") as String)
//        val weather_visibility = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("visibility") as String)
//        val weather_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("high") as String)
//        val weather_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("low") as String)
//        val weather_sunrise = SimpleDateFormat("HH:mm").format(SimpleDateFormat("KK:mm a", Locale.US).parse(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy").get("sunrise").toString().toUpperCase()))
//        val weather_sunset = SimpleDateFormat("HH:mm").format(SimpleDateFormat("KK:mm a", Locale.US).parse(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy").get("sunset").toString().toUpperCase()))
//        val weather_d1_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("day") as String
//        val weather_d1_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("high") as String)
//        val weather_d1_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("low") as String)
//        val weather_d1_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("text") as String
//        val weather_d2_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("day") as String
//        val weather_d2_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("high") as String)
//        val weather_d2_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("low") as String)
//        val weather_d2_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("text") as String
//        val weather_d3_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("day") as String
//        val weather_d3_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("high") as String)
//        val weather_d3_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("low") as String)
//        val weather_d3_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("text") as String
//        val weather_d4_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("day") as String
//        val weather_d4_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("high") as String)
//        val weather_d4_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("low") as String)
//        val weather_d4_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("text") as String
//        val weather_d5_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("day") as String
//        val weather_d5_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("high") as String)
//        val weather_d5_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("low") as String)
//        val weather_d5_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("text") as String
//        val weather_d6_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("day") as String
//        val weather_d6_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("high") as String)
//        val weather_d6_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("low") as String)
//        val weather_d6_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("text") as String
//        val weather_d7_day = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("day") as String
//        val weather_d7_high = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("high") as String)
//        val weather_d7_low = java.lang.Double.parseDouble(JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("low") as String)
//        val weather_d7_text = JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("text") as String
//
//        return Weather(weather_date, filterName, weather_now, weather_city, weather_high, weather_low, weather_text, weather_description, weather_humidity, weather_pressure, weather_rising, weather_visibility, weather_sunrise, weather_sunset,
//                weather_d1_day, weather_d1_high, weather_d1_low, weather_d1_text, weather_d2_day, weather_d2_high, weather_d2_low, weather_d2_text, weather_d3_day, weather_d3_high, weather_d3_low, weather_d3_text, weather_d4_day, weather_d4_high,
//                weather_d4_low, weather_d4_text, weather_d5_day, weather_d5_high, weather_d5_low, weather_d5_text, weather_d6_day, weather_d6_high, weather_d6_low, weather_d6_text, weather_d7_day, weather_d7_high, weather_d7_low, weather_d7_text)
        return Weather()
    }

    @Throws(IOException::class)
    private fun inputStreamToString(inputStream: InputStream): String {
        ByteArrayOutputStream().use { result ->
            val buffer = ByteArray(1024)
            while ((inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, inputStream.read(buffer))
            }
            return result.toString("UTF-8")
        }
    }

    @Throws(IOException::class, JSONException::class, ParseException::class)
    fun readWeatherArray(array: String, city: String): List<Weather> {
        val tasks = ArrayList<Weather>()

        tasks.add(readTask(array, city))
        return tasks
    }
}
