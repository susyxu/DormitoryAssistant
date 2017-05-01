package com.susy.dormitoryassistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.activity.WorkerRepairOrderActivity;
import com.susy.dormitoryassistant.activity.WorkerWaterOrderActivity;


/**
 * 外来人员端的主界面－1功能模块
 * Created by susy on 17/2/4.
 */
public class WorkerFunctionFragment extends Fragment implements View.OnClickListener {
    private LinearLayout ll_repair;
    private LinearLayout ll_water;
    private LinearLayout ll_suggest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_worker_function, container, false);

        ll_repair = (LinearLayout) rootView.findViewById(R.id.ll_repair);
        ll_water = (LinearLayout) rootView.findViewById(R.id.ll_water);
        ll_suggest = (LinearLayout) rootView.findViewById(R.id.ll_suggest);

        ll_repair.setOnClickListener(this);
        ll_water.setOnClickListener(this);
        ll_suggest.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_repair:
                Intent intent2 = new Intent(getActivity(), WorkerRepairOrderActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_water:
                Intent intent = new Intent(getActivity(), WorkerWaterOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_suggest:
                break;
        }
    }
}
