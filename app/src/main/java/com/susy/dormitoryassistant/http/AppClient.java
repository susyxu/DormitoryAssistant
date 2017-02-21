package com.susy.dormitoryassistant.http;

import com.susy.dormitoryassistant.entity.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by susy on 17/1/19.
 */
public class AppClient {
    static Retrofit mRetrofit;
    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://115.159.217.61:8080/zzz/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public interface ApiStores {
        @GET("json/getStudent?")
        Call<Student> getStudent(@Query("studentId") String studentId);
    }

}
