package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.LoginStudent;
import com.susy.dormitoryassistant.entity.LoginUser;
import com.susy.dormitoryassistant.entity.Student;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登入界面
 * Created by susy on 17/2/4.
 */
public class LoginActivity extends AppCompatActivity {

    private Spinner sp_type;
    private EditText et_username;
    private EditText et_pwd;
    private Button btn_login;

    private String type = "";
    private DormitoryApplication mApplication = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_login2);

        //正式代码
        sp_type = (Spinner) findViewById(R.id.sp_type);
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        String[] items = getResources().getStringArray(R.array.type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_type.getSelectedItem().toString().equals("学生")) {
                    type = "student";
                }
                if (sp_type.getSelectedItem().toString().equals("寝室管理员")) {
                    type = "user";
                }
                if (sp_type.getSelectedItem().toString().equals("外来工作人员")) {
                    type = "user";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = "";
            }
        });

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getStudent();   测试用
                if (et_username.getText().toString().equals("")) {
                    UtilTools.showToast(LoginActivity.this, "请输入用户名");
                } else if (et_pwd.getText().toString().equals("")) {
                    UtilTools.showToast(LoginActivity.this, "请输入密码");
                } else {
                    login();
                }
            }
        });
    }

    /**
     * 和服务器交互，登入
     */
    private void login() {
        String username = "";
        String password = "";
        username = et_username.getText().toString();
        password = et_pwd.getText().toString();

        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);

        if (type.equals("student")) {
            Call<LoginStudent> call = apiStores.appStudentLogin(type, username, password);
            call.enqueue(new Callback<LoginStudent>() {
                @Override
                public void onResponse(Call<LoginStudent> call, Response<LoginStudent> response) {
                    Log.i("PrintSLoginInfo", response.body().getInfo());
                    if (response.body().getInfo().equals("学生登陆成功")) {
                        LoginStudent globalStudent = response.body();
                        mApplication.setGlobalStudent(globalStudent);
                        startApp(sp_type.getSelectedItem().toString());
                    } else {
                        UtilTools.showToast(LoginActivity.this, response.body().getInfo());
                    }
                }

                @Override
                public void onFailure(Call<LoginStudent> call, Throwable t) {
                    UtilTools.showToast(LoginActivity.this, "学生登陆失败");
                }
            });
        }
        if (type.equals("user")) {
            Call<LoginUser> call = apiStores.appUserLogin(type, username, password);
            call.enqueue(new Callback<LoginUser>() {
                @Override
                public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                    Log.i("PrintULoginInfo", response.body().getInfo());
                    if (response.body().getInfo().equals("用户登陆成功")) {
                        LoginUser globalUser = response.body();
                        mApplication.setGlobalUser(globalUser);
                        startApp(sp_type.getSelectedItem().toString());
                    } else {
                        UtilTools.showToast(LoginActivity.this, response.body().getInfo());
                    }
                }

                @Override
                public void onFailure(Call<LoginUser> call, Throwable t) {
                    UtilTools.showToast(LoginActivity.this, "用户登陆失败");
                }
            });
        }
    }

    /**
     * 启动对应角色的app
     */
    private void startApp(String type) {
        if (type.equals("学生")) {
            Intent intent = new Intent(LoginActivity.this, MainStudentActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        if (type.equals("寝室管理员")) {
            Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        if (type.equals("外来工作人员")) {
            Intent intent = new Intent(LoginActivity.this, MainWorkerActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }
}
