package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.Score;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/5/4.
 */
public class ScoreAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<Score> mOrderList = new ArrayList<Score>();//用来存放对象的列表

    public ScoreAdapter(Context context, List<Score> orderList) {
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
            convertView = mLayoutInflater.inflate(R.layout.item_score, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvDormCode = (TextView) convertView.findViewById(R.id.tvDormCode);
            myViewHolder.tvWeek = (TextView) convertView.findViewById(R.id.tvWeek);
            myViewHolder.rbbedClean = (RatingBar) convertView.findViewById(R.id.bedClean);
            myViewHolder.rbfloorClean = (RatingBar) convertView.findViewById(R.id.floorClean);
            myViewHolder.rbdeskClean = (RatingBar) convertView.findViewById(R.id.deskClean);
            myViewHolder.rbwcClean = (RatingBar) convertView.findViewById(R.id.wcClean);
            myViewHolder.rbbalconyClean = (RatingBar) convertView.findViewById(R.id.balconyClean);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvDormCode.setText(mOrderList.get(position).getDormitoryId());
        myViewHolder.tvWeek.setText(mOrderList.get(position).getWeek() + "周");

        myViewHolder.rbbedClean.setRating(Float.parseFloat(mOrderList.get(position).getBedClean()));
        myViewHolder.rbfloorClean.setRating(Float.parseFloat(mOrderList.get(position).getFloorClean()));
        myViewHolder.rbdeskClean.setRating(Float.parseFloat(mOrderList.get(position).getDeskClean()));
        myViewHolder.rbwcClean.setRating(Float.parseFloat(mOrderList.get(position).getWcClean()));
        myViewHolder.rbbalconyClean.setRating(Float.parseFloat(mOrderList.get(position).getBalconyClean()));
        return convertView;
    }

    private class MyViewHolder {
        TextView tvDormCode;
        TextView tvWeek;
        RatingBar rbbedClean;
        RatingBar rbfloorClean;
        RatingBar rbdeskClean;
        RatingBar rbwcClean;
        RatingBar rbbalconyClean;
    }

    /*刷新数据的方法*/
    public void refresh(List<Score> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }
}
