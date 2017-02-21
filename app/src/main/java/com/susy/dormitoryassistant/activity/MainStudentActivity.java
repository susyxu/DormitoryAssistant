package com.susy.dormitoryassistant.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.susy.dormitoryassistant.fragment.StudentMeFragment;
import com.susy.dormitoryassistant.fragment.StudentNewsFragment;
import com.susy.dormitoryassistant.fragment.StudentRepairFragment;
import com.susy.dormitoryassistant.fragment.StudentSubscribeFragment;
import com.susy.dormitoryassistant.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生端的主界面
 * Created by susy on 17/2/4.
 */
public class MainStudentActivity extends AppCompatActivity implements View.OnClickListener{
    private DormitoryApplication mApplication = null;
    private Context context = MainStudentActivity.this;

    private TextView tvTitle;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private CustomViewPager viewPager;
    private LinearLayout buttonLayout;

    //4个Tab，每个Tab包含一个按钮
    private LinearLayout mTabNews;
    private LinearLayout mTabSubscribe;
    private LinearLayout mTabRepair;
    private LinearLayout mTabMe;

    //4个按钮
    private ImageButton mNewsImg = null;
    private ImageButton mSubscribeImg = null;
    private ImageButton mRepairImg = null;
    private ImageButton mMeImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        mApplication = (DormitoryApplication)getApplication();

        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        viewPager = (CustomViewPager) findViewById(R.id.vp_horizontal_ntb);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("寝室公告");

        initUI();
        resetImg();
        mNewsImg.setImageResource(R.drawable.tab_news_checked);
    }

    private void initUI() {
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //获取屏幕宽、高
        int width = d.getWidth()/4;
        int pageIndex = 0;

        mTabNews = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_newsbutton, null);
        mTabNews.setOnClickListener(this);
        mTabNews.setTag(pageIndex);
        buttonLayout.addView(mTabNews, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mNewsImg = (ImageButton) mTabNews.findViewById(R.id.id_tab_news_img);
        fragments.add(new StudentNewsFragment());
        pageIndex++;

        mTabSubscribe = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_subscribebutton, null);
        mTabSubscribe.setOnClickListener(this);
        mTabSubscribe.setTag(pageIndex);
        buttonLayout.addView(mTabSubscribe, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mSubscribeImg = (ImageButton) mTabSubscribe.findViewById(R.id.id_tab_subscribe_img);
        fragments.add(new StudentSubscribeFragment());
        pageIndex++;

        mTabRepair = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_repairbutton, null);
        mTabRepair.setOnClickListener(this);
        mTabRepair.setTag(pageIndex);
        buttonLayout.addView(mTabRepair, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mRepairImg = (ImageButton) mTabRepair.findViewById(R.id.id_tab_repair_img);
        fragments.add(new StudentRepairFragment());
        pageIndex++;

        mTabMe = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_mebutton, null);
        mTabMe.setOnClickListener(this);
        mTabMe.setTag(pageIndex);
        buttonLayout.addView(mTabMe, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mMeImg = (ImageButton) mTabMe.findViewById(R.id.id_tab_me_img);
        fragments.add(new StudentMeFragment());
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
            case R.id.id_tab_news:
                tvTitle.setText(R.string.title_news);
                viewPager.setCurrentItem((int) mTabNews.getTag());
                resetImg();
                mNewsImg.setImageResource(R.drawable.tab_news_checked);
                break;
            case R.id.id_tab_subscribe:
                tvTitle.setText(R.string.title_subscribe);
                viewPager.setCurrentItem((int) mTabSubscribe.getTag());
                resetImg();
                mSubscribeImg.setImageResource(R.drawable.tab_subscribe_checked);
                break;
            case R.id.id_tab_repair:
                tvTitle.setText(R.string.title_repair);
                viewPager.setCurrentItem((int) mTabRepair.getTag());
                resetImg();
                mRepairImg.setImageResource(R.drawable.tab_repair_checked);
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
                            MainStudentActivity.this.finish();
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
        if (mNewsImg != null) {
            mNewsImg.setImageResource(R.drawable.tab_news_normal);
        }
        if (mSubscribeImg != null) {
            mSubscribeImg.setImageResource(R.drawable.tab_subscribe_normal);
        }
        if (mRepairImg != null) {
            mRepairImg.setImageResource(R.drawable.tab_repair_normal);
        }
        if (mMeImg != null) {
            mMeImg.setImageResource(R.drawable.tab_me_normal);
        }
    }
}
