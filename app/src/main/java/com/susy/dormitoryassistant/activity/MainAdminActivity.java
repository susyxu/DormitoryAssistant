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
import com.susy.dormitoryassistant.fragment.AdminContactFragment;
import com.susy.dormitoryassistant.fragment.AdminCostFragment;
import com.susy.dormitoryassistant.fragment.AdminMeFragment;
import com.susy.dormitoryassistant.fragment.AdminNewsFragment;
import com.susy.dormitoryassistant.fragment.AdminScoreFragment;
import com.susy.dormitoryassistant.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员端的主界面
 * Created by susy on 17/2/4.
 */

public class MainAdminActivity extends AppCompatActivity implements View.OnClickListener {
    private DormitoryApplication mApplication = null;
    private Context context = MainAdminActivity.this;

    private TextView tvTitle;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private CustomViewPager viewPager;
    private LinearLayout buttonLayout;

    //5个Tab，每个Tab包含一个按钮
    private LinearLayout mTabNews;
    private LinearLayout mTabScore;
    private LinearLayout mTabCost;
    private LinearLayout mTabContact;
    private LinearLayout mTabMe;

    //5个按钮
    private ImageButton mNewsImg = null;
    private ImageButton mScoreImg = null;
    private ImageButton mCostImg = null;
    private ImageButton mContactImg = null;
    private ImageButton mMeImg = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        int width = d.getWidth()/5;
        int pageIndex = 0;

        mTabNews = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_newsbutton, null);
        mTabNews.setOnClickListener(this);
        mTabNews.setTag(pageIndex);
        buttonLayout.addView(mTabNews, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mNewsImg = (ImageButton) mTabNews.findViewById(R.id.id_tab_news_img);
        fragments.add(new AdminNewsFragment());
        pageIndex++;

        mTabScore = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_scorebutton, null);
        mTabScore.setOnClickListener(this);
        mTabScore.setTag(pageIndex);
        buttonLayout.addView(mTabScore, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mScoreImg = (ImageButton) mTabScore.findViewById(R.id.id_tab_score_img);
        fragments.add(new AdminScoreFragment());
        pageIndex++;

        mTabCost = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_costbutton, null);
        mTabCost.setOnClickListener(this);
        mTabCost.setTag(pageIndex);
        buttonLayout.addView(mTabCost, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mCostImg = (ImageButton) mTabCost.findViewById(R.id.id_tab_cost_img);
        fragments.add(new AdminCostFragment());
        pageIndex++;

        mTabContact = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_contactbutton, null);
        mTabContact.setOnClickListener(this);
        mTabContact.setTag(pageIndex);
        buttonLayout.addView(mTabContact, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mContactImg = (ImageButton) mTabContact.findViewById(R.id.id_tab_contact_img);
        fragments.add(new AdminContactFragment());
        pageIndex++;

        mTabMe = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_mebutton, null);
        mTabMe.setOnClickListener(this);
        mTabMe.setTag(pageIndex);
        buttonLayout.addView(mTabMe, width, LinearLayout.LayoutParams.MATCH_PARENT);
        mMeImg = (ImageButton) mTabMe.findViewById(R.id.id_tab_me_img);
        fragments.add(new AdminMeFragment());
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
            case R.id.id_tab_score:
                tvTitle.setText(R.string.title_score);
                viewPager.setCurrentItem((int) mTabScore.getTag());
                resetImg();
                mScoreImg.setImageResource(R.drawable.tab_score_checked);
                break;
            case R.id.id_tab_cost:
                tvTitle.setText(R.string.title_cost);
                viewPager.setCurrentItem((int) mTabCost.getTag());
                resetImg();
                mCostImg.setImageResource(R.drawable.tab_cost_checked);
                break;
            case R.id.id_tab_contact:
                tvTitle.setText(R.string.title_contact);
                viewPager.setCurrentItem((int) mTabContact.getTag());
                resetImg();
                mContactImg.setImageResource(R.drawable.tab_contact_checked);
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
                            MainAdminActivity.this.finish();
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
        if (mScoreImg != null) {
            mScoreImg.setImageResource(R.drawable.tab_score_normal);
        }
        if (mCostImg != null) {
            mCostImg.setImageResource(R.drawable.tab_cost_normal);
        }
        if (mContactImg != null) {
            mContactImg.setImageResource(R.drawable.tab_contact_normal);
        }
        if (mMeImg != null) {
            mMeImg.setImageResource(R.drawable.tab_me_normal);
        }
    }
}
