package com.example.momoweather.android.db;

import org.litepal.crud.LitePalSupport;

public class WeatherString extends LitePalSupport {

   private String todayResponse;

   private String every;

    public String getEvery() {
        return every;
    }

    public void setEvery(String every) {
        this.every = every;
    }

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    private String hourly;

    public String getTodayResponse() {
        return todayResponse;
    }

    public void setTodayResponse(String todayResponse) {
        this.todayResponse = todayResponse;
    }
}
