package com.susy.dormitoryassistant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.WeatherJson;
import com.susy.dormitoryassistant.http.WeatherClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 学生端的主界面－1寝室公告
 * Created by susy on 17/2/4.
 */
public class StudentNewsFragment extends Fragment implements View.OnClickListener{

    private TextView tv_weather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_news, container, false);
        tv_weather = (TextView) rootView.findViewById(R.id.weather);

        getWeather();
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    private void getWeather() {
        WeatherClient.ApiStores apiStores = WeatherClient.retrofit().create(WeatherClient.ApiStores.class);
        Call<WeatherJson> call = apiStores.getWeather("101210101");
        call.enqueue(new Callback<WeatherJson>() {
            @Override
            public void onResponse(Call<WeatherJson> call, Response<WeatherJson> response) {
                Log.i("weather", "getWeatherinfo=" + response.body().getWeatherinfo().getCity());
                tv_weather.setText(response.body().getWeatherinfo().getCity());
            }

            @Override
            public void onFailure(Call<WeatherJson> call, Throwable t) {

            }
        });
    }
}
