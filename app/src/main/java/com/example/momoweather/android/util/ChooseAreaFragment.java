package com.example.momoweather.android.util;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.momoweather.R;
import com.example.momoweather.android.MainActivity;
import com.example.momoweather.android.WeatherActivity;
import com.example.momoweather.android.db.City;
import com.example.momoweather.android.db.County;
import com.example.momoweather.android.db.Province;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChooseAreaFragment extends Fragment {

    private static final String TAG = "ChooseAreaFragment";

    public static final int LEVEL_PROVINCE = 0;

    public static final int LEVEL_CITY = 1;

    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;

    private TextView titleText;

    private Button backButton;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 市列表
     */
    private List<City> cityList;

    /**
     * 县列表
     */
    private List<County> countyList;

    /**
     * 选中的省份
     */
    private Province selectedProvince;

    /**
     * 选中的城市
     */
    private City selectedCity;

    /**
     * 当前选中的级别
     */
    private int currentLevel;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
                LitePal.deleteAll(Province.class);
                InputStream is = null;
                try {
                    is = getResources().openRawResource(R.raw.province);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
                    //创建字符缓冲流
                    String line;
                    while ((line = br.readLine()) != null) {
                        //把读取到的数据用split()进行分割
                        String[] strArray = line.split(",");
                        //创建省份对象
                        Province province = new Province();
                        province.setProvinceName(strArray[1]);
                        province.save();
                        Log.d("province", province.getProvinceName());
                    }
                    is.close();
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LitePal.deleteAll(City.class);
                    InputStream is = null;
                    try {
                        is =getResources().openRawResource(R.raw.city);
                        //调用字符缓冲输入流对象的方法读数据
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
                        //创建字符缓冲流
                        String line;
                        while ((line = br.readLine()) != null) {
                            //把读取到的数据用split()进行分割
                            String[] strArray = line.split(",");
                            //创建省份对象
                            City city = new City();
                            city.setProvinceName(strArray[1]);
                            city.setCityName(strArray[2]);
                            city.save();
                        }
                        br.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                LitePal.deleteAll(County.class);
                InputStream is = null;
                try {
                    is =getResources().openRawResource(R.raw.county);
                    //调用字符缓冲输入流对象的方法读数据
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
                    //创建字符缓冲流
                    String line;
                    while ((line = br.readLine()) != null) {
                        //把读取到的数据用split()进行分割
                        String[] strArray = line.split(",");
                        //创建省份对象
                       County county = new County();
                       county.setWeatherId(strArray[0]);
                       county.setCityName(strArray[2]);
                       county.setCountyName(strArray[1]);
                       county.save();
                    }
                    br.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    queryCounties();
                }
                else if (currentLevel == LEVEL_COUNTY) {
                    String weatherId = countyList.get(position).getWeatherId();
                    String cityName = countyList.get(position).getCountyName();
                    if (getActivity() instanceof MainActivity) {
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id", weatherId);
                        intent.putExtra("city_name",cityName);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity) {
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id", weatherId);
                        intent.putExtra("city_name",cityName);
                        startActivity(intent);
                        Log.d("city",cityName);
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        activity.drawerLayout.closeDrawers();
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
        queryProvinces();
    }

    /**
     * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryProvinces() {
        titleText.setText("末末天气");
        backButton.setVisibility(View.GONE);
        provinceList = LitePal.findAll(Province.class);
            dataList.clear();
            for (Province province : provinceList) {
                 dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCities() {
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList = LitePal.where("provinceName = ?",selectedProvince.getProvinceName()).find(City.class);
        Log.d("city",selectedProvince.getProvinceName());
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCounties() {
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countyList = LitePal.where("cityName = ?",selectedCity.getCityName()).find(County.class);
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTY;
    }

    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
