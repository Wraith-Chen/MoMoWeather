package com.example.momoweather.android.util;

import com.example.momoweather.android.gson.AirNowResponse;
import com.example.momoweather.android.gson.EverydayResponse;
import com.example.momoweather.android.gson.HourlyResponse;
import com.example.momoweather.android.gson.LifestyleResponse;
import com.example.momoweather.android.gson.TodayResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utility {

    public static TodayResponse handleTodayResponse(String response) {
        try {
            return new Gson().fromJson(response, TodayResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static HourlyResponse handleNextResponse(String response) {
        try {
            return new Gson().fromJson(response, HourlyResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static EverydayResponse handleEveryResponse(String response) {
        try {
            return new Gson().fromJson(response, EverydayResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static AirNowResponse handleAirResponse(String response) {
        try {
            return new Gson().fromJson(response, AirNowResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LifestyleResponse handleLifeResponse(String response) {
        try {
            return new Gson().fromJson(response, LifestyleResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
