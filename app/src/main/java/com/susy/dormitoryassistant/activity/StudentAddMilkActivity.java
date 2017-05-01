package com.susy.dormitoryassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.adapter.MilkOrderAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.MilkOrder;
import com.susy.dormitoryassistant.entity.MilkOrders;
import com.susy.dormitoryassistant.entity.SaveMilkOrder;
import com.susy.dormitoryassistant.entity.SaveWaterOrder;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by susy on 17/4/26.
 */

public class StudentAddMilkActivity extends AppCompatActivity implements View.OnClickListener{

    private DormitoryApplication mApplication = null;
    
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_save;
    private TextView tv_name;
    private TextView tv_money;
    private Spinner sp_type;
    private Spinner sp_month;
    private RadioGroup rg;
    private RadioButton rb_WaterOne;
    private RadioButton rb_WaterTwo;

    private ListView lv_milk;
    private MilkOrders mOrders = new MilkOrders();
    private List<MilkOrder> mMilkOrderList = new ArrayList<MilkOrder>();

    private MilkOrderAdapter mAdapter;

    private String studentId="";
    private String count="";
    private String dormitoryId="";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = DormitoryApplication.getmInstance();
        setContentView(R.layout.activity_add_milk);
        tv_title = (TextView) findViewById(R.id.tvTitle);
        tv_save = (TextView) findViewById(R.id.tvSave);
        tv_name = (TextView) findViewById(R.id.tv_dormCode);
        tv_money = (TextView) findViewById(R.id.tv_money);
        iv_back = (ImageView) findViewById(R.id.back);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_WaterOne = (RadioButton) findViewById(R.id.rbNumOne);
        rb_WaterTwo = (RadioButton) findViewById(R.id.rbNumTwo);
        sp_type = (Spinner) findViewById(R.id.milk_type);
        sp_month = (Spinner) findViewById(R.id.milk_month);

        tv_title.setText("预定牛奶");
        tv_save.setText("预定");
        tv_money.setText("总计金额:   30.00元");
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        if (mApplication.getGlobalStudent() != null) {
            tv_name.setText("学生姓名:   "+mApplication.getGlobalStudent().getData().getStudentName());
            studentId=mApplication.getGlobalStudent().getData().getStudentId();
            dormitoryId=mApplication.getGlobalStudent().getData().getDormitoryId();

            initListViewData();
        }

        //根据rb选项的check改变textview handler
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbNumOne){
                    tv_money.setText("总计金额:   30.00元");
                } else if(checkedId==R.id.rbNumTwo){
                    tv_money.setText("总计金额:   60.00元");
                }
            }
        });

        String[] items = getResources().getStringArray(R.array.milktype);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);

        String[] items2 = getResources().getStringArray(R.array.milkmonth);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_month.setAdapter(adapter2);

        lv_milk = (ListView) findViewById(R.id.lv_milk);
        mAdapter = new MilkOrderAdapter(StudentAddMilkActivity.this,mMilkOrderList);
        lv_milk.setAdapter(mAdapter);
    }

    private void initListViewData() {
        mMilkOrderList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<MilkOrders> call = apiStores.studentGetMilkOrder(studentId);
        call.enqueue(new Callback<MilkOrders>() {
            @Override
            public void onResponse(Call<MilkOrders> call, Response<MilkOrders> response) {
                mOrders = response.body();
                if(mOrders.getData()!=null){
                    mMilkOrderList = mOrders.getData();
                    mAdapter.refresh(mMilkOrderList);
                }
            }

            @Override
            public void onFailure(Call<MilkOrders> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                StudentAddMilkActivity.this.finish();
                break;
            case R.id.tvSave:
                save();
                break;
        }
    }

    private void save() {
        //关于月份还需要加判断
        if (rb_WaterOne.isChecked()) {
            count = "1";
        } else {
            count = "2";
        }
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<SaveMilkOrder> call = apiStores.studentAddMilk(studentId,dormitoryId,
                String.valueOf(sp_month.getSelectedItemId()+1),
                sp_type.getSelectedItem().toString(), count);
        call.enqueue(new Callback<SaveMilkOrder>() {
            @Override
            public void onResponse(Call<SaveMilkOrder> call, Response<SaveMilkOrder> response) {
                Log.i("saveAddMilk",response.body().getInfo());
                if(response.body().getInfo().equals("添加成功")){
                    UtilTools.showToast(StudentAddMilkActivity.this, "预定牛奶成功");
                    initListViewData();
                } else {
                    UtilTools.showToast(StudentAddMilkActivity.this, "预定牛奶失败");
                }
            }

            @Override
            public void onFailure(Call<SaveMilkOrder> call, Throwable t) {

            }
        });

    }
}
