package com.susy.dormitoryassistant.http;

import com.susy.dormitoryassistant.entity.WeatherJson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by susy on 17/1/19.
 */
public class WeatherClient {
    static Retrofit mRetrofit;
    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://www.weather.com.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public interface ApiStores {
        @GET("adat/sk/{cityId}.html")
        Call<WeatherJson> getWeather(@Path("cityId") String cityId);
    }

}
