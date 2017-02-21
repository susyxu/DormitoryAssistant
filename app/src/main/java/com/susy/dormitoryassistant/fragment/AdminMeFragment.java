package com.susy.dormitoryassistant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.susy.dormitoryassistant.R;


/**
 * 管理员端的主界面－5个人信息
 * Created by susy on 17/2/4.
 */
public class AdminMeFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_me, container, false);
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }
}
