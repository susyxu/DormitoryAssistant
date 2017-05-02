package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susy on 17/3/13.
 */

public class StudentAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<Student> mList = new ArrayList<Student>();//用来存放对象的列表
    private MyClickListener mListener;

    public StudentAdapter(Context context, List<Student> orderList, MyClickListener listener) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = orderList;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if (convertView == null) { //加判断，复用，让效率更高
            convertView = mLayoutInflater.inflate(R.layout.item_search_student, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            myViewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            myViewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            myViewHolder.tvClass = (TextView) convertView.findViewById(R.id.tvClass);
            myViewHolder.btnDisobey = (Button) convertView.findViewById(R.id.btnDisobey);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.tvId.setText(mList.get(position).getStudentId());
        myViewHolder.tvName.setText(mList.get(position).getStudentName());
        myViewHolder.tvPhone.setText(mList.get(position).getStudentPhone());
        myViewHolder.tvClass.setText(mList.get(position).getStudentMajor() + mList.get(position).getStudentClass());

        myViewHolder.btnDisobey.setOnClickListener(mListener);
        myViewHolder.btnDisobey.setTag(position);
        return convertView;
    }

    private class MyViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvPhone;
        TextView tvClass;
        Button btnDisobey;
    }

    /*刷新数据的方法*/
    public void refresh(List<Student> orderList) {
        mList = orderList;
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
