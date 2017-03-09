package com.susy.dormitoryassistant.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.WeatherJson;
import com.susy.dormitoryassistant.http.WeatherClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 学生端的主界面－1寝室公告（和管理员段共用）
 * Created by susy on 17/2/4.
 */
public class StudentNewsFragment extends Fragment implements View.OnClickListener {

    private TextView tv_weather;
    private TextView tv_date;

    private RadioGroup rg_info;
    private RadioButton rb_dormInfo;
    private RadioButton rb_waterInfo;
    private RadioButton rb_lightInfo;

    private RadioGroup rg_info2;
    private RadioButton rb_red;
    private RadioButton rb_black;
    private RadioButton rb_bad;

    int width = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_news, container, false);
        rg_info = (RadioGroup) rootView.findViewById(R.id.rg_info);
        rg_info2 = (RadioGroup) rootView.findViewById(R.id.rg_info2);

        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕的宽、高
        width = (int) (d.getWidth() * 1.0);

        initTabUI();

        tv_weather = (TextView) rootView.findViewById(R.id.tv_weather);
        tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        getWeather();
        tv_date.setText(UtilTools.getCurrentTime_ch());
        return rootView;
    }

    /**
     * 初始化两个tab的UI
     */
    private void initTabUI() {
        //第一个
        rb_dormInfo = (RadioButton) LayoutInflater.from(getActivity()).
                inflate(R.layout.tab_rb_infos, null);
        rb_dormInfo.setText("寝室公告");
        rb_dormInfo.setTextSize(16);
        rb_dormInfo.setTextColor(Color.parseColor("#00B7F3"));
        rb_dormInfo.setGravity(Gravity.CENTER);
        RadioGroup.LayoutParams params = new
                RadioGroup.LayoutParams(width / 3,
                RadioGroup.LayoutParams.MATCH_PARENT);
        rb_dormInfo.setTag(0);
        rb_dormInfo.setChecked(true);
        rb_dormInfo.setOnClickListener(this);
        rg_info.addView(rb_dormInfo, params);

        rb_waterInfo = (RadioButton) LayoutInflater.from(getActivity()).
                inflate(R.layout.tab_rb_infos, null);
        rb_waterInfo.setText("停水公告");
        rb_waterInfo.setTextSize(16);
        rb_waterInfo.setGravity(Gravity.CENTER);
        params = new
                RadioGroup.LayoutParams(width / 3,
                RadioGroup.LayoutParams.MATCH_PARENT);
        rb_waterInfo.setTag(1);
        rb_waterInfo.setOnClickListener(this);
        rg_info.addView(rb_waterInfo, params);

        rb_lightInfo = (RadioButton) LayoutInflater.from(getActivity()).
                inflate(R.layout.tab_rb_infos, null);
        rb_lightInfo.setText("熄灯公告");
        rb_lightInfo.setTextSize(16);
        rb_lightInfo.setGravity(Gravity.CENTER);
        params = new
                RadioGroup.LayoutParams(width / 3,
                RadioGroup.LayoutParams.MATCH_PARENT);
        rb_lightInfo.setTag(2);
        rb_lightInfo.setOnClickListener(this);
        rg_info.addView(rb_lightInfo, params);

        //第二个
        rb_red = (RadioButton) LayoutInflater.from(getActivity()).
                inflate(R.layout.tab_rb_infos, null);
        rb_red.setText("寝室红榜");
        rb_red.setTextSize(16);
        rb_red.setTextColor(Color.parseColor("#00B7F3"));
        rb_red.setGravity(Gravity.CENTER);
        RadioGroup.LayoutParams params2 = new
                RadioGroup.LayoutParams(width / 3,
                RadioGroup.LayoutParams.MATCH_PARENT);
        rb_red.setTag(3);
        rb_red.setChecked(true);
        rb_red.setOnClickListener(this);
        rg_info2.addView(rb_red, params);

        rb_black = (RadioButton) LayoutInflater.from(getActivity()).
                inflate(R.layout.tab_rb_infos, null);
        rb_black.setText("寝室黑榜");
        rb_black.setTextSize(16);
        rb_black.setGravity(Gravity.CENTER);
        params2 = new
                RadioGroup.LayoutParams(width / 3,
                RadioGroup.LayoutParams.MATCH_PARENT);
        rb_black.setTag(4);
        rb_black.setOnClickListener(this);
        rg_info2.addView(rb_black, params);

        rb_bad = (RadioButton) LayoutInflater.from(getActivity()).
                inflate(R.layout.tab_rb_infos, null);
        rb_bad.setText("通报批评");
        rb_bad.setTextSize(16);
        rb_bad.setGravity(Gravity.CENTER);
        params2 = new
                RadioGroup.LayoutParams(width / 3,
                RadioGroup.LayoutParams.MATCH_PARENT);
        rb_bad.setTag(5);
        rb_bad.setOnClickListener(this);
        rg_info2.addView(rb_bad, params);
    }

    /**
     * 监听事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        int index = (int) view.getTag();
        switch (index) {
            case 0: //寝室公告
                rb_dormInfo.setChecked(true);
                rb_dormInfo.setTextColor(Color.parseColor("#00B7F3"));
                rb_waterInfo.setChecked(false);
                rb_waterInfo.setTextColor(Color.parseColor("#000000"));
                rb_lightInfo.setChecked(false);
                rb_lightInfo.setTextColor(Color.parseColor("#000000"));
                break;
            case 1: //停水公告
                rb_dormInfo.setChecked(false);
                rb_dormInfo.setTextColor(Color.parseColor("#000000"));
                rb_waterInfo.setChecked(true);
                rb_waterInfo.setTextColor(Color.parseColor("#00B7F3"));
                rb_lightInfo.setChecked(false);
                rb_lightInfo.setTextColor(Color.parseColor("#000000"));
                break;
            case 2: //熄灯公告
                rb_dormInfo.setChecked(false);
                rb_dormInfo.setTextColor(Color.parseColor("#000000"));
                rb_waterInfo.setChecked(false);
                rb_waterInfo.setTextColor(Color.parseColor("#000000"));
                rb_lightInfo.setChecked(true);
                rb_lightInfo.setTextColor(Color.parseColor("#00B7F3"));
                break;
            case 3: //寝室红榜
                rb_red.setChecked(true);
                rb_red.setTextColor(Color.parseColor("#00B7F3"));
                rb_black.setChecked(false);
                rb_black.setTextColor(Color.parseColor("#000000"));
                rb_bad.setChecked(false);
                rb_bad.setTextColor(Color.parseColor("#000000"));
                break;
            case 4: //寝室黑榜
                rb_red.setChecked(false);
                rb_red.setTextColor(Color.parseColor("#000000"));
                rb_black.setChecked(true);
                rb_black.setTextColor(Color.parseColor("#00B7F3"));
                rb_bad.setChecked(false);
                rb_bad.setTextColor(Color.parseColor("#000000"));
                break;
            case 5: //通报批评
                rb_red.setChecked(false);
                rb_red.setTextColor(Color.parseColor("#000000"));
                rb_black.setChecked(false);
                rb_black.setTextColor(Color.parseColor("#000000"));
                rb_bad.setChecked(true);
                rb_bad.setTextColor(Color.parseColor("#00B7F3"));
                break;
        }
    }

    /**
     * 获取天气的信息 数据来源：http://www.weather.com.cn/data/cityinfo/101210101.html
     */
    private void getWeather() {
        WeatherClient.ApiStores apiStores = WeatherClient.retrofit().create(WeatherClient.ApiStores.class);
        Call<WeatherJson> call = apiStores.getWeather("101210101");
        call.enqueue(new Callback<WeatherJson>() {
            @Override
            public void onResponse(Call<WeatherJson> call, Response<WeatherJson> response) {
                tv_weather.setText(response.body().getWeatherinfo().getWeather()+" | "
                        +response.body().getWeatherinfo().getTemp1()+"/"
                        +response.body().getWeatherinfo().getTemp2());
            }

            @Override
            public void onFailure(Call<WeatherJson> call, Throwable t) {

            }
        });
    }

    /**
     * 获取公告中的6个信息
     */
    private void getInfos(){

    }
}
