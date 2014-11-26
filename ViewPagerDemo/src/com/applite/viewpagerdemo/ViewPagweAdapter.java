package com.applite.viewpagerdemo;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagweAdapter extends PagerAdapter {

	private List<View> views;
	
	private Context context;
	
	public ViewPagweAdapter(List<View> views,Context context)
	{
		this.context=context;
		this.views=views;
	}
	
	//不使用view的时候 将其销毁
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		//super.destroyItem(container, position, object);
		//移除
		((ViewPager)container).removeView(views.get(position));
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		
		((ViewPager)container).addView(views.get(position));
		return views.get(position);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0==arg1);
	}

}
