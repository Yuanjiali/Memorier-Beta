package com.example.a17104.memorier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 17104 on 2017/10/19.
 */

public class CostListAdapter extends BaseAdapter{

    private List<DailyBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CostListAdapter(Context context,List<DailyBean> list){
        mContext=context;
        mList=list;
        mLayoutInflater=LayoutInflater.from(context);
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.list_item,null);
            viewHolder.mTvCostTitle=convertView.findViewById(R.id.tv_title);
            viewHolder.mTvCostDate=convertView.findViewById(R.id.tv_date);
            viewHolder.mTvCostMoney=convertView.findViewById(R.id.tv_cost);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        DailyBean bean=mList.get(position);
        viewHolder.mTvCostTitle.setText(bean.addAccount);
        viewHolder.mTvCostDate.setText(bean.costDate);
        viewHolder.mTvCostMoney.setText(bean.addPassword);
        return convertView;
    }

    private  static class ViewHolder{
        public TextView mTvCostTitle;
        public TextView mTvCostDate;
        public TextView mTvCostMoney;
    }
}
