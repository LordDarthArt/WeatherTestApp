package com.example.lorddarthart.weathertest;

import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HttpServiceHelper {

    public int getTasks(SQLiteDatabase mSqLiteDatabase, String city) throws IOException, JSONException, ParseException {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("connection", "keep-alive");
        con.setRequestProperty("content-type", "application/octet-stream");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        if (!(responseCode == 401)) {
            InputStream inputStream = con.getInputStream();
            String stringResponse = imputStreamToString(inputStream);

            //JSONArray jsonTasks = new JSONObject(stringResponse).getJSONArray("#value").getJSONObject(0).getJSONObject("Value").getJSONArray("#value");

            List<Weather> weathers = readWeatherArray(stringResponse, city);
            System.out.println(weathers);
            if (weathers!=null) {
                for (Weather weather : weathers) {
                    DatabaseHelper.addWeather(mSqLiteDatabase, weather.getWeather_date(), weather.getWeather_filterName(), weather.getWeather_now(), weather.getWeather_city(),
                            weather.getWeather_high(), weather.getWeather_low(), weather.getWeather_text(), weather.getWeather_description(), weather.getWeather_humidity(),
                            weather.getWeather_pressure(), weather.getWeather_rising(), weather.getWeather_visibility(), weather.getWeather_sunrise(), weather.getWeather_sunset(),
                            weather.getWeather_d1_day(), weather.getWeather_d1_high(), weather.getWeather_d1_low(), weather.getWeather_d1_text(),
                            weather.getWeather_d2_day(), weather.getWeather_d2_high(), weather.getWeather_d2_low(), weather.getWeather_d2_text(),
                            weather.getWeather_d3_day(), weather.getWeather_d3_high(), weather.getWeather_d3_low(), weather.getWeather_d3_text(),
                            weather.getWeather_d4_day(), weather.getWeather_d4_high(), weather.getWeather_d4_low(), weather.getWeather_d4_text(),
                            weather.getWeather_d5_day(), weather.getWeather_d5_high(), weather.getWeather_d5_low(), weather.getWeather_d5_text(),
                            weather.getWeather_d6_day(), weather.getWeather_d6_high(), weather.getWeather_d6_low(), weather.getWeather_d6_text(),
                            weather.getWeather_d7_day(), weather.getWeather_d7_high(), weather.getWeather_d7_low(), weather.getWeather_d7_text());
                }
            }
        }
        return responseCode;
    }

    public Weather readTask(String stringResponse, String filterName) throws IOException, JSONException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        long weather_date = sdf.parse(new JSONObject(stringResponse).getJSONObject("query").get("created").toString()).getTime();
        double weather_now = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").get("temp"));
        String weather_city = (String) new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("location").get("city");
        String weather_text = (String) new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").get("text");
        String weather_description = (String) new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").get("description");
        double weather_humidity=Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("humidity"));
        double weather_pressure=Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("pressure"));
        long weather_rising=Long.parseLong((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("rising"));
        double weather_visibility=Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere").get("visibility"));
        double weather_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("high"));
        double weather_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("low"));
        String weather_sunrise = new SimpleDateFormat("HH:mm").format(new SimpleDateFormat("KK:mm a", Locale.US).parse(new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy").get("sunrise").toString().toUpperCase()));;
        String weather_sunset = new SimpleDateFormat("HH:mm").format(new SimpleDateFormat("KK:mm a", Locale.US).parse(new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy").get("sunset").toString().toUpperCase()));
        String weather_d1_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("day");
        double weather_d1_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("high"));
        double weather_d1_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("low"));
        String weather_d1_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(0).get("text");
        String weather_d2_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("day");
        double weather_d2_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("high"));
        double weather_d2_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("low"));
        String weather_d2_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(1).get("text");
        String weather_d3_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("day");
        double weather_d3_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("high"));
        double weather_d3_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("low"));
        String weather_d3_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(2).get("text");
        String weather_d4_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("day");
        double weather_d4_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("high"));
        double weather_d4_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("low"));
        String weather_d4_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(3).get("text");
        String weather_d5_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("day");
        double weather_d5_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("high"));
        double weather_d5_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("low"));
        String weather_d5_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(4).get("text");
        String weather_d6_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("day");
        double weather_d6_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("high"));
        double weather_d6_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("low"));
        String weather_d6_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(5).get("text");
        String weather_d7_day = (String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("day");
        double weather_d7_high = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("high"));
        double weather_d7_low = Double.parseDouble((String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("low"));
        String weather_d7_text=(String)new JSONObject(stringResponse).getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast").getJSONObject(6).get("text");

        return new Weather(weather_date, filterName, weather_now, weather_city,  weather_high, weather_low, weather_text, weather_description, weather_humidity, weather_pressure,weather_rising, weather_visibility, weather_sunrise, weather_sunset,
                weather_d1_day, weather_d1_high, weather_d1_low, weather_d1_text,weather_d2_day, weather_d2_high, weather_d2_low, weather_d2_text,weather_d3_day, weather_d3_high, weather_d3_low, weather_d3_text,weather_d4_day, weather_d4_high,
                weather_d4_low, weather_d4_text,weather_d5_day, weather_d5_high, weather_d5_low, weather_d5_text,weather_d6_day, weather_d6_high, weather_d6_low, weather_d6_text,weather_d7_day, weather_d7_high, weather_d7_low, weather_d7_text);
    }

    private String imputStreamToString(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        }
    }

    public List<Weather> readWeatherArray(String array, String city) throws IOException, JSONException, ParseException {
        List<Weather> tasks = new ArrayList<Weather>();


        tasks.add(readTask(array, city));
        return tasks;
    }
}
