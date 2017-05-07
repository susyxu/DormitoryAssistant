package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.SaveScore;
import com.susy.dormitoryassistant.entity.Score;
import com.susy.dormitoryassistant.entity.Scores;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/1.
 */
public class AdminAddScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private TextView tv_dormCode;
    private SimpleRatingBar rbBed;
    private SimpleRatingBar rbfloorClean;
    private SimpleRatingBar rbdeskClean;
    private SimpleRatingBar rbwcClean;
    private SimpleRatingBar rbbalconyClean;

    private String dormitoryId = "";
    private String userId = "";
    private int flag = 0;

    private List<Score> mAllAcoreList = new ArrayList<>();
    private Score nowScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_score);

        tv_dormCode = (TextView) findViewById(R.id.tv_dormCode);

        Intent intent = getIntent();
        if (intent != null) {
            dormitoryId = intent.getStringExtra("dormitoryId");
        }

        if (mApplication.getGlobalUser() != null) {
            userId = mApplication.getGlobalUser().getData().getId();
            tv_dormCode.setText("寝室号码:   " + dormitoryId);
            getScoreThisWeek();
        }

        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);
        tv_title.setText("添加寝室评分");
        tv_save.setText("添加");
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        rbBed = (SimpleRatingBar) findViewById(R.id.bedClean);
        rbfloorClean = (SimpleRatingBar) findViewById(R.id.floorClean);
        rbdeskClean = (SimpleRatingBar) findViewById(R.id.deskClean);
        rbwcClean = (SimpleRatingBar) findViewById(R.id.wcClean);
        rbbalconyClean = (SimpleRatingBar) findViewById(R.id.balconyClean);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                AdminAddScoreActivity.this.finish();
                break;
            case R.id.tvSave:
                save();
                break;
        }
    }

    private void getScoreThisWeek() {
        flag = 0;
        final String week = UtilTools.getCurrentWeekOfYear();
        mAllAcoreList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Scores> call = apiStores.getDormScore(dormitoryId);
        call.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (response.body().getData() != null) {
                    mAllAcoreList = response.body().getData();
                    for (Score s : mAllAcoreList) {
                        if (s.getWeek().equals(week)) {
                            flag = 1;
                            nowScore = s;
                            break;
                        }
                    }
                }
                if (flag == 1) {
                    tv_save.setText("已评分");
                    tv_save.setTextColor(Color.parseColor("#88ffffff"));
                    rbBed.setIndicator(true);
                    rbfloorClean.setIndicator(true);
                    rbdeskClean.setIndicator(true);
                    rbwcClean.setIndicator(true);
                    rbbalconyClean.setIndicator(true);
                    tv_save.setEnabled(false);

                    rbBed.setRating(Float.parseFloat(nowScore.getBedClean()));
                    rbfloorClean.setRating(Float.parseFloat(nowScore.getFloorClean()));
                    rbdeskClean.setRating(Float.parseFloat(nowScore.getDeskClean()));
                    rbwcClean.setRating(Float.parseFloat(nowScore.getWcClean()));
                    rbbalconyClean.setRating(Float.parseFloat(nowScore.getBalconyClean()));
                }
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {
                UtilTools.showToast(AdminAddScoreActivity.this, "获取数据失败");
            }
        });
    }

    private void save() {
        int rate1 = (int) rbBed.getRating();
        int rate2 = (int) rbfloorClean.getRating();
        int rate3 = (int) rbdeskClean.getRating();
        int rate4 = (int) rbwcClean.getRating();
        int rate5 = (int) rbbalconyClean.getRating();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveScore> call = apiStores.addDormScore(userId, dormitoryId,
                String.valueOf(rate1), String.valueOf(rate2), String.valueOf(rate3),
                String.valueOf(rate4), String.valueOf(rate5));
        call.enqueue(new Callback<SaveScore>() {
            @Override
            public void onResponse(Call<SaveScore> call, Response<SaveScore> response) {
                if (response.body() != null && response.body().getData() != null) {
                    if (response.body().getInfo().equals("添加成功")) {
                        UtilTools.showToast(AdminAddScoreActivity.this, "评分成功");
                        AdminAddScoreActivity.this.finish();
                    } else {
                        UtilTools.showToast(AdminAddScoreActivity.this, "评分失败");
                    }
                } else {
                    UtilTools.showToast(AdminAddScoreActivity.this, "评分失败");
                }
            }

            @Override
            public void onFailure(Call<SaveScore> call, Throwable t) {
                UtilTools.showToast(AdminAddScoreActivity.this, "评分失败");
            }
        });
    }

}
