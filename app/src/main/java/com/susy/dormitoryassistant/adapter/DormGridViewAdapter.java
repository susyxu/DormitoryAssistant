package com.susy.dormitoryassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 做了一些优化操作！
 * Created by susy on 17/03/05.
 */
public class DormGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater; //用来读布局
    private List<String> mDormList = new ArrayList<String>();//用来存放对象的列表

    public DormGridViewAdapter(Context context, List<String> dormList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDormList = dormList;
    }

    @Override
    public int getCount() {
        //有多少条数据
        return mDormList.size();
    }

    @Override
    public Object getItem(int position) {
        //具体找到哪一条的数据对象
        return mDormList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder myViewHolder;

        if (convertView == null) { //加判断，复用，让效率更高
            convertView = mLayoutInflater.inflate(R.layout.item_dorm, null);
            myViewHolder = new MyViewHolder();

            //获取控件
            myViewHolder.text = (TextView) convertView.findViewById(R.id.tv_text);
            myViewHolder.image = (ImageView) convertView.findViewById(R.id.iv_image);

            convertView.setTag(myViewHolder);
        }
        else{
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        //和数据进行绑定
        myViewHolder.text.setText(mDormList.get(position).toString());

        return convertView;
    }

    class MyViewHolder {
        TextView text;
        ImageView image;
    }

    /*刷新数据的方法*/
    public void refresh(List<String> dormList) {
        mDormList = dormList;
        notifyDataSetChanged();
    }
}
