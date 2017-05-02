package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.DisobeyOrders;
import com.susy.dormitoryassistant.entity.SaveDisobeyOrder;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/2.
 */

public class AdminAddDisobeyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private TextView tv_student;
    private Spinner sp_type;
    private EditText et_details;


    private DormitoryApplication mApplication = null;
    private String userId = "";
    private String studentId = "";
    private String studentName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_disobey);

        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);

        tv_title.setText("添加学生维修记录");
        tv_save.setText("添加");
        iv_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            studentId = intent.getStringExtra("studentId");
            studentName = intent.getStringExtra("studentName");
        }
        if (mApplication.getGlobalUser() != null) {
            userId = mApplication.getGlobalUser().getData().getId();
        }

        tv_student = (TextView) findViewById(R.id.tv_student);
        sp_type = (Spinner) findViewById(R.id.type);
        et_details = (EditText) findViewById(R.id.et_details);

        String[] items = getResources().getStringArray(R.array.disobeytype);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);

        tv_student.setText("违规同学:  " + studentId + " " + studentName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                AdminAddDisobeyActivity.this.finish();
                break;
            case R.id.tvSave:
                save();
                break;
        }
    }

    private void save() {
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveDisobeyOrder> call = apiStores.addDisobeyOrders(studentId, sp_type.getSelectedItem().toString(),
                userId, et_details.getText().toString());
        call.enqueue(new Callback<SaveDisobeyOrder>() {
            @Override
            public void onResponse(Call<SaveDisobeyOrder> call, Response<SaveDisobeyOrder> response) {
                if (response.body().getInfo().equals("添加成功")){
                    UtilTools.showToast(AdminAddDisobeyActivity.this, "添加违规成功");
                    AdminAddDisobeyActivity.this.finish();
                } else {
                    UtilTools.showToast(AdminAddDisobeyActivity.this, "添加违规失败");
                }
            }

            @Override
            public void onFailure(Call<SaveDisobeyOrder> call, Throwable t) {
                UtilTools.showToast(AdminAddDisobeyActivity.this, "添加违规失败");
            }
        });
    }
}
