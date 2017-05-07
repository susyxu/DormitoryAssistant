package com.susy.dormitoryassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.adapter.ScoreAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.Score;
import com.susy.dormitoryassistant.entity.Scores;
import com.susy.dormitoryassistant.http.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/2.
 */

public class StudentScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private ListView lv_cost;

    private String dormId="";
    private List<Score> mList = new ArrayList<>();
    private ScoreAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_student_cost);
        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);

        tv_title.setText("我的寝室评分");
        tv_save.setText("");
        iv_back.setOnClickListener(this);

        lv_cost = (ListView) findViewById(R.id.lv_cost);
        mAdapter = new ScoreAdapter(StudentScoreActivity.this,mList);
        lv_cost.setAdapter(mAdapter);

        if (mApplication.getGlobalStudent() != null) {
            dormId = mApplication.getGlobalStudent().getData().getDormitoryId();
            initDisobeyData();
        }
    }

    private void initDisobeyData() {
        mList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Scores> call = apiStores.getDormScore(dormId);
        call.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (response.body().getData()!=null){
                    mList = response.body().getData();
                    mAdapter.refresh(mList);
                }
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                StudentScoreActivity.this.finish();
                break;
        }
    }
}
