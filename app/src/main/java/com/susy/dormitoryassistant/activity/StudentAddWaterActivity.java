package com.susy.dormitoryassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.SaveWaterOrder;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 学生订水
 * Created by susy on 17/3/12.
 */

public class StudentAddWaterActivity extends AppCompatActivity implements View.OnClickListener {

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private TextView tv_dormCode;
    private TextView tv_money;
    private RadioGroup rg;
    private RadioButton rb_WaterOne;
    private RadioButton rb_WaterTwo;

    private String studentId="";
    private String count="";
    private String dormitoryId="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_water);
        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        tv_dormCode = (TextView) findViewById(R.id.tv_dormCode);
        tv_money = (TextView) findViewById(R.id.tv_money);
        iv_back = (ImageView) findViewById(R.id.back);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_WaterOne = (RadioButton) findViewById(R.id.rbNumOne);
        rb_WaterTwo = (RadioButton) findViewById(R.id.rbNumTwo);

        tv_title.setText("预定饮用水");
        tv_save.setText("预定");
        tv_money.setText("总计金额: 10.00元");
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        if (mApplication.getGlobalStudent() != null) {
            tv_dormCode.setText("寝室号码: "+mApplication.getGlobalStudent().getData().getDormitoryId());
            studentId=mApplication.getGlobalStudent().getData().getStudentId();
            dormitoryId=mApplication.getGlobalStudent().getData().getDormitoryId();
        }


        //根据rb选项的check改变textview handler
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbNumOne){
                    tv_money.setText("总计金额: 10.00元");
                } else if(checkedId==R.id.rbNumTwo){
                    tv_money.setText("总计金额: 20.00元");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                StudentAddWaterActivity.this.finish();
                break;
            case R.id.tvSave:
                save();
                break;
        }
    }

    private void save() {
        if (rb_WaterOne.isChecked()) {
            count = "1";
        } else {
            count = "2";
        }
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveWaterOrder> call = apiStores.studentAddwater(studentId,count,dormitoryId);
        call.enqueue(new Callback<SaveWaterOrder>() {
            @Override
            public void onResponse(Call<SaveWaterOrder> call, Response<SaveWaterOrder> response) {
                Log.i("saveAddWater",response.body().getInfo());
                if(response.body().getInfo().equals("添加成功")){
                    UtilTools.showToast(StudentAddWaterActivity.this, "预定饮用水成功");
                    StudentAddWaterActivity.this.finish();
                } else {
                    UtilTools.showToast(StudentAddWaterActivity.this, "预定饮用水失败");
                }

            }
            @Override
            public void onFailure(Call<SaveWaterOrder> call, Throwable t) {
                UtilTools.showToast(StudentAddWaterActivity.this, "预定饮用水失败");
            }
        });
    }
}
