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

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.activity.StudentAddWaterActivity;
import com.susy.dormitoryassistant.adapter.WaterOrderAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.LoginStudent;
import com.susy.dormitoryassistant.entity.WaterOrder;
import com.susy.dormitoryassistant.http.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 学生端的主界面－2我的预定
 * Created by susy on 17/2/4.
 */
public class StudentSubscribeFragment extends Fragment implements View.OnClickListener{

    private LinearLayout ll_water;
    private LinearLayout ll_milk;
    private ListView lv_water;

    private List<WaterOrder> mWaterOrderList = new ArrayList<WaterOrder>();
    private WaterOrderAdapter mAdapter;
    private DormitoryApplication mApplication = null;

    /**
     * 刷新
     */
    public void update() {
        initListViewData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApplication = DormitoryApplication.getmInstance();
        View rootView = inflater.inflate(R.layout.fragment_student_subscribe, container, false);

        ll_water = (LinearLayout)rootView.findViewById(R.id.ll_water);
        ll_milk  = (LinearLayout)rootView.findViewById(R.id.ll_milk);
        lv_water = (ListView) rootView.findViewById(R.id.lv_water);
        ll_water.setOnClickListener(this);
        ll_milk.setOnClickListener(this);

        mAdapter = new WaterOrderAdapter(getContext(),mWaterOrderList);
        lv_water.setAdapter(mAdapter);

        if(mApplication.getGlobalStudent()!=null){
            initListViewData();
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private void initListViewData() {
        mWaterOrderList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<ArrayList<WaterOrder>> call = apiStores.studentGetWaterOrder(mApplication.getGlobalStudent().getData().getStudentId());
        call.enqueue(new Callback<ArrayList<WaterOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<WaterOrder>> call, Response<ArrayList<WaterOrder>> response) {
                if(response.body()!=null){
                    mWaterOrderList = response.body();
                    mAdapter.refresh(mWaterOrderList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WaterOrder>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_water:
                Intent intent = new Intent(getActivity(), StudentAddWaterActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_milk:
                break;
        }
    }
}
