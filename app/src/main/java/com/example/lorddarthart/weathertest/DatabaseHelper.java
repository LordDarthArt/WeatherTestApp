package com.example.lorddarthart.weathertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DATABASE_NAME = "com.example.yahooweather.db";
    public static int DATABASE_VERSION = 1;

    public static final String DATABASE_WEATHER = "weather";
    public static final String WEATHER_FILTERNAME = "weather_filterName";
    public static final String WEATHER_ID = "weather_id";
    public static final String WEATHER_NOW = "weather_now";
    public static final String WEATHER_DATE = "weather_date";
    public static final String WEATHER_CITY = "weather_city";
    public static final String WEATHER_HIGH = "weather_high";
    public static final String WEATHER_LOW = "weather_low";
    public static final String WEATHER_TEXT = "weather_text";
    public static final String WEATHER_DESCRIPTION = "weather_description";
    public static final String WEATHER_HUMIDITY = "weather_humidity";
    public static final String WEATHER_PRESSURE = "weather_pressure";
    public static final String WEATHER_RISING = "weather_rising";
    public static final String WEATHER_VISIBILITY = "weather_visibility";
    public static final String WEATHER_SUNRISE = "weather_sunrise";
    public static final String WEATHER_SUNSET = "weather_sunset";
    public static final String WEATHER_D1_DAY = "weather_d1_day";
    public static final String WEATHER_D1_HIGH = "weather_d1_high";
    public static final String WEATHER_D1_LOW = "weather_d1_low";
    public static final String WEATHER_D1_TEXT = "weather_d1_text";
    public static final String WEATHER_D2_DAY = "weather_d2_day";
    public static final String WEATHER_D2_HIGH = "weather_d2_high";
    public static final String WEATHER_D2_LOW = "weather_d2_low";
    public static final String WEATHER_D2_TEXT = "weather_d2_text";
    public static final String WEATHER_D3_DAY = "weather_d3_day";
    public static final String WEATHER_D3_HIGH = "weather_d3_high";
    public static final String WEATHER_D3_LOW = "weather_d3_low";
    public static final String WEATHER_D3_TEXT = "weather_d3_text";
    public static final String WEATHER_D4_DAY = "weather_d4_day";
    public static final String WEATHER_D4_HIGH = "weather_d4_high";
    public static final String WEATHER_D4_LOW = "weather_d4_low";
    public static final String WEATHER_D4_TEXT = "weather_d4_text";
    public static final String WEATHER_D5_DAY = "weather_d5_day";
    public static final String WEATHER_D5_HIGH = "weather_d5_high";
    public static final String WEATHER_D5_LOW = "weather_d5_low";
    public static final String WEATHER_D5_TEXT = "weather_d5_text";
    public static final String WEATHER_D6_DAY = "weather_d6_day";
    public static final String WEATHER_D6_HIGH = "weather_d6_high";
    public static final String WEATHER_D6_LOW = "weather_d6_low";
    public static final String WEATHER_D6_TEXT = "weather_d6_text";
    public static final String WEATHER_D7_DAY = "weather_d7_day";
    public static final String WEATHER_D7_HIGH = "weather_d7_high";
    public static final String WEATHER_D7_LOW = "weather_d7_low";
    public static final String WEATHER_D7_TEXT = "weather_d7_text";

    public static final String DATABASE_CREATE_WEATHER_SCRIPT = "create table "
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
            + WEATHER_D1_DAY + " text not null, "
            + WEATHER_D1_HIGH + " double not null, "
            + WEATHER_D1_LOW + " double not null, "
            + WEATHER_D1_TEXT + " text not null, "
            + WEATHER_D2_DAY + " text not null, "
            + WEATHER_D2_HIGH + " double not null, "
            + WEATHER_D2_LOW + " double not null, "
            + WEATHER_D2_TEXT + " text not null, "
            + WEATHER_D3_DAY + " text not null, "
            + WEATHER_D3_HIGH + " double not null, "
            + WEATHER_D3_LOW + " double not null, "
            + WEATHER_D3_TEXT + " text not null, "
            + WEATHER_D4_DAY + " text not null, "
            + WEATHER_D4_HIGH + " double not null, "
            + WEATHER_D4_LOW + " double not null, "
            + WEATHER_D4_TEXT + " text not null, "
            + WEATHER_D5_DAY + " text not null, "
            + WEATHER_D5_HIGH + " double not null, "
            + WEATHER_D5_LOW + " double not null, "
            + WEATHER_D5_TEXT + " text not null, "
            + WEATHER_D6_DAY + " text not null, "
            + WEATHER_D6_HIGH + " double not null, "
            + WEATHER_D6_LOW + " double not null, "
            + WEATHER_D6_TEXT + " text not null, "
            + WEATHER_D7_DAY + " text not null, "
            + WEATHER_D7_HIGH + " double not null, "
            + WEATHER_D7_LOW + " double not null, "
            + WEATHER_D7_TEXT + " text not null, "
            + "UNIQUE(" + WEATHER_CITY + ") ON CONFLICT REPLACE);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_WEATHER_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void addWeather(SQLiteDatabase mSqLiteDatabase, long weather_date, String weather_filterName, double weather_now, String weather_city, double weather_high,
                                  double weather_low, String weather_text, String weather_description, double weather_humidity, double weather_pressure, long weather_rising,
                                  double weather_visibility, String weather_sunrise, String weather_sunset, String weather_d1_day, double weather_d1_high, double weather_d1_low,
                                  String weather_d1_text, String weather_d2_day, double weather_d2_high, double weather_d2_low, String weather_d2_text, String weather_d3_day,
                                  double weather_d3_high, double weather_d3_low, String weather_d3_text, String weather_d4_day, double weather_d4_high, double weather_d4_low,
                                  String weather_d4_text, String weather_d5_day, double weather_d5_high, double weather_d5_low, String weather_d5_text, String weather_d6_day,
                                  double weather_d6_high, double weather_d6_low, String weather_d6_text, String weather_d7_day, double weather_d7_high, double weather_d7_low,
                                  String weather_d7_text) {

        ContentValues newValues = new ContentValues();
        newValues.put(DatabaseHelper.WEATHER_DATE, weather_date);
        newValues.put(DatabaseHelper.WEATHER_FILTERNAME, weather_filterName);
        newValues.put(DatabaseHelper.WEATHER_NOW, weather_now);
        newValues.put(DatabaseHelper.WEATHER_CITY, weather_city);
        newValues.put(DatabaseHelper.WEATHER_HIGH, weather_high);
        newValues.put(DatabaseHelper.WEATHER_LOW, weather_low);
        newValues.put(DatabaseHelper.WEATHER_TEXT, weather_text);
        newValues.put(DatabaseHelper.WEATHER_HUMIDITY, weather_humidity);
        newValues.put(DatabaseHelper.WEATHER_PRESSURE, weather_pressure);
        newValues.put(DatabaseHelper.WEATHER_RISING, weather_rising);
        newValues.put(DatabaseHelper.WEATHER_VISIBILITY, weather_visibility);
        newValues.put(DatabaseHelper.WEATHER_DESCRIPTION, weather_description);
        newValues.put(DatabaseHelper.WEATHER_SUNRISE, weather_sunrise);
        newValues.put(DatabaseHelper.WEATHER_SUNSET, weather_sunset);
        newValues.put(DatabaseHelper.WEATHER_D1_DAY, weather_d1_day);
        newValues.put(DatabaseHelper.WEATHER_D1_HIGH, weather_d1_high);
        newValues.put(DatabaseHelper.WEATHER_D1_LOW, weather_d1_low);
        newValues.put(DatabaseHelper.WEATHER_D1_TEXT, weather_d1_text);
        newValues.put(DatabaseHelper.WEATHER_D2_DAY, weather_d2_day);
        newValues.put(DatabaseHelper.WEATHER_D2_HIGH, weather_d2_high);
        newValues.put(DatabaseHelper.WEATHER_D2_LOW, weather_d2_low);
        newValues.put(DatabaseHelper.WEATHER_D2_TEXT, weather_d2_text);
        newValues.put(DatabaseHelper.WEATHER_D3_DAY, weather_d3_day);
        newValues.put(DatabaseHelper.WEATHER_D3_HIGH, weather_d3_high);
        newValues.put(DatabaseHelper.WEATHER_D3_LOW, weather_d3_low);
        newValues.put(DatabaseHelper.WEATHER_D3_TEXT, weather_d3_text);
        newValues.put(DatabaseHelper.WEATHER_D4_DAY, weather_d4_day);
        newValues.put(DatabaseHelper.WEATHER_D4_HIGH, weather_d4_high);
        newValues.put(DatabaseHelper.WEATHER_D4_LOW, weather_d4_low);
        newValues.put(DatabaseHelper.WEATHER_D4_TEXT, weather_d4_text);
        newValues.put(DatabaseHelper.WEATHER_D5_DAY, weather_d5_day);
        newValues.put(DatabaseHelper.WEATHER_D5_HIGH, weather_d5_high);
        newValues.put(DatabaseHelper.WEATHER_D5_LOW, weather_d5_low);
        newValues.put(DatabaseHelper.WEATHER_D5_TEXT, weather_d5_text);
        newValues.put(DatabaseHelper.WEATHER_D6_DAY, weather_d6_day);
        newValues.put(DatabaseHelper.WEATHER_D6_HIGH, weather_d6_high);
        newValues.put(DatabaseHelper.WEATHER_D6_LOW, weather_d6_low);
        newValues.put(DatabaseHelper.WEATHER_D6_TEXT, weather_d6_text);
        newValues.put(DatabaseHelper.WEATHER_D7_DAY, weather_d7_day);
        newValues.put(DatabaseHelper.WEATHER_D7_HIGH, weather_d7_high);
        newValues.put(DatabaseHelper.WEATHER_D7_LOW, weather_d7_low);
        newValues.put(DatabaseHelper.WEATHER_D7_TEXT, weather_d7_text);

        mSqLiteDatabase.insertWithOnConflict(DatabaseHelper.DATABASE_WEATHER, null, newValues, SQLiteDatabase.CONFLICT_REPLACE);
    }
}