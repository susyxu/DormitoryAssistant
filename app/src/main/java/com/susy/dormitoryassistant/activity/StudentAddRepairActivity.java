package com.susy.dormitoryassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.SaveRepairOrder;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/4/26.
 */

public class StudentAddRepairActivity extends AppCompatActivity implements View.OnClickListener{

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private TextView tv_dormCode;
    private Spinner sp_type;
    private RadioGroup rg;
    private RadioButton rb_WaterOne;
    private RadioButton rb_WaterTwo;
    private RadioButton rb_WaterThree;
    private EditText et_details;

    private String studentId="";
    private String freeTime="";
    private String dormitoryId="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_repair);

        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        tv_dormCode = (TextView) findViewById(R.id.tv_dormCode);
        iv_back = (ImageView) findViewById(R.id.back);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_WaterOne = (RadioButton) findViewById(R.id.rbNumOne);
        rb_WaterTwo = (RadioButton) findViewById(R.id.rbNumTwo);
        rb_WaterThree = (RadioButton) findViewById(R.id.rbNumThree);
        sp_type = (Spinner) findViewById(R.id.type);
        et_details = (EditText) findViewById(R.id.et_details);

        tv_title.setText("预约维修");
        tv_save.setText("预约");
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);


        if (mApplication.getGlobalStudent() != null) {
            tv_dormCode.setText("寝室号码:   "+mApplication.getGlobalStudent().getData().getDormitoryId());
            studentId=mApplication.getGlobalStudent().getData().getStudentId();
            dormitoryId=mApplication.getGlobalStudent().getData().getDormitoryId();
        }

        String[] items = getResources().getStringArray(R.array.repairtype);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                StudentAddRepairActivity.this.finish();
                break;
            case R.id.tvSave:
                save();
                break;
        }
    }

    private void save() {
        if (rb_WaterOne.isChecked()) {
            freeTime = "早上";
        } else if (rb_WaterTwo.isChecked()){
            freeTime = "中午";
        } else {
            freeTime = "下午";
        }
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveRepairOrder> call = apiStores.studentAddRepair(studentId,dormitoryId,
                String.valueOf(sp_type.getSelectedItemPosition()+1),
                et_details.getText().toString(),
                freeTime);
        call.enqueue(new Callback<SaveRepairOrder>() {
            @Override
            public void onResponse(Call<SaveRepairOrder> call, Response<SaveRepairOrder> response) {
                Log.i("saveAddReapir",response.body().getInfo());
                if(response.body().getInfo().equals("添加成功")){
                    UtilTools.showToast(StudentAddRepairActivity.this, "预约维修成功");
                    StudentAddRepairActivity.this.finish();
                } else {
                    UtilTools.showToast(StudentAddRepairActivity.this, "预约维修失败");
                }
            }

            @Override
            public void onFailure(Call<SaveRepairOrder> call, Throwable t) {
                UtilTools.showToast(StudentAddRepairActivity.this, "预约维修失败");
            }
        });

    }
}
