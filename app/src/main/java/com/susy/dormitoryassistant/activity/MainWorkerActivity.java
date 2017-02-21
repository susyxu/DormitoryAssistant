package com.susy.dormitoryassistant.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.adapter.FragmentAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.fragment.WorkerFunctionFragment;
import com.susy.dormitoryassistant.fragment.WorkerMeFragment;
import com.susy.dormitoryassistant.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 外来人员端的主界面
 * Created by susy on 17/2/4.
 */
public class MainWorkerActivity extends AppCompatActivity implements View.OnClickListener {
    private DormitoryApplication mApplication = null;
    private Context context = MainWorkerActivity.this;

    private TextView tvTitle;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private CustomViewPager viewPager;
    private LinearLayout buttonLayout;

    //2个Tab，每个Tab包含一个按钮
    private LinearLayout mTabFunction;
    private LinearLayout mTabMe;

    //2个按钮
    private ImageButton mFunctionImg = null;
    private ImageButton mMeImg = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        mApplication = (DormitoryApplication)getApplication();

        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        viewPager = (CustomViewPager) findViewById(R.id.vp_horizontal_ntb);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("功能模块");

        initUI();
        resetImg();
        mFunctionImg.setImageResource(R.drawable.tab_function_checked);
    }

    private void initUI(){
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //获取屏幕宽、高
        int width = d.getWidth()/2;
        int pageIndex = 0;

        mTabFunction = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_functionbutton, null);
        mTabFunction.setOnClickListener(this);
        mTabFunction.setTag(pageIndex);
        buttonLayout.addView(mTabFunction, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mFunctionImg = (ImageButton) mTabFunction.findViewById(R.id.id_tab_function_img);
        fragments.add(new WorkerFunctionFragment());
        pageIndex++;

        mTabMe = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_mebutton, null);
        mTabMe.setOnClickListener(this);
        mTabMe.setTag(pageIndex);
        buttonLayout.addView(mTabMe, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mMeImg = (ImageButton) mTabMe.findViewById(R.id.id_tab_me_img);
        fragments.add(new WorkerMeFragment());
        pageIndex++;

        FragmentAdapter adapter = new FragmentAdapter(
                getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setScanScroll(false);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_tab_function:
                tvTitle.setText(R.string.title_function);
                viewPager.setCurrentItem((int) mTabFunction.getTag());
                resetImg();
                mFunctionImg.setImageResource(R.drawable.tab_function_checked);
                break;
            case R.id.id_tab_me:
                tvTitle.setText(R.string.title_me);
                viewPager.setCurrentItem((int) mTabMe.getTag());
                resetImg();
                mMeImg.setImageResource(R.drawable.tab_me_checked);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new MaterialDialog.Builder(context)
                    .title("系统提示")
                    .content("确定要退出吗？")
                    .positiveText("确定")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            MainWorkerActivity.this.finish();
                            System.exit(0);
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    })
                    .negativeText("取消")
                    .show();
        }
        return false;
    }

    /**
     * 把所有图片变暗
     */
    private void resetImg() {
        if (mFunctionImg != null) {
            mFunctionImg.setImageResource(R.drawable.tab_function_normal);
        }
        if (mMeImg != null) {
            mMeImg.setImageResource(R.drawable.tab_me_normal);
        }
    }
}
