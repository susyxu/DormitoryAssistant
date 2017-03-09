package com.susy.dormitoryassistant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.adapter.DormGridViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 管理员端的主界面－3水电费
 * Created by susy on 17/2/4.
 */
public class AdminCostFragment extends Fragment implements View.OnClickListener{
    private GridView gv_dormList;
    private List<String> dormList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_cost, container, false);
        gv_dormList = (GridView) rootView.findViewById(R.id.gv_dormList);

        getDorm();

        DormGridViewAdapter dormGridViewAdapter = new DormGridViewAdapter(getActivity(),dormList);
        gv_dormList.setAdapter(dormGridViewAdapter);
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    public void getDorm() {
        dormList.add("001");
        dormList.add("002");
        dormList.add("003");
        dormList.add("004");
        dormList.add("005");
        dormList.add("006");
    }
}
