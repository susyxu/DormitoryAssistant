package com.susy.dormitoryassistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.activity.StudentAddRepairActivity;
import com.susy.dormitoryassistant.adapter.RepairOrderAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.RepairOrder;
import com.susy.dormitoryassistant.entity.RepairOrders;
import com.susy.dormitoryassistant.http.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 学生端的主界面－3寝室维修
 * Created by susy on 17/2/4.
 */
public class StudentRepairFragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_repair;
    private LinearLayout ll_suggest;
    private ListView lv_repair;

    private RepairOrders mOrders = new RepairOrders();
    private List<RepairOrder> mOrderList = new ArrayList<RepairOrder>();
    private RepairOrderAdapter mAdapter;
    private DormitoryApplication mApplication = null;

    private String studentId = "";
    private String dormitoryId = "";

    /**
     * 刷新
     */
    public void update() {
        if (mApplication.getGlobalStudent() != null) {
            initListViewData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApplication = DormitoryApplication.getmInstance();
        View rootView = inflater.inflate(R.layout.fragment_student_repair, container, false);
        ll_repair = (LinearLayout) rootView.findViewById(R.id.ll_repair);
        ll_repair.setOnClickListener(this);
        ll_suggest = (LinearLayout) rootView.findViewById(R.id.ll_suggest);
        ll_suggest.setOnClickListener(this);

        if (mApplication.getGlobalStudent() != null) {
            studentId = mApplication.getGlobalStudent().getData().getStudentId();
            dormitoryId = mApplication.getGlobalStudent().getData().getDormitoryId();
            initListViewData();
        }

        lv_repair = (ListView) rootView.findViewById(R.id.lv_repair);
        mAdapter = new RepairOrderAdapter(getActivity(), mOrderList);
        lv_repair.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private void initListViewData() {
        mOrderList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<RepairOrders> call = apiStores.studentGetRepairOrder(studentId);
        call.enqueue(new Callback<RepairOrders>() {
            @Override
            public void onResponse(Call<RepairOrders> call, Response<RepairOrders> response) {
                mOrders = response.body();
                if (mOrders.getData() != null) {
                    mOrderList = mOrders.getData();
                    mAdapter.refresh(mOrderList);
                }
            }

            @Override
            public void onFailure(Call<RepairOrders> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_repair:
                Intent intent = new Intent(getActivity(), StudentAddRepairActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_suggest:
                new MaterialDialog.Builder(getActivity())
                        .title("意见反馈")
                        .positiveText("提交")
                        .inputRangeRes(5, 20, R.color.colorAccent)
                        .input(null, null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                            }
                        }).show();
                break;
        }
    }
}
