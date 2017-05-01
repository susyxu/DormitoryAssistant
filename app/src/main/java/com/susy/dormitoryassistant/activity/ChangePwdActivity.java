package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.LoginStudent;
import com.susy.dormitoryassistant.entity.LoginUser;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/1.
 */

public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener {

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private EditText et_oldPwd;
    private EditText et_newPwd;
    private EditText et_newPwd2;

    private String studentId = "";
    private String userId = "";
    private String userType = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_changepwd);

        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);
        tv_title.setText("修改密码");
        tv_save.setText("确认");
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        et_oldPwd = (EditText) findViewById(R.id.et_oldpwd);
        et_newPwd = (EditText) findViewById(R.id.et_newPwd);
        et_newPwd2 = (EditText) findViewById(R.id.et_newPwd2);

        Intent intent = getIntent();
        if (intent != null) {
            userType = intent.getStringExtra("userType");
        }

        if (mApplication.getGlobalStudent() != null) {
            studentId = mApplication.getGlobalStudent().getData().getStudentId();
        }
        if (mApplication.getGlobalUser() != null) {
            userId = mApplication.getGlobalUser().getData().getId();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                ChangePwdActivity.this.finish();
                break;
            case R.id.tvSave:
                if (et_oldPwd.getText().toString().equals("")) {
                    UtilTools.showToast(ChangePwdActivity.this, "请输入旧密码");
                } else if (et_newPwd.getText().toString().equals("")) {
                    UtilTools.showToast(ChangePwdActivity.this, "请输入新密码");
                } else if (et_newPwd.getText().toString().equals(et_newPwd2.getText().toString()) == false) {
                    UtilTools.showToast(ChangePwdActivity.this, "两次输入新密码不符");
                } else {
                    save();
                }
                break;
        }
    }

    private void save() {
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        if (userType.equals("student")) {
            Call<LoginStudent> call = apiStores.changeStudentPwd(userType, studentId,
                    et_oldPwd.getText().toString(),
                    et_newPwd.getText().toString());
            call.enqueue(new Callback<LoginStudent>() {
                @Override
                public void onResponse(Call<LoginStudent> call, Response<LoginStudent> response) {
                    LoginStudent student = response.body();
                    UtilTools.showToast(ChangePwdActivity.this, response.body().getInfo());
                    if (response.body().getInfo().equals("修改成功")) {
                        ChangePwdActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<LoginStudent> call, Throwable t) {
                    UtilTools.showToast(ChangePwdActivity.this, "修改密码失败");
                }
            });
        } else if (userType.equals("user")) {
            Call<LoginUser> call = apiStores.changeUserPwd(userType, userId,
                    et_oldPwd.getText().toString(),
                    et_newPwd.getText().toString());
            call.enqueue(new Callback<LoginUser>() {
                @Override
                public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                    LoginUser user = response.body();
                    UtilTools.showToast(ChangePwdActivity.this, response.body().getInfo());
                    if (response.body().getInfo().equals("修改成功")) {
                        ChangePwdActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<LoginUser> call, Throwable t) {
                    UtilTools.showToast(ChangePwdActivity.this, "修改密码失败");
                }
            });

        }

    }
}
