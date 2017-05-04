package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.DisobeyOrder;
import com.susy.dormitoryassistant.entity.RepairOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/3/13.
 */

public class DisobeyAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<DisobeyOrder> mOrderList = new ArrayList<DisobeyOrder>();//用来存放对象的列表

    public DisobeyAdapter(Context context, List<DisobeyOrder> orderList) {
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
            convertView = mLayoutInflater.inflate(R.layout.item_disobey, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            myViewHolder.tvAdmin = (TextView) convertView.findViewById(R.id.tvAdmin);
            myViewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            myViewHolder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvTime.setText(mOrderList.get(position).getTime());
        myViewHolder.tvAdmin.setText(mOrderList.get(position).getUserId());
        myViewHolder.tvType.setText("违规类型" + mOrderList.get(position).getRuleId());
        myViewHolder.tvDetail.setText(mOrderList.get(position).getDetail());

        return convertView;
    }

    private class MyViewHolder {
        TextView tvType;
        TextView tvTime;
        TextView tvAdmin;
        TextView tvDetail;
    }

    /*刷新数据的方法*/
    public void refresh(List<DisobeyOrder> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }
}
