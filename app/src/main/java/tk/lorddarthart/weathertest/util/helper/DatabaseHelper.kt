package tk.lorddarthart.weathertest.util.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DatabaseHelper(
        context: Context,
        name: String,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
) : SQLiteOpenHelper(context, name, factory, version), BaseColumns {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE_WEATHER_SCRIPT)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    companion object {
        const val DATABASE_NAME = "tk.lorddarthart.openweathermap.db"
        var DATABASE_VERSION = 1

        const val DATABASE_WEATHER = "weather"
        const val WEATHER_FILTERNAME = "weather_filterName"
        private const val WEATHER_ID = "weather_id"
        const val WEATHER_NOW = "weather_now"
        const val WEATHER_DATE = "weather_date"
        const val WEATHER_CITY = "weather_city"
        const val WEATHER_HIGH = "weather_high"
        const val WEATHER_LOW = "weather_low"
        const val WEATHER_TEXT = "weather_text"
        const val WEATHER_DESCRIPTION = "weather_description"
        const val WEATHER_HUMIDITY = "weather_humidity"
        const val WEATHER_PRESSURE = "weather_pressure"
        const val WEATHER_RISING = "weather_rising"
        const val WEATHER_VISIBILITY = "weather_visibility"
        const val WEATHER_SUNRISE = "weather_sunrise"
        const val WEATHER_SUNSET = "weather_sunset"
        const val WEATHER_D1 = "weather_d1"
        const val WEATHER_D2 = "weather_d2"
        const val WEATHER_D3 = "weather_d3"
        const val WEATHER_D4 = "weather_d4"
        const val WEATHER_D5 = "weather_d5"
        const val WEATHER_D6 = "weather_d6"
        const val WEATHER_D7 = "weather_d7"

        const val DATABASE_CREATE_WEATHER_SCRIPT = ("create table "
                + DATABASE_WEATHER
                + " (" + WEATHER_ID + " integer not null primary key autoincrement, "
                + WEATHER_FILTERNAME + " text not null, "
                + WEATHER_DATE + " long not null, "
                + WEATHER_CITY + " text not null, "
                + WEATHER_NOW + " double not null, "
                + WEATHER_HIGH + " double not null, "
                + WEATHER_LOW + " double not null, "
                + WEATHER_TEXT + " text not null, "
                + WEATHER_DESCRIPTION + " text not null, "
                + WEATHER_HUMIDITY + " double not null, "
                + WEATHER_PRESSURE + " double not null, "
                + WEATHER_RISING + " long not null, "
                + WEATHER_VISIBILITY + " double not null, "
                + WEATHER_SUNRISE + " text not null, "
                + WEATHER_SUNSET + " text not null, "
                + WEATHER_D1 + " text not null, "
                + WEATHER_D2 + " text not null, "
                + WEATHER_D3 + " text not null, "
                + WEATHER_D4 + " text not null, "
                + WEATHER_D5 + " text not null, "
                + WEATHER_D6 + " text not null, "
                + WEATHER_D7 + " text not null, "
                + "UNIQUE(" + WEATHER_CITY + ") ON CONFLICT REPLACE);")

        fun addWeather(mSqLiteDatabase: SQLiteDatabase, weather_date: Long,
                       weather_filterName: String, weather_now: Double, weather_city: String,
                       weather_high: Double, weather_low: Double, weather_text: String,
                       weather_description: String, weather_humidity: Double,
                       weather_pressure: Double, weather_rising: Long, weather_visibility: Double,
                       weather_sunrise: String, weather_sunset: String, weather_d1: String,
                       weather_d2: String, weather_d3: String, weather_d4: String,
                       weather_d5: String, weather_d6: String, weather_d7: String) {

            val newValues = ContentValues()
            newValues.put(WEATHER_DATE, weather_date)
            newValues.put(WEATHER_FILTERNAME, weather_filterName)
            newValues.put(WEATHER_NOW, weather_now)
            newValues.put(WEATHER_CITY, weather_city)
            newValues.put(WEATHER_HIGH, weather_high)
            newValues.put(WEATHER_LOW, weather_low)
            newValues.put(WEATHER_TEXT, weather_text)
            newValues.put(WEATHER_HUMIDITY, weather_humidity)
            newValues.put(WEATHER_PRESSURE, weather_pressure)
            newValues.put(WEATHER_RISING, weather_rising)
            newValues.put(WEATHER_VISIBILITY, weather_visibility)
            newValues.put(WEATHER_DESCRIPTION, weather_description)
            newValues.put(WEATHER_SUNRISE, weather_sunrise)
            newValues.put(WEATHER_SUNSET, weather_sunset)
            newValues.put(WEATHER_D1, weather_d1)
            newValues.put(WEATHER_D2, weather_d2)
            newValues.put(WEATHER_D3, weather_d3)
            newValues.put(WEATHER_D4, weather_d4)
            newValues.put(WEATHER_D5, weather_d5)
            newValues.put(WEATHER_D6, weather_d6)
            newValues.put(WEATHER_D7, weather_d7)

            mSqLiteDatabase.insertWithOnConflict(DATABASE_WEATHER,
                    null,
                    newValues,
                    SQLiteDatabase.CONFLICT_REPLACE)
        }
    }
}