package com.example.momoweather.android;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momoweather.R;

import java.util.List;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.MyViewHolder> {

    private List<String> list;

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        TextView info;
        TextView temp;

        public MyViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.todayNext_time);
            info = view.findViewById(R.id.todayNext_info);
            temp = view.findViewById(R.id.todayNext_temp);
        }
    }

    public HourlyWeatherAdapter(List<String> list){
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.afterfore,parent,false);
        MyViewHolder viewHolder =new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String s = list.get(position);
        Log.d("onBind",s);
        String sTime =s.split(",")[0];
        String sInfo = s.split(",")[1];
        String sTemp = s.split(",")[2];
        holder.time.setText(sTime);
        holder.info.setText(sInfo);
        holder.temp.setText(sTemp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}