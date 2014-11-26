package com.applite.usingbaseadapter;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyListAdapter<T> extends BaseAdapter {

	private Context context;
	private int listCellId=0;
	
	public MyListAdapter(Context context,int resourceId)
	{
		this.context = context;
		this.listCellId = resourceId;
	}
	
	public Context getContext()
	{
		return context;
	}
	
	private List<T> list =new ArrayList<T>();
	
	public void Add(T item)
	{
		list.add(item);
		//通知ui组件刷新
		notifyDataSetChanged();
	}
	
	public void removeLast() {
		list.remove(getCount()-1);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null)
		{
			//创建
			convertView=LayoutInflater.from(getContext()).inflate(listCellId, null);
		}
		//列表项的初始化过程抽象方法 交给调用者去具体实现
		
		initListCell(position,convertView,parent);
		
		return convertView;
	}
	
	protected abstract void initListCell(int position,View listCell,ViewGroup parent);

}
