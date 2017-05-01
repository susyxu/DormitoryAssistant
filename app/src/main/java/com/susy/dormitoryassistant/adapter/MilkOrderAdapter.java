package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.MilkOrder;
import com.susy.dormitoryassistant.entity.WaterOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/3/13.
 */

public class MilkOrderAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<MilkOrder> mOrderList = new ArrayList<MilkOrder>();//用来存放对象的列表

    public MilkOrderAdapter(Context context, List<MilkOrder> orderList) {
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
            convertView = mLayoutInflater.inflate(R.layout.item_milk_order, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvState = (TextView) convertView.findViewById(R.id.tvState);
            myViewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            myViewHolder.tvNum = (TextView) convertView.findViewById(R.id.tvNum);
            myViewHolder.tvMoney = (TextView) convertView.findViewById(R.id.tvMoney);
            myViewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            myViewHolder.tvMonth = (TextView) convertView.findViewById(R.id.tvMonth);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvState.setText(mOrderList.get(position).getMilkOrderStatus());
        myViewHolder.tvTime.setText(mOrderList.get(position).getMilkOrderTime());

        myViewHolder.tvNum.setText(mOrderList.get(position).getMilkCount());
        myViewHolder.tvMoney.setText(Integer.parseInt(mOrderList.get(position).getMilkCount())*30 + "元");
        myViewHolder.tvType.setText(mOrderList.get(position).getMilkType());
        myViewHolder.tvMonth.setText(mOrderList.get(position).getMilkMonth()+"月份");

        return convertView;
    }

    private class MyViewHolder {
        TextView tvState;
        TextView tvTime;
        TextView tvNum;
        TextView tvMoney;
        TextView tvType;
        TextView tvMonth;
    }

    /*刷新数据的方法*/
    public void refresh(List<MilkOrder> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }
}
