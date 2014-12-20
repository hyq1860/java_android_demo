package com.applite.androidpulltorefreshdemo;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PullToRefreshAdapter extends BaseAdapter {

	private Context context;
	
	private LinkedList<DataItemInfo> dataSources;
	
	public PullToRefreshAdapter(Context context,LinkedList<DataItemInfo> dataSources) {
		this.context=context;
		this.dataSources=dataSources;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataSources.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataSources.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null)	
		{
			LayoutInflater inflater=LayoutInflater.from(context);
			convertView=(LinearLayout)inflater.inflate(R.layout.contract_item,null);
			holder=new ViewHolder();
			holder.title=(TextView)convertView.findViewById(R.id.title);
			holder.date=(TextView)convertView.findViewById(R.id.date);
			holder.title.setText(dataSources.get(position).getTitle());
			holder.date.setText(dataSources.get(position).getDate());
			convertView.setTag(holder);
		}
		else {
			holder=(ViewHolder)convertView.getTag();
			holder.title.setText(dataSources.get(position).getTitle());
			holder.date.setText(dataSources.get(position).getDate());
		}
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView title;
		TextView date;
	}

}
