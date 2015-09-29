package com.muzhi.pinterest.adapter;

import java.util.LinkedList;
import java.util.List;

import com.muzhi.pinterest.AppData;
import com.muzhi.pinterest.R;
import com.muzhi.pinterest.model.DuitangInfo;
import com.muzhi.widget.views.ScaleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StaggeredAdapter extends BaseAdapter {
    private Context mContext;
    private LinkedList<DuitangInfo> mInfos;

    public StaggeredAdapter(Context context) {
        mContext = context;
        mInfos = new LinkedList<DuitangInfo>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        DuitangInfo duitangInfo = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.infos_list, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView.findViewById(R.id.news_pic);
            holder.contentView = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        /*int w=duitangInfo.getWidth();
        int h=duitangInfo.getHeight();*/
        
        int w=200;
        int h=200;
        
        holder.imageView.setImageWidth(w);
        holder.imageView.setImageHeight(h);
        holder.contentView.setText(duitangInfo.getMsg());
        
        AppData.imageLoader.displayImage(duitangInfo.getIsrc(),holder.imageView);
        
        return convertView;
    }

    class ViewHolder {
    	ScaleImageView imageView;
        TextView contentView;
        TextView timeView;
    }

    
    
    
    
    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mInfos.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    public void addItemLast(List<DuitangInfo> datas) {
        mInfos.addAll(datas);
    }

    public void addItemTop(List<DuitangInfo> datas) {
        for (DuitangInfo info : datas) {
            mInfos.addFirst(info);
        }
    }
}