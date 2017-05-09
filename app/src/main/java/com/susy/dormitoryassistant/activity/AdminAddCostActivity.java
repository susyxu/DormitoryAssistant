package com.susy.dormitoryassistant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.Costs;
import com.susy.dormitoryassistant.entity.SaveCost;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/1.
 */
public class AdminAddCostActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private EditText et_water;
    private EditText et_elec;
    private TextView tv_dormCode;

    private DormitoryApplication mApplication = null;

    private String dormitoryId = "";
    private String userId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_cost);

        tv_dormCode = (TextView) findViewById(R.id.tv_dormCode);

        Intent intent = getIntent();
        if (intent != null) {
            dormitoryId = intent.getStringExtra("dormitoryId");
        }
        if (mApplication.getGlobalUser() != null) {
            userId = mApplication.getGlobalUser().getData().getId();
            tv_dormCode.setText("寝室号码:   " + dormitoryId);
            getWaterCostThisQ();
        }

        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);
        et_water = (EditText) findViewById(R.id.et_water);
        et_elec = (EditText) findViewById(R.id.et_elec);

        tv_title.setText("添加水电费");
        tv_save.setText("提交");
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                AdminAddCostActivity.this.finish();
                break;
            case R.id.tvSave:
                if (et_water.getText().toString() != null && et_elec.getText().toString() != null) {
                    save();
                    AdminAddCostActivity.this.finish();
                } else {
                    UtilTools.showToast(AdminAddCostActivity.this, "请输入完整信息");
                }
                break;
        }
    }

    private boolean getWaterCostThisQ(){
        String qOfYear = UtilTools.getTimeAndQ();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Costs> call = apiStores.getDormCostByQ(dormitoryId,qOfYear);
        call.enqueue(new Callback<Costs>() {
            @Override
            public void onResponse(Call<Costs> call, Response<Costs> response) {
                if (response.body().getData()!=null){
                    tv_save.setText("已提交");
                    tv_save.setTextColor(Color.parseColor("#88ffffff"));
                    tv_save.setEnabled(false);
                    et_water.setEnabled(false);
                    et_elec.setEnabled(false);
                    et_water.setText(response.body().getData().get(0).getWaterCount());
                    et_elec.setText(response.body().getData().get(0).getElecCount());
                }
            }

            @Override
            public void onFailure(Call<Costs> call, Throwable t) {

            }
        });
        return false;
    }

    private void save() {
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveCost> call = apiStores.addDormCost(userId, dormitoryId,
                et_water.getText().toString(),
                et_elec.getText().toString());
        call.enqueue(new Callback<SaveCost>() {
            @Override
            public void onResponse(Call<SaveCost> call, Response<SaveCost> response) {
                if (response.body() != null && response.body().getData() != null) {
                    if (response.body().getInfo().equals("添加成功")) {
                        UtilTools.showToast(AdminAddCostActivity.this, "添加水电费成功");
                    } else {
                        UtilTools.showToast(AdminAddCostActivity.this, "添加水电费失败");
                    }
                } else {
                    UtilTools.showToast(AdminAddCostActivity.this, "添加水电费失败");
                }
            }

            @Override
            public void onFailure(Call<SaveCost> call, Throwable t) {
                UtilTools.showToast(AdminAddCostActivity.this, "添加水电费失败");
            }
        });
    }
}
