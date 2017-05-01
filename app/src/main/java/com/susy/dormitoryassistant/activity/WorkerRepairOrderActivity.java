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
import com.susy.dormitoryassistant.adapter.RepairOrderWorkerAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.RepairOrder;
import com.susy.dormitoryassistant.entity.RepairOrders;
import com.susy.dormitoryassistant.entity.SaveRepairOrder;
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

public class WorkerRepairOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private DormitoryApplication mApplication = null;

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;

    private ListView lv_repairOrder;
    private RepairOrders mOrders = new RepairOrders();
    private List<RepairOrder> mRepairOrderList = new ArrayList<RepairOrder>();
    private RepairOrderWorkerAdapter mAdapter;

    private String userId = "";
    private String repairOrderId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_worker_repairorder);
        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        iv_back = (ImageView) findViewById(R.id.back);

        tv_title.setText("今日维修");
        tv_save.setText("");
        iv_back.setOnClickListener(this);

        lv_repairOrder = (ListView) findViewById(R.id.lv_repairOrder);
        mAdapter = new RepairOrderWorkerAdapter(WorkerRepairOrderActivity.this, mRepairOrderList, mListener);
        lv_repairOrder.setAdapter(mAdapter);

        if (mApplication.getGlobalUser() != null) {
            initListViewData();
            userId = mApplication.getGlobalUser().getData().getId();
        }

    }

    private void initListViewData() {
        mRepairOrderList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<RepairOrders> call = apiStores.workerGetRepairOrder(mApplication.getGlobalUser().getData().getId());
        call.enqueue(new Callback<RepairOrders>() {
            @Override
            public void onResponse(Call<RepairOrders> call, Response<RepairOrders> response) {
                if (response.body().getData() != null) {
                    mOrders = response.body();
                    mRepairOrderList = mOrders.getData();
                    mAdapter.refresh(mRepairOrderList);
                }
            }

            @Override
            public void onFailure(Call<RepairOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                WorkerRepairOrderActivity.this.finish();
                break;
        }
    }

    private RepairOrderWorkerAdapter.MyClickListener mListener = new RepairOrderWorkerAdapter.MyClickListener() {
        @Override
        public void myOnClick(final int position, View v) {
            new MaterialDialog.Builder(WorkerRepairOrderActivity.this)
                    .title("维修确认")
                    .content("您确定已完成维修吗？")
                    .positiveText("确定")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            repairOrderId = mRepairOrderList.get(position).getRepairOrderId();
                            finishOrder(repairOrderId);
                        }
                    })
                    .negativeText("取消")
                    .show();
        }
    };

    private void finishOrder(String repairOrderId) {
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveRepairOrder> call = apiStores.workerFinishRepairOrder(userId,repairOrderId);
        call.enqueue(new Callback<SaveRepairOrder>() {
            @Override
            public void onResponse(Call<SaveRepairOrder> call, Response<SaveRepairOrder> response) {
                if (response.body().getInfo().equals("订单已完成")){
                    UtilTools.showToast(WorkerRepairOrderActivity.this, "订单已完成");
                    initListViewData();
                }
            }

            @Override
            public void onFailure(Call<SaveRepairOrder> call, Throwable t) {
                UtilTools.showToast(WorkerRepairOrderActivity.this, "订单完成失败");
            }
        });
    }
}
