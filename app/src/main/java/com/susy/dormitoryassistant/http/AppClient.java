package com.susy.dormitoryassistant.http;

import android.util.Log;

import com.susy.dormitoryassistant.entity.DisobeyOrders;
import com.susy.dormitoryassistant.entity.DormStudents;
import com.susy.dormitoryassistant.entity.Dorms;
import com.susy.dormitoryassistant.entity.LoginStudent;
import com.susy.dormitoryassistant.entity.LoginUser;
import com.susy.dormitoryassistant.entity.MilkOrder;
import com.susy.dormitoryassistant.entity.MilkOrders;
import com.susy.dormitoryassistant.entity.RepairOrder;
import com.susy.dormitoryassistant.entity.RepairOrders;
import com.susy.dormitoryassistant.entity.SaveDisobeyOrder;
import com.susy.dormitoryassistant.entity.SaveMilkOrder;
import com.susy.dormitoryassistant.entity.SaveRepairOrder;
import com.susy.dormitoryassistant.entity.SaveWaterOrder;
import com.susy.dormitoryassistant.entity.Student;
import com.susy.dormitoryassistant.entity.WaterOrder;
import com.susy.dormitoryassistant.entity.WaterOrders;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                    .baseUrl("http://115.159.217.61:8080/dorm-api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("myRequestURL", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    public interface ApiStores {
        /**
         * 根据学号搜索学生
         */
        @GET("json/getStudent?")
        Call<Student> getStudent(@Query("studentId") String studentId);

        /**
         * 根据寝室搜索学生们
         */
        @GET("dormitory/getDormitoryById?")
        Call<DormStudents> getStudentsByDormCode(@Query("dormitoryId") String dormitoryId);

        /**
         * 用户登入
         */
        @GET("login/loginApp?")
        Call<LoginUser> appUserLogin(@Query("type") String type,
                                     @Query("username") String username,
                                     @Query("password") String password);

        @GET("login/loginApp?")
        Call<LoginStudent> appStudentLogin(@Query("type") String type,
                                           @Query("username") String username,
                                           @Query("password") String password);

        /**
         * 预定水
         */
        @GET("waterOrder/addOrder?")
        Call<SaveWaterOrder> studentAddwater(@Query("studentId") String studentId,
                                             @Query("count") String count,
                                             @Query("dormitoryId") String dormitoryId);

        @GET("waterOrder/{studentId}")
        Call<WaterOrders> studentGetWaterOrder(@Path("studentId") String studentId);

        /**
         * 预定牛奶
         */
        @GET("milkOrder/addOrder?")
        Call<SaveMilkOrder> studentAddMilk(@Query("studentId") String studentId,
                                           @Query("dormitoryId") String dormitoryId,
                                           @Query("milkMonth") String milkMonth,
                                           @Query("milkType") String milkType,
                                           @Query("milkCount") String milkCount);

        @GET("milkOrder/{studentId}")
        Call<MilkOrders> studentGetMilkOrder(@Path("studentId") String studentId);

        /**
         * 预约维修
         */
        @GET("repairOrder/addOrder?")
        Call<SaveRepairOrder> studentAddRepair(@Query("studentId") String studentId,
                                               @Query("dormitoryId") String dormitoryId,
                                               @Query("repairOrderItem") String repairOrderItem,
                                               @Query("repairOrderDetail") String repairOrderDetail,
                                               @Query("repairOrderFreeTime") String repairOrderFreeTime);

        @GET("repairOrder/{studentId}")
        Call<RepairOrders> studentGetRepairOrder(@Path("studentId") String studentId);

        /**
         * 修改用户密码
         */
        @GET("login/changeAppPwd?")
        Call<LoginUser> changeUserPwd(@Query("type") String type,
                                      @Query("username") String username,
                                      @Query("oldPassword") String oldPassword,
                                      @Query("newPassword") String newPassword);

        @GET("login/changeAppPwd?")
        Call<LoginStudent> changeStudentPwd(@Query("type") String type,
                                            @Query("username") String username,
                                            @Query("oldPassword") String oldPassword,
                                            @Query("newPassword") String newPassword);

        /**
         * 师傅订水订单
         */
        @GET("waterOrder/getWaterOrderByWorkerId?")
        Call<WaterOrders> workerGetWaterOrder(@Query("userId") String userId);

        @GET("waterOrder/finishOrder?")
        Call<SaveWaterOrder> workerFinishWaterOrder(@Query("userId") String userId,
                                                    @Query("waterOrderId") String waterOrderId);

        /**
         * 师傅维修订单
         */
        @GET("repairOrder/getRepairOrderByWorkerId?")
        Call<RepairOrders> workerGetRepairOrder(@Query("userId") String userId);
        @GET("repairOrder/finishOrder?")
        Call<SaveRepairOrder> workerFinishRepairOrder(@Query("userId") String userId,
                                                      @Query("repairOrderId") String repairOrderId);

        /**
         * 获取管理寝室列表
         */
        @GET("dormitory/getAdminDorms?")
        Call<Dorms> adminGetDormList(@Query("userId") String userId);

        /**
         * 违规记录
         */
        @GET("disobedientRule/add?")
        Call<SaveDisobeyOrder> addDisobeyOrders(@Query("studentId") String studentId,
                                                @Query("ruleId") String ruleId,
                                                @Query("userId") String userId,
                                                @Query("detail") String detail);
        @GET("disobedientRule/getByStudentId?")
        Call<DisobeyOrders> getDisobeyOrders(@Query("studentId") String studentId);


    }

}
