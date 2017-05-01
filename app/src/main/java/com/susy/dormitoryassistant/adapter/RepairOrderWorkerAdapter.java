package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.RepairOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/3/13.
 */

public class RepairOrderWorkerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<RepairOrder> mRepairOrderList = new ArrayList<RepairOrder>();//用来存放对象的列表
    private MyClickListener mListener;

    public RepairOrderWorkerAdapter(Context context, List<RepairOrder> repairOrderList, MyClickListener listener) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRepairOrderList = repairOrderList;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mRepairOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRepairOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) { //加判断，复用，让效率更高
            convertView = mLayoutInflater.inflate(R.layout.item_repair_order_worker, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvState = (TextView) convertView.findViewById(R.id.tvState);
            myViewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            myViewHolder.tvFinished = (TextView) convertView.findViewById(R.id.tvFinished);
            myViewHolder.tvFinishedTime = (TextView) convertView.findViewById(R.id.tvFinishedTime);
            myViewHolder.tvWantTime = (TextView) convertView.findViewById(R.id.tvWantTime);
            myViewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            myViewHolder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
            myViewHolder.btnFinished = (Button) convertView.findViewById(R.id.btn_finished);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvState.setText(mRepairOrderList.get(position).getRepairOrderStatus());
        myViewHolder.tvTime.setText(mRepairOrderList.get(position).getRepairOrderTime());
        if (mRepairOrderList.get(position).getRepairOrderFinishTime() == null) {
            myViewHolder.tvFinished.setText("待维修");
        } else {
            myViewHolder.tvFinished.setText("已维修");
            myViewHolder.tvFinishedTime.setText(mRepairOrderList.get(position).getRepairOrderFinishTime());

            myViewHolder.btnFinished.setBackgroundResource(R.drawable.button_shape2);
            myViewHolder.btnFinished.setTextColor(Color.parseColor("#4CAF50"));
            myViewHolder.btnFinished.setText("已完成");
            myViewHolder.btnFinished.setEnabled(false);
        }
        myViewHolder.tvWantTime.setText(mRepairOrderList.get(position).getRepairOrderFreeTime());
        myViewHolder.tvType.setText("维修类型"+mRepairOrderList.get(position).getRepairOrderItem());
        myViewHolder.tvDetail.setText(mRepairOrderList.get(position).getRepairOrderDetail());

        myViewHolder.btnFinished.setOnClickListener(mListener);
        myViewHolder.btnFinished.setTag(position);

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
        Button btnFinished;
    }

    /*刷新数据的方法*/
    public void refresh(List<RepairOrder> waterOrderList) {
        mRepairOrderList = waterOrderList;
        notifyDataSetChanged();
    }

    public static abstract class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }

        public abstract void myOnClick(int position, View v);
    }
}
