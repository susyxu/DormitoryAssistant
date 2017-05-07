package com.susy.dormitoryassistant.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.activity.ChangePwdActivity;
import com.susy.dormitoryassistant.activity.MainStudentActivity;
import com.susy.dormitoryassistant.activity.StudentCostActivity;
import com.susy.dormitoryassistant.activity.StudentDisobeyActivity;
import com.susy.dormitoryassistant.activity.StudentScoreActivity;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.http.AppClient;


/**
 * 学生端的主界面－4个人信息
 * Created by susy on 17/2/4.
 */
public class StudentMeFragment extends Fragment implements View.OnClickListener {
    private TextView tv_name;
    private TextView tv_id;
    private LinearLayout ly_Wallet;
    private LinearLayout ly_Cost;
    private LinearLayout ly_Score;
    private LinearLayout ly_Disobey;
    private LinearLayout ly_Contact;
    private LinearLayout ly_ChangePwd;
    private LinearLayout ly_Exit;

    private DormitoryApplication mApplication = null;
    private String money = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApplication = DormitoryApplication.getmInstance();

        View rootView = inflater.inflate(R.layout.fragment_student_me, container, false);
        tv_name = (TextView) rootView.findViewById(R.id.tvName);
        tv_id = (TextView) rootView.findViewById(R.id.tvID);
        ly_Wallet = (LinearLayout) rootView.findViewById(R.id.linearMyWallet);
        ly_Cost = (LinearLayout) rootView.findViewById(R.id.linearMyCost);
        ly_Score = (LinearLayout) rootView.findViewById(R.id.linearMyScore);
        ly_Disobey = (LinearLayout) rootView.findViewById(R.id.linearMyDisobey);
        ly_Contact = (LinearLayout) rootView.findViewById(R.id.linearMyContact);
        ly_ChangePwd = (LinearLayout) rootView.findViewById(R.id.linearChangePwd);
        ly_Exit = (LinearLayout) rootView.findViewById(R.id.linearExit);

        if (mApplication.getGlobalStudent() != null) {
            tv_name.setText(mApplication.getGlobalStudent().getData().getStudentName());
            tv_id.setText("ID:" + mApplication.getGlobalStudent().getData().getStudentId());
            setWallet();
        }

        ly_Wallet.setOnClickListener(this);
        ly_Exit.setOnClickListener(this);
        ly_ChangePwd.setOnClickListener(this);
        ly_Disobey.setOnClickListener(this);
        ly_Contact.setOnClickListener(this);
        ly_Cost.setOnClickListener(this);
        ly_Score.setOnClickListener(this);

        return rootView;
    }

    /**
     * 获取用户余额
     */
    private void setWallet() {
        money = "100.00";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearExit:
                new MaterialDialog.Builder(getContext())
                        .title("系统提示")
                        .content("确定要退出吗？")
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                getActivity().finish();
                                System.exit(0);
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        })
                        .negativeText("取消")
                        .show();
                break;
            case R.id.linearMyWallet:
                new MaterialDialog.Builder(getContext())
                        .title("我的钱包")
                        .content("可用余额：" + money)
                        .positiveText("确定")
                        .show();
                break;
            case R.id.linearChangePwd:
                Intent intent = new Intent(getActivity(), ChangePwdActivity.class);
                intent.putExtra("userType","student");
                startActivity(intent);
                break;
            case R.id.linearMyDisobey:
                Intent intent2 = new Intent(getActivity(), StudentDisobeyActivity.class);
                startActivity(intent2);
                break;
            case R.id.linearMyContact:
                boolean wrapInScrollView = true;
                MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                        .title("电话本")
                        .customView(R.layout.dialog_contact, wrapInScrollView)
                        .show();
                View dialogView = dialog.getCustomView();
                LinearLayout lyTel1 = (LinearLayout) dialogView.findViewById(R.id.linearTel1);
                final TextView tvTel1 = (TextView) dialogView.findViewById(R.id.Tel1);
                lyTel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phone = tvTel1.getText().toString();
                        if (phone != null && phone.trim().length() > 0) {
                            //这里"tel:"+电话号码 是固定格式，系统一看是以"tel:"开头的，就知道后面应该是电话号码
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.trim()));
                            startActivity(intent);
                        }
                    }
                });
                LinearLayout lyTel2 = (LinearLayout) dialogView.findViewById(R.id.linearTel2);
                final TextView tvTel2 = (TextView) dialogView.findViewById(R.id.Tel2);
                lyTel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phone = tvTel2.getText().toString();
                        if (phone != null && phone.trim().length() > 0) {
                            //这里"tel:"+电话号码 是固定格式，系统一看是以"tel:"开头的，就知道后面应该是电话号码
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.trim()));
                            startActivity(intent);
                        }
                    }
                });
                LinearLayout lyTel3 = (LinearLayout) dialogView.findViewById(R.id.linearTel3);
                final TextView tvTel3 = (TextView) dialogView.findViewById(R.id.Tel3);
                lyTel3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phone = tvTel3.getText().toString();
                        if (phone != null && phone.trim().length() > 0) {
                            //这里"tel:"+电话号码 是固定格式，系统一看是以"tel:"开头的，就知道后面应该是电话号码
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.trim()));
                            startActivity(intent);
                        }
                    }
                });
                break;
            case R.id.linearMyCost:
                Intent intent3 = new Intent(getActivity(), StudentCostActivity.class);
                startActivity(intent3);
                break;
            case R.id.linearMyScore:
                Intent intent4 = new Intent(getActivity(), StudentScoreActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
