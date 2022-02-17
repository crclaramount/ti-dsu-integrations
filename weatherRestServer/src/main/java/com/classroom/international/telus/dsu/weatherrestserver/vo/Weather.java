package com.classroom.international.telus.dsu.weatherrestserver.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author geovanni.santos
 */
public class Weather {

    private String city;
    private String country;
    private String weatherType;
    private int temperature;
    private int humidity;
    private int maxtemperature;
    private int mintemperature;
    private List<Integer> dailyTemperature;
    private List<Integer> dailyHumidity;
    private String date;

    public Weather() {
    }

    public Weather(String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
        randomWeatherType();
        this.maxtemperature = Collections.max(this.dailyTemperature);
        this.mintemperature = Collections.min(this.dailyTemperature);
        this.temperature = this.dailyTemperature.get(hour(date));
        this.humidity = this.dailyHumidity.get(hour(date));

    }

    //Custom methods
    /**
     * This method set a random weatherType, temperature and humidity
     */
    public final void randomWeatherType() {
        int value = (int) (Math.random() * (6 - 1)) + 1;
        switch (value) {
            case 1:
                setWeatherType("Sunny");
                setdailyTemperature(18, 35);
                setdailyHumidity(40, 50);

                break;
            case 2:
                setWeatherType("Cloudy");
                setdailyTemperature(18, 30);
                setdailyHumidity(40, 70);

                break;
            case 3:
                setWeatherType("Rainy");
                setdailyTemperature(15, 30);
                setdailyHumidity(80, 100);

                break;
            case 4:
                setWeatherType("Windy");
                setdailyTemperature(18, 30);
                setdailyHumidity(40, 50);

                break;
            case 5:
                setWeatherType("Stormy");
                setdailyTemperature(15, 30);
                setdailyHumidity(80, 100);

                break;
            case 6:
                setWeatherType("Snowy");
                setdailyTemperature(5, -8);
                setdailyHumidity(40, 70);

                break;

        }

    }

    public void setdailyTemperature(int minT, int maxT) {
        this.dailyTemperature = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            int valueT = (int) (Math.random() * (maxT - minT)) + minT;
            this.dailyTemperature.add(i, valueT);
        }
    }

    public void setdailyHumidity(int minH, int maxH) {
        this.dailyHumidity = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            int valueT = (int) (Math.random() * (maxH - minH)) + minH;
            this.dailyHumidity.add(i, valueT);
        }
    }

    public int hour(String Date) {
        Date dat;
        int hour = 11;
        try {
            Calendar calendar = Calendar.getInstance();
            try {
                dat = new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(date);
                calendar.setTime(dat);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        } catch (Exception e) {
        }
        return hour;
    }

    //Setters & Getters 
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getMaxtemperature() {
        return maxtemperature;
    }

    public void setMaxtemperature(int maxtemperature) {
        this.maxtemperature = maxtemperature;
    }

    public int getMintemperature() {
        return mintemperature;
    }

    public void setMintemperature(int mintemperature) {
        this.mintemperature = mintemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Integer> getDailyTemperature() {

        return dailyTemperature;
    }

    public List<Integer> getDailyHumidity() {
        return dailyHumidity;
    }

    @Override
    public String toString() {
        return "{"
                + "\"city\":\"" + city
                + "\", \"country\":\"" + country
                + "\", \"date\":\"" + date
                + "\", \"weatherType\":\"" + weatherType
                + "\", \"temperature\":\"" + temperature
                + "\", \"humidity\":\"" + humidity
                + "\", \"maxtemperature\":\"" + maxtemperature
                + "\", \"mintemperature\":\"" + mintemperature
                + "\", \"dailyTemperature\":\"" + dailyTemperature
                + "\", \"dailyHumidity\":\"" + dailyHumidity + "\"}";

    }

}
