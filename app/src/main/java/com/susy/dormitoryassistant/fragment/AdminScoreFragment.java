package com.susy.dormitoryassistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.activity.ChangePwdActivity;
import com.susy.dormitoryassistant.activity.WorkerAddScoreActivity;
import com.susy.dormitoryassistant.adapter.DormGridViewAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.Dorms;
import com.susy.dormitoryassistant.http.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 管理员端的主界面－2寝室评分
 * Created by susy on 17/2/4.
 */
public class AdminScoreFragment extends Fragment implements View.OnClickListener{

    private GridView gv_dormList;
    private DormGridViewAdapter dormGridViewAdapter;
    private Dorms dorms = new Dorms();
    private List<String> dormList = new ArrayList<>();

    private DormitoryApplication mApplication = null;
    private String userId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApplication = DormitoryApplication.getmInstance();
        View rootView = inflater.inflate(R.layout.fragment_admin_score, container, false);
        gv_dormList = (GridView) rootView.findViewById(R.id.gv_dormList);

        if (mApplication.getGlobalUser() != null) {
            userId = mApplication.getGlobalUser().getData().getId();
            initDormList();
        }

        dormGridViewAdapter = new DormGridViewAdapter(getActivity(), dormList);
        gv_dormList.setAdapter(dormGridViewAdapter);
        gv_dormList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WorkerAddScoreActivity.class);
                intent.putExtra("dormitoryId",dormList.get(position));
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    public void initDormList() {
        dormList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Dorms> call = apiStores.adminGetDormList(userId);
        call.enqueue(new Callback<Dorms>() {
            @Override
            public void onResponse(Call<Dorms> call, Response<Dorms> response) {
                dorms = response.body();
                if (dorms.getData() != null) {
                    dormList = dorms.getData();
                    dormGridViewAdapter.refresh(dormList);
                }
            }

            @Override
            public void onFailure(Call<Dorms> call, Throwable t) {

            }
        });
    }
}
