package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;

/**
 * Created by susy on 17/5/1.
 */
public class WorkerAddCostActivity extends AppCompatActivity implements View.OnClickListener {
    private DormitoryApplication mApplication = null;

    private String dormitoryId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_cost);
        Intent intent = getIntent();
        if (intent != null) {
            dormitoryId = intent.getStringExtra("dormitoryId");
        }

    }

    @Override
    public void onClick(View v) {

    }
}
