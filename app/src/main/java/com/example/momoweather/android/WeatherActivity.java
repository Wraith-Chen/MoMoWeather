package com.example.momoweather.android;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.momoweather.R;
import com.example.momoweather.android.db.WeatherString;
import com.example.momoweather.android.gson.AirNowResponse;
import com.example.momoweather.android.gson.EverydayResponse;
import com.example.momoweather.android.gson.HourlyResponse;
import com.example.momoweather.android.gson.LifestyleResponse;
import com.example.momoweather.android.gson.TodayResponse;
import com.example.momoweather.android.util.HttpUtil;
import com.example.momoweather.android.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private List<String> hourlyWeather = new ArrayList<>();

    public DrawerLayout drawerLayout;

    public SwipeRefreshLayout swipeRefresh;

    private ScrollView weatherLayout;

    private Button navButton;

    private TextView titleCity;

    private TextView titleUpdateTime;

    private TextView nextForeText;

    private TextView degreeText;

    private TextView weatherInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiText;

    private TextView pm25Text;

    private TextView comfortText;

    private TextView carWashText;

    private TextView sportText;

    private TextView sunText;

    private ImageView bingPicImg;

    private String weatherId;

    private String mWeatherId;

    private String cityName;

    private RecyclerView recyclerView;

    private HourlyWeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        // 初始化各控
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        sunText = (TextView) findViewById(R.id.sun_text);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navButton = (Button) findViewById(R.id.nav_button);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if(weatherString!=null) {
//            TodayResponse todayResponse = Utility.handleTodayResponse(weatherString);
//            weatherId = todayResponse.getCityId();
////            String everyWeather = prefs.getString("everyResponse",null);
//            Log.d("every",weatherString);
////            String airNowWeather = prefs.getString("airNowResponse",null);
////            String lifeWeather = prefs.getString("lifestyleResponse",null);
////            EverydayResponse everydayResponse =Utility.handleEveryResponse(everyWeather);
////            AirNowResponse airNowResponse = Utility.handleAirResponse(airNowWeather);
////            LifestyleResponse lifestyleResponse = Utility.handleLifeResponse(lifeWeather);
//            showTodayWeatherInfo(todayResponse);
////            showEveryWeatherInfo(everydayResponse);
////            showAirNowInfo(airNowResponse);
////            showLifeStyleInfo(lifestyleResponse);
//        }else {
            weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            cityName = getIntent().getStringExtra("city_name");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestWeather(weatherId, cityName);
                }
            }).start();
                    requestNextWeather(weatherId);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestEveryWeather(weatherId);
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestAirNowWeather(weatherId);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestLifestyleWeather(weatherId);
                }
            }).start();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                weatherId = getIntent().getStringExtra("weather_id");
                cityName = getIntent().getStringExtra("city_name");
                requestWeather(weatherId,cityName);
                requestEveryWeather(weatherId);
                requestAirNowWeather(weatherId);
                requestLifestyleWeather(weatherId);
            }
        });
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        } else {
            loadBingPic();
        }
    }

    /**
     * 根据天气id请求城市当天天气信息。
     */
    public void requestWeather(final String weatherId, final String cityName) {
        String weatherUrl = "https://devapi.qweather.com/v7/weather/now?location=" + weatherId + "&key=68c6366a84c8429b9218687da1864c76";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final TodayResponse todayResponse = Utility.handleTodayResponse(responseText);
                todayResponse.setCityName(cityName);
                todayResponse.setCityId(weatherId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (todayResponse.getCode() != null) {
                            WeatherString today = new WeatherString();
                            today.setTodayResponse(responseText);
                            today.save();
                            showTodayWeatherInfo(todayResponse);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    /**
     * 根据天气id请求未来24小时天气信息。
     */
    public void requestNextWeather(final String weatherId) {
        String weatherUrl = "https://devapi.qweather.com/v7/weather/24h?location=" + weatherId + "&key=68c6366a84c8429b9218687da1864c76";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final HourlyResponse hourlyResponse = Utility.handleNextResponse(responseText);
                hourlyResponse.setCityId(weatherId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hourlyResponse.getCode() != null) {
                           WeatherString hourly = new WeatherString();
                           hourly.setHourly(responseText);
                           hourly.save();
                            showNextWeatherInfo(hourlyResponse);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }
    /**
     * 根据天气id请求城市7天天气信息。
     */
    public void requestEveryWeather(final String weatherId) {
        String weatherUrl = "https://devapi.qweather.com/v7/weather/7d?location=" + weatherId + "&key=68c6366a84c8429b9218687da1864c76";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final EverydayResponse everydayResponse = Utility.handleEveryResponse(responseText);
                everydayResponse.setCityId(weatherId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (everydayResponse.getCode() != null) {
                           WeatherString every = new WeatherString();
                           every.setEvery(responseText);
                           every.save();
                            showEveryWeatherInfo(everydayResponse);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });

        loadBingPic();
    }

    /**
     * 根据天气id请求城市空气质量信息。
     */
    public void requestAirNowWeather(final String weatherId) {
        String weatherUrl = "https://devapi.qweather.com/v7/air/now?location=" + weatherId + "&key=68c6366a84c8429b9218687da1864c76";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final AirNowResponse airNowResponse = Utility.handleAirResponse(responseText);
                airNowResponse.setCityId(weatherId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (airNowResponse.getCode() != null) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("airNowResponse", responseText);
                            editor.apply();
                            showAirNowInfo(airNowResponse);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }
    /**
     * 根据天气id请求城市生活建议信息。
     */
    public void requestLifestyleWeather(final String weatherId) {
        String weatherUrl = "https://devapi.qweather.com/v7/indices/1d?type=1,2,3&location="+weatherId+"&key=68c6366a84c8429b9218687da1864c76";
        HttpUtil.sendOkHttpRequest(weatherUrl,new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final LifestyleResponse lifestyleResponse= Utility.handleLifeResponse(responseText);
                Log.d("response",responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (lifestyleResponse.getCode() != null ) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("lifestyleResponse", responseText);
                            editor.apply();
                            showLifeStyleInfo(lifestyleResponse);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });

        loadBingPic();
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 处理并展示当天中的数据。
     */
    private void showTodayWeatherInfo(TodayResponse todayResponse) {
        String cityName = todayResponse.getCityName();
        String updateTime1 = todayResponse.getUpdateTime().split("\\+")[0];
        String updateTime = updateTime1.split("T")[1];
        String degree = todayResponse.getNow().getTemp() + "℃";
        String weatherInfo = todayResponse.getNow().getText();
        weatherInfoText.setText(weatherInfo);
        titleCity.setText(cityName);
        titleUpdateTime.setText("更新时间：" + updateTime);
        degreeText.setText(degree);
        forecastLayout.removeAllViews();
        weatherLayout.setVisibility(View.VISIBLE);
//        Intent intent = new Intent(this, AutoUpdateService.class);
//        startService(intent);
    }
    /**
     * 处理并展示24小时的数据。
     */
    private void showNextWeatherInfo(HourlyResponse hourlyResponse) {
        for (HourlyResponse.HourlyBean hourlyBean:hourlyResponse.getHourly()) {
            String nextTime = hourlyBean.getFxTime().split("T")[1];
            nextTime = nextTime.split("\\+")[0];
            Log.d("hourlyString",nextTime);
            hourlyWeather.add(nextTime+","+hourlyBean.getText()+","+hourlyBean.getTemp()+"℃");
        }
        Log.d("nextResponse",hourlyWeather.get(2));
        recyclerView = (RecyclerView) findViewById(R.id.after_fore);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HourlyWeatherAdapter(hourlyWeather);
        recyclerView.setAdapter(adapter);
    }
    /**
     * 处理并展示7天的数据。
     */
    private void showEveryWeatherInfo(EverydayResponse everydayResponse) {
        forecastLayout.removeAllViews();
        for (EverydayResponse.DailyBean everydayResponse1 : everydayResponse.getDaily()) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(everydayResponse1.getFxDate());
            infoText.setText(everydayResponse1.getTextDay() + "/" + everydayResponse1.getTextNight());
            maxText.setText(everydayResponse1.getTempMax() + "℃");
            minText.setText(everydayResponse1.getTempMin() + "℃");
            forecastLayout.addView(view);
        }
        weatherLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 处理并展示空气质量数据。
     */
    private void showAirNowInfo(AirNowResponse airNowResponse) {
        if (airNowResponse.getNow().getAqi() != null) {
            aqiText.setText(airNowResponse.getNow().getAqi());
            pm25Text.setText(airNowResponse.getNow().getPm2p5());
        }
    }
    private void showLifeStyleInfo(LifestyleResponse lifestyleResponse){
            for (LifestyleResponse.DailyBean dailyBean : lifestyleResponse.getDaily()) {
                if (("1").equals(dailyBean.getType())) {
                    String sport = "运动建议：" + dailyBean.getText();
                    sportText.setText(sport);
                } else if (("2").equals(dailyBean.getType())) {
                    String carwash = "洗车建议：" + dailyBean.getText();
                    carWashText.setText(carwash);
                }else if(("3").equals(dailyBean.getType())){
                    String suntext = "穿衣建议:"+dailyBean.getText();
                    sunText.setText(suntext);
                }
            }
        weatherLayout.setVisibility(View.VISIBLE);
    }
    public void initfruit(){
        for(int i = 0 ;i<10;i++){
            String fruit =("18:00,多云,25"+ "℃");
            hourlyWeather.add(fruit);
        }
    }

}
