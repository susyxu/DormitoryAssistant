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
import com.susy.dormitoryassistant.entity.WaterOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/3/13.
 */

public class WaterOrderWorkerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<WaterOrder> mWaterOrderList = new ArrayList<WaterOrder>();//用来存放对象的列表
    private MyClickListener mListener;

    public WaterOrderWorkerAdapter(Context context, List<WaterOrder> waterOrderList, MyClickListener listener) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mWaterOrderList = waterOrderList;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mWaterOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWaterOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) { //加判断，复用，让效率更高
            convertView = mLayoutInflater.inflate(R.layout.item_water_order_worker, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvState = (TextView) convertView.findViewById(R.id.tvState);
            myViewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            myViewHolder.tvSent = (TextView) convertView.findViewById(R.id.tvSent);
            myViewHolder.tvSentTime = (TextView) convertView.findViewById(R.id.tvSentTime);
            myViewHolder.tvNum = (TextView) convertView.findViewById(R.id.tvNum);
            myViewHolder.tvMoney = (TextView) convertView.findViewById(R.id.tvMoney);
            myViewHolder.tvDormCode = (TextView) convertView.findViewById(R.id.tvDormCode);
            myViewHolder.btnFinished = (Button) convertView.findViewById(R.id.btn_finished);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvState.setText(mWaterOrderList.get(position).getWaterOrderStatus());
        myViewHolder.tvTime.setText(mWaterOrderList.get(position).getWaterOrderTime());
        if (mWaterOrderList.get(position).getWaterOrderFinishTime() == null) {
            myViewHolder.tvSent.setText("待配送");
        } else {
            myViewHolder.tvSent.setText("已送达");
            myViewHolder.tvSentTime.setText(mWaterOrderList.get(position).getWaterOrderFinishTime());
            myViewHolder.btnFinished.setBackgroundResource(R.drawable.button_shape2);
            myViewHolder.btnFinished.setTextColor(Color.parseColor("#4CAF50"));
            myViewHolder.btnFinished.setText("已完成");
            myViewHolder.btnFinished.setEnabled(false);
        }
        myViewHolder.tvNum.setText(mWaterOrderList.get(position).getWaterCount());
        myViewHolder.tvMoney.setText(mWaterOrderList.get(position).getWaterCount() + "0元");
        myViewHolder.tvDormCode.setText(mWaterOrderList.get(position).getDormitoryId());

        myViewHolder.btnFinished.setOnClickListener(mListener);
        myViewHolder.btnFinished.setTag(position);

        return convertView;
    }

    private class MyViewHolder {
        TextView tvState;
        TextView tvTime;
        TextView tvSent;
        TextView tvSentTime;
        TextView tvNum;
        TextView tvMoney;
        TextView tvDormCode;
        Button btnFinished;
    }

    /*刷新数据的方法*/
    public void refresh(List<WaterOrder> waterOrderList) {
        mWaterOrderList = waterOrderList;
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
