package com.susy.dormitoryassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.adapter.DisobeyAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.DisobeyOrder;
import com.susy.dormitoryassistant.entity.DisobeyOrders;
import com.susy.dormitoryassistant.http.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/2.
 */

public class StudentDisobeyActivity extends AppCompatActivity implements View.OnClickListener {

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private ListView lv_disobey;

    private String studentId="";
    private List<DisobeyOrder> mList = new ArrayList<>();
    private DisobeyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_student_disobey);
        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);

        tv_title.setText("我的违规记录");
        tv_save.setText("");
        iv_back.setOnClickListener(this);

        lv_disobey = (ListView) findViewById(R.id.lv_disobey);
        mAdapter = new DisobeyAdapter(StudentDisobeyActivity.this,mList);
        lv_disobey.setAdapter(mAdapter);

        if (mApplication.getGlobalStudent() != null) {
            studentId = mApplication.getGlobalStudent().getData().getStudentId();
            initDisobeyData();
        }
    }

    private void initDisobeyData() {
        mList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<DisobeyOrders> call = apiStores.getDisobeyOrders(studentId);
        call.enqueue(new Callback<DisobeyOrders>() {
            @Override
            public void onResponse(Call<DisobeyOrders> call, Response<DisobeyOrders> response) {
                if (response.body().getData()!=null){
                    mList = response.body().getData();
                    mAdapter.refresh(mList);
                }
            }

            @Override
            public void onFailure(Call<DisobeyOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                StudentDisobeyActivity.this.finish();
                break;
        }
    }
}
