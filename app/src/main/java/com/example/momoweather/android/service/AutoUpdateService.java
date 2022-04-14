package com.example.momoweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.momoweather.android.WeatherActivity;
import com.example.momoweather.android.gson.AirNowResponse;
import com.example.momoweather.android.gson.EverydayResponse;
import com.example.momoweather.android.gson.LifestyleResponse;
import com.example.momoweather.android.gson.TodayResponse;
import com.example.momoweather.android.util.HttpUtil;
import com.example.momoweather.android.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updatetodayWeather();
        update7dWeather();
        updateAirWeather();
        updateLifeWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000; // 这是8小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新当日天气信息。
     */
    private void updatetodayWeather(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("todayResponse", null);
            // 有缓存时直接解析天气数据
            TodayResponse todayResponse= Utility.handleTodayResponse(weatherString);
            String weatherId = todayResponse.getCityId();
            String weatherUrl = "https://devapi.qweather.com/v7/weather/now?location="+weatherId+"&key=68c6366a84c8429b9218687da1864c76";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    TodayResponse todayResponse= Utility.handleTodayResponse(responseText);
                    if (todayResponse.getCode() != null ) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("todayResponse", responseText);
                        editor.apply();
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }
            });

    }
/**
 * 更新七天天气信息。
 */
private void update7dWeather(){
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    String weatherString = prefs.getString("everydayResponse", null);
    // 有缓存时直接解析天气数据
    EverydayResponse everydayResponse= Utility.handleEveryResponse(weatherString);
    String weatherId = everydayResponse.getCityId();
    String weatherUrl = "https://devapi.qweather.com/v7/weather/7d?location=" + weatherId + "&key=68c6366a84c8429b9218687da1864c76";
    HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String responseText = response.body().string();
            EverydayResponse everydayResponse= Utility.handleEveryResponse(responseText);
            if (everydayResponse.getCode() != null ) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("everyResponse", responseText);
                editor.apply();
            }
        }
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }
    });
}
/**
 * 更新空气质量信息。
 */
private void updateAirWeather(){
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    String weatherString = prefs.getString("airNowResponse", null);
    // 有缓存时直接解析天气数据
    AirNowResponse airNowResponse = Utility.handleAirResponse(weatherString);
    String weatherId = airNowResponse.getCityId();
    String weatherUrl = "https://devapi.qweather.com/v7/weather/7d?location=" + weatherId + "&key=68c6366a84c8429b9218687da1864c76";
    HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String responseText = response.body().string();
            AirNowResponse airNowResponse = Utility.handleAirResponse(responseText);
            if (airNowResponse.getCode() != null ) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("airNowResponse", responseText);
                editor.apply();
            }
        }
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }
    });
}
/**
 * 更新生活指数信息。
 */
private void updateLifeWeather(){
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    String weatherString = prefs.getString("lifestyleResponse", null);
    // 有缓存时直接解析天气数据
    LifestyleResponse lifestyleResponse = Utility.handleLifeResponse(weatherString);
    String weatherId = lifestyleResponse.getCityId();
    String weatherUrl = "https://devapi.qweather.com/v7/indices/1d?type=1,2,3&location="+weatherId+"&key=68c6366a84c8429b9218687da1864c76";
    HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String responseText = response.body().string();
            LifestyleResponse lifestyleResponse = Utility.handleLifeResponse(responseText);
            if (lifestyleResponse.getCode() != null ) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("lifestyleResponse", responseText);
                editor.apply();
            }
        }
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }
    });
}
    /**
     * 更新必应每日一图
     */
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

}
