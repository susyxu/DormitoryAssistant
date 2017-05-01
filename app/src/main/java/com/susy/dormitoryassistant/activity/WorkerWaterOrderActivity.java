package com.susy.dormitoryassistant.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.adapter.WaterOrderWorkerAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.SaveWaterOrder;
import com.susy.dormitoryassistant.entity.WaterOrder;
import com.susy.dormitoryassistant.entity.WaterOrders;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/5/1.
 */

public class WorkerWaterOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;

    private ListView lv_waterOrder;
    private WaterOrders mOrders = new WaterOrders();
    private List<WaterOrder> mWaterOrderList = new ArrayList<WaterOrder>();
    private WaterOrderWorkerAdapter mAdapter;

    private String userId = "";
    private String waterOrderId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_worker_waterorder);
        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);

        tv_title.setText("今日送水");
        tv_save.setText("");
        iv_back.setOnClickListener(this);

        lv_waterOrder = (ListView) findViewById(R.id.lv_waterOrder);
        mAdapter = new WaterOrderWorkerAdapter(WorkerWaterOrderActivity.this, mWaterOrderList, mListener);
        lv_waterOrder.setAdapter(mAdapter);

        if (mApplication.getGlobalUser() != null) {
            initListViewData();
            userId = mApplication.getGlobalUser().getData().getId();
        }

    }

    private void initListViewData() {
        mWaterOrderList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<WaterOrders> call = apiStores.workerGetWaterOrder(mApplication.getGlobalUser().getData().getId());
        call.enqueue(new Callback<WaterOrders>() {
            @Override
            public void onResponse(Call<WaterOrders> call, Response<WaterOrders> response) {
                if (response.body().getData() != null) {
                    mOrders = response.body();
                    mWaterOrderList = mOrders.getData();
                    mAdapter.refresh(mWaterOrderList);
                }
            }

            @Override
            public void onFailure(Call<WaterOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                WorkerWaterOrderActivity.this.finish();
                break;
        }
    }

    private WaterOrderWorkerAdapter.MyClickListener mListener = new WaterOrderWorkerAdapter.MyClickListener() {
        @Override
        public void myOnClick(final int position, View v) {
            new MaterialDialog.Builder(WorkerWaterOrderActivity.this)
                    .title("送水确认")
                    .content("您确定已完成送水吗？")
                    .positiveText("确定")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            waterOrderId = mWaterOrderList.get(position).getWaterOrderId();
                            finishOrder(waterOrderId);
                        }
                    })
                    .negativeText("取消")
                    .show();
        }
    };

    private void finishOrder(String waterOrderId) {
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveWaterOrder> call = apiStores.workerFinishWaterOrder(userId, waterOrderId);
        call.enqueue(new Callback<SaveWaterOrder>() {
            @Override
            public void onResponse(Call<SaveWaterOrder> call, Response<SaveWaterOrder> response) {
                if (response.body().getInfo().equals("订单已完成")) {
                    UtilTools.showToast(WorkerWaterOrderActivity.this, "订单已完成");
                    initListViewData();
                }
            }

            @Override
            public void onFailure(Call<SaveWaterOrder> call, Throwable t) {
                UtilTools.showToast(WorkerWaterOrderActivity.this, "订单完成失败");
            }
        });
    }
}
