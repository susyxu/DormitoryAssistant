package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.Student;
import com.susy.dormitoryassistant.http.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登入界面
 * Created by susy on 17/2/4.
 */
public class LoginActivity extends AppCompatActivity{

    private Button btn_studentLogin;
    private Button btn_adminLogin;
    private Button btn_workerLogin;

    private EditText et_username;
    private EditText et_pwd;
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_studentLogin = (Button)findViewById(R.id.btn_studentLogin);
        btn_studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainStudentActivity.class);
                startActivity(intent);
                //LoginActivity.this.finish();
            }
        });

        btn_adminLogin = (Button)findViewById(R.id.btn_adminLogin);
        btn_adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                startActivity(intent);
                //LoginActivity.this.finish();
            }
        });

        btn_workerLogin = (Button)findViewById(R.id.btn_workerLogin);
        btn_workerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainWorkerActivity.class);
                startActivity(intent);
                //LoginActivity.this.finish();
            }
        });


        //
        et_username = (EditText)findViewById(R.id.et_username);
        et_pwd = (EditText)findViewById(R.id.et_pwd);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStudent();
            }
        });
    }


    private void getStudent(){
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Student> call = apiStores.getStudent("31301215");
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Log.i("student", "getStudent=" + response.body().getStudentName());
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
            }
        });
    }
}
