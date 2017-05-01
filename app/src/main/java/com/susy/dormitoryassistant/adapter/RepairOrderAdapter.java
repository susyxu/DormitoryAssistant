package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.MilkOrder;
import com.susy.dormitoryassistant.entity.RepairOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/3/13.
 */

public class RepairOrderAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<RepairOrder> mOrderList = new ArrayList<RepairOrder>();//用来存放对象的列表

    public RepairOrderAdapter(Context context, List<RepairOrder> orderList) {
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
            convertView = mLayoutInflater.inflate(R.layout.item_repair_order, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvState = (TextView) convertView.findViewById(R.id.tvState);
            myViewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            myViewHolder.tvFinished = (TextView) convertView.findViewById(R.id.tvFinished);
            myViewHolder.tvFinishedTime = (TextView) convertView.findViewById(R.id.tvFinishedTime);
            myViewHolder.tvWantTime = (TextView) convertView.findViewById(R.id.tvWantTime);
            myViewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            myViewHolder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvState.setText(mOrderList.get(position).getRepairOrderStatus());
        myViewHolder.tvTime.setText(mOrderList.get(position).getRepairOrderTime() );

        if(mOrderList.get(position).getRepairOrderFinishTime()==null){
            myViewHolder.tvFinished.setText("待维修");
        } else {
            myViewHolder.tvFinished.setText("已维修");
            myViewHolder.tvFinishedTime.setText(mOrderList.get(position).getRepairOrderFinishTime());
        }

        myViewHolder.tvWantTime.setText(mOrderList.get(position).getRepairOrderFreeTime());
        myViewHolder.tvType.setText("维修类型"+mOrderList.get(position).getRepairOrderItem());
        myViewHolder.tvDetail.setText(mOrderList.get(position).getRepairOrderDetail());

        return convertView;
    }

    private class MyViewHolder {
        TextView tvState;
        TextView tvTime;
        TextView tvFinished;
        TextView tvFinishedTime;
        TextView tvWantTime;
        TextView tvType;
        TextView tvDetail;
    }

    /*刷新数据的方法*/
    public void refresh(List<RepairOrder> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }
}
