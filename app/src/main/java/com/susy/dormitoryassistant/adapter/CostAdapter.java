package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.Cost;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/5/4.
 */
public class CostAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<Cost> mOrderList = new ArrayList<Cost>();//用来存放对象的列表

    public CostAdapter(Context context, List<Cost> orderList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mOrderList = orderList;
    }

    @Override
    public int getCount() {
        return mOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) { //加判断，复用，让效率更高
            convertView = mLayoutInflater.inflate(R.layout.item_cost, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvDormCode = (TextView) convertView.findViewById(R.id.tvDormCode);
            myViewHolder.tvQuarter = (TextView) convertView.findViewById(R.id.tvQuarter);
            myViewHolder.tvWater = (TextView) convertView.findViewById(R.id.tvWaterCount);
            myViewHolder.tvElec = (TextView) convertView.findViewById(R.id.tvElecCount);
            myViewHolder.tvMoney = (TextView) convertView.findViewById(R.id.tvMoney);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvDormCode.setText(mOrderList.get(position).getDormitoryId());
        myViewHolder.tvQuarter.setText(mOrderList.get(position).getQuarter());
        myViewHolder.tvWater.setText(mOrderList.get(position).getWaterCount()
                + "（" + mOrderList.get(position).getCurrentWaterPrice() + "元）");
        myViewHolder.tvElec.setText(mOrderList.get(position).getElecCount()
                + "（" + mOrderList.get(position).getCurrentElecPrice() + "元）");

        Double water = Double.parseDouble(mOrderList.get(position).getCurrentWaterPrice());
        Double elec = Double.parseDouble(mOrderList.get(position).getCurrentElecPrice());
        Double sum = water + elec;
        DecimalFormat df = new DecimalFormat("######0.00");

        myViewHolder.tvMoney.setText(df.format(sum) + "元");

        return convertView;
    }

    private class MyViewHolder {
        TextView tvDormCode;
        TextView tvQuarter;
        TextView tvWater;
        TextView tvElec;
        TextView tvMoney;
    }

    /*刷新数据的方法*/
    public void refresh(List<Cost> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }
}
