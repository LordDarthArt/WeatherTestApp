package com.example.lorddarthart.weathertest;

public class Weather {
    private long weather_date;
    private String weather_filterName;
    private double weather_now;
    private String weather_city;
    private double weather_high;
    private double weather_low;
    private String weather_text;
    private String weather_description;
    private double weather_humidity;
    private double weather_pressure;
    private long weather_rising;
    private double weather_visibility;
    private String weather_sunrise;
    private String weather_sunset;
    private String weather_d1_day;
    private double weather_d1_high;
    private double weather_d1_low;
    private String weather_d1_text;
    private String weather_d2_day;
    private double weather_d2_high;
    private double weather_d2_low;
    private String weather_d2_text;
    private String weather_d3_day;
    private double weather_d3_high;
    private double weather_d3_low;
    private String weather_d3_text;
    private String weather_d4_day;
    private double weather_d4_high;
    private double weather_d4_low;
    private String weather_d4_text;
    private String weather_d5_day;
    private double weather_d5_high;
    private double weather_d5_low;
    private String weather_d5_text;
    private String weather_d6_day;
    private double weather_d6_high;
    private double weather_d6_low;
    private String weather_d6_text;
    private String weather_d7_day;
    private double weather_d7_high;
    private double weather_d7_low;
    private String weather_d7_text;

    public Weather(long weather_date, String weather_filterName, double weather_now, String weather_city, double weather_high, double weather_low, String weather_text, String weather_description, double weather_humidity, double weather_pressure, long weather_rising, double weather_visibility, String weather_sunrise, String weather_sunset, String weather_d1_day, double weather_d1_high, double weather_d1_low, String weather_d1_text, String weather_d2_day, double weather_d2_high, double weather_d2_low, String weather_d2_text, String weather_d3_day, double weather_d3_high, double weather_d3_low, String weather_d3_text, String weather_d4_day, double weather_d4_high, double weather_d4_low, String weather_d4_text, String weather_d5_day, double weather_d5_high, double weather_d5_low, String weather_d5_text, String weather_d6_day, double weather_d6_high, double weather_d6_low, String weather_d6_text, String weather_d7_day, double weather_d7_high, double weather_d7_low, String weather_d7_text) {
        this.weather_date = weather_date;
        this.weather_filterName = weather_filterName;
        this.weather_now = weather_now;
        this.weather_city = weather_city;
        this.weather_high = weather_high;
        this.weather_low = weather_low;
        this.weather_text = weather_text;
        this.weather_description = weather_description;
        this.weather_humidity = weather_humidity;
        this.weather_pressure = weather_pressure;
        this.weather_rising = weather_rising;
        this.weather_visibility = weather_visibility;
        this.weather_sunrise = weather_sunrise;
        this.weather_sunset = weather_sunset;
        this.weather_d1_day = weather_d1_day;
        this.weather_d1_high = weather_d1_high;
        this.weather_d1_low = weather_d1_low;
        this.weather_d1_text = weather_d1_text;
        this.weather_d2_day = weather_d2_day;
        this.weather_d2_high = weather_d2_high;
        this.weather_d2_low = weather_d2_low;
        this.weather_d2_text = weather_d2_text;
        this.weather_d3_day = weather_d3_day;
        this.weather_d3_high = weather_d3_high;
        this.weather_d3_low = weather_d3_low;
        this.weather_d3_text = weather_d3_text;
        this.weather_d4_day = weather_d4_day;
        this.weather_d4_high = weather_d4_high;
        this.weather_d4_low = weather_d4_low;
        this.weather_d4_text = weather_d4_text;
        this.weather_d5_day = weather_d5_day;
        this.weather_d5_high = weather_d5_high;
        this.weather_d5_low = weather_d5_low;
        this.weather_d5_text = weather_d5_text;
        this.weather_d6_day = weather_d6_day;
        this.weather_d6_high = weather_d6_high;
        this.weather_d6_low = weather_d6_low;
        this.weather_d6_text = weather_d6_text;
        this.weather_d7_day = weather_d7_day;
        this.weather_d7_high = weather_d7_high;
        this.weather_d7_low = weather_d7_low;
        this.weather_d7_text = weather_d7_text;
    }

    public Weather() {

    }

    public double getWeather_humidity() {
        return weather_humidity;
    }

    public void setWeather_humidity(double weather_humidity) {
        this.weather_humidity = weather_humidity;
    }

    public double getWeather_pressure() {
        return weather_pressure;
    }

    public void setWeather_pressure(double weather_pressure) {
        this.weather_pressure = weather_pressure;
    }

    public long getWeather_rising() {
        return weather_rising;
    }

    public void setWeather_rising(long weather_rising) {
        this.weather_rising = weather_rising;
    }

    public double getWeather_visibility() {
        return weather_visibility;
    }

    public void setWeather_visibility(double weather_visibility) {
        this.weather_visibility = weather_visibility;
    }

    public String getWeather_text() {
        return weather_text;
    }

    public void setWeather_text(String weather_text) {
        this.weather_text = weather_text;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    public String getWeather_d1_day() {
        return weather_d1_day;
    }

    public void setWeather_d1_day(String weather_d1_day) {
        this.weather_d1_day = weather_d1_day;
    }

    public double getWeather_d1_high() {
        return weather_d1_high;
    }

    public void setWeather_d1_high(double weather_d1_high) {
        this.weather_d1_high = weather_d1_high;
    }

    public double getWeather_d1_low() {
        return weather_d1_low;
    }

    public void setWeather_d1_low(double weather_d1_low) {
        this.weather_d1_low = weather_d1_low;
    }

    public String getWeather_d1_text() {
        return weather_d1_text;
    }

    public void setWeather_d1_text(String weather_d1_text) {
        this.weather_d1_text = weather_d1_text;
    }

    public String getWeather_d2_day() {
        return weather_d2_day;
    }

    public void setWeather_d2_day(String weather_d2_day) {
        this.weather_d2_day = weather_d2_day;
    }

    public double getWeather_d2_high() {
        return weather_d2_high;
    }

    public void setWeather_d2_high(double weather_d2_high) {
        this.weather_d2_high = weather_d2_high;
    }

    public double getWeather_d2_low() {
        return weather_d2_low;
    }

    public void setWeather_d2_low(double weather_d2_low) {
        this.weather_d2_low = weather_d2_low;
    }

    public String getWeather_d2_text() {
        return weather_d2_text;
    }

    public void setWeather_d2_text(String weather_d2_text) {
        this.weather_d2_text = weather_d2_text;
    }

    public String getWeather_d3_day() {
        return weather_d3_day;
    }

    public void setWeather_d3_day(String weather_d3_day) {
        this.weather_d3_day = weather_d3_day;
    }

    public double getWeather_d3_high() {
        return weather_d3_high;
    }

    public void setWeather_d3_high(double weather_d3_high) {
        this.weather_d3_high = weather_d3_high;
    }

    public double getWeather_d3_low() {
        return weather_d3_low;
    }

    public void setWeather_d3_low(double weather_d3_low) {
        this.weather_d3_low = weather_d3_low;
    }

    public String getWeather_d3_text() {
        return weather_d3_text;
    }

    public void setWeather_d3_text(String weather_d3_text) {
        this.weather_d3_text = weather_d3_text;
    }

    public String getWeather_d4_day() {
        return weather_d4_day;
    }

    public void setWeather_d4_day(String weather_d4_day) {
        this.weather_d4_day = weather_d4_day;
    }

    public double getWeather_d4_high() {
        return weather_d4_high;
    }

    public void setWeather_d4_high(double weather_d4_high) {
        this.weather_d4_high = weather_d4_high;
    }

    public double getWeather_d4_low() {
        return weather_d4_low;
    }

    public void setWeather_d4_low(double weather_d4_low) {
        this.weather_d4_low = weather_d4_low;
    }

    public String getWeather_d4_text() {
        return weather_d4_text;
    }

    public void setWeather_d4_text(String weather_d4_text) {
        this.weather_d4_text = weather_d4_text;
    }

    public String getWeather_d5_day() {
        return weather_d5_day;
    }

    public void setWeather_d5_day(String weather_d5_day) {
        this.weather_d5_day = weather_d5_day;
    }

    public double getWeather_d5_high() {
        return weather_d5_high;
    }

    public void setWeather_d5_high(double weather_d5_high) {
        this.weather_d5_high = weather_d5_high;
    }

    public double getWeather_d5_low() {
        return weather_d5_low;
    }

    public void setWeather_d5_low(double weather_d5_low) {
        this.weather_d5_low = weather_d5_low;
    }

    public String getWeather_d5_text() {
        return weather_d5_text;
    }

    public void setWeather_d5_text(String weather_d5_text) {
        this.weather_d5_text = weather_d5_text;
    }

    public String getWeather_d6_day() {
        return weather_d6_day;
    }

    public void setWeather_d6_day(String weather_d6_day) {
        this.weather_d6_day = weather_d6_day;
    }

    public double getWeather_d6_high() {
        return weather_d6_high;
    }

    public void setWeather_d6_high(double weather_d6_high) {
        this.weather_d6_high = weather_d6_high;
    }

    public double getWeather_d6_low() {
        return weather_d6_low;
    }

    public void setWeather_d6_low(double weather_d6_low) {
        this.weather_d6_low = weather_d6_low;
    }

    public String getWeather_d6_text() {
        return weather_d6_text;
    }

    public void setWeather_d6_text(String weather_d6_text) {
        this.weather_d6_text = weather_d6_text;
    }

    public String getWeather_d7_day() {
        return weather_d7_day;
    }

    public void setWeather_d7_day(String weather_d7_day) {
        this.weather_d7_day = weather_d7_day;
    }

    public double getWeather_d7_high() {
        return weather_d7_high;
    }

    public void setWeather_d7_high(double weather_d7_high) {
        this.weather_d7_high = weather_d7_high;
    }

    public double getWeather_d7_low() {
        return weather_d7_low;
    }

    public void setWeather_d7_low(double weather_d7_low) {
        this.weather_d7_low = weather_d7_low;
    }

    public String getWeather_d7_text() {
        return weather_d7_text;
    }

    public void setWeather_d7_text(String weather_d7_text) {
        this.weather_d7_text = weather_d7_text;
    }

    public String getWeather_filterName() {
        return weather_filterName;
    }

    public void setWeather_filterName(String weather_filterName) {
        this.weather_filterName = weather_filterName;
    }

    public long getWeather_date() {
        return weather_date;
    }

    public void setWeather_date(long weather_date) {
        this.weather_date = weather_date;
    }

    public double getWeather_now() {
        return weather_now;
    }

    public void setWeather_now(double weather_now) {
        this.weather_now = weather_now;
    }

    public String getWeather_city() {
        return weather_city;
    }

    public void setWeather_city(String weather_city) {
        this.weather_city = weather_city;
    }

    public double getWeather_high() {
        return weather_high;
    }

    public void setWeather_high(double weather_high) {
        this.weather_high = weather_high;
    }

    public double getWeather_low() {
        return weather_low;
    }

    public void setWeather_low(double weather_low) {
        this.weather_low = weather_low;
    }

    public String getWeather_sunrise() {
        return weather_sunrise;
    }

    public void setWeather_sunrise(String weather_sunrise) {
        this.weather_sunrise = weather_sunrise;
    }

    public String getWeather_sunset() {
        return weather_sunset;
    }

    public void setWeather_sunset(String weather_sunset) {
        this.weather_sunset = weather_sunset;
    }
}
