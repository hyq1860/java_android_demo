package com.applite.androidallinone;

import java.util.ArrayList;
import java.util.List;

import com.applite.androidallinone.AtySliderAndListView.ViewPageAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class AtyViewPagerAsListViewItem extends Activity {
	
	private ListView listView=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_sliderlistview_listview);
		init();
	}
	
	private void init()
	{
		listView=(ListView)findViewById(R.id.sliderlistview_listview);
		
		ArrayList<String> data=new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			data.add(i+":"+"数据");
		}
		listView.setAdapter(new ListViewAdapter(data));
	}
	
	public class ViewPageAdapter extends PagerAdapter
	{
		
		private List<ImageView> imageViews=null;
		
		public ViewPageAdapter(List<ImageView> imageViews)
		{
			this.imageViews=imageViews;
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView(imageViews.get(position));
		}
		
		@Override
		public Object instantiateItem(View container, int position) {
			ImageView imageView=imageViews.get(position);
			((ViewPager)container).addView(imageView);
			return imageView;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
	}
	
	public class ListViewAdapter extends BaseAdapter
	{
		private LayoutInflater mInflater=null;
		
		private List<String> mData=null;
		
		public ListViewAdapter(List<String> data)
		{
			//??
			mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.mData=data;
		}
		
		//返回listview中的item类型
		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			return position>0?1:0;
		}

		//返回listview中有几种item类型
		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=convertView;
			
			//第一个 ViewPager
			int itemViewType=getItemViewType(position);
			if(itemViewType==0)
			{
				ViewPagerHolder viewPagerHolder=null;
				if(view==null)
				{
					view=mInflater.inflate(R.layout.aty_sliderlistview_slider, null);
					viewPagerHolder=new ViewPagerHolder();
					viewPagerHolder.viewPager=(ViewPager)view.findViewById(R.id.sliderlistview_slider);
					
					List<ImageView> imageViews=new ArrayList<ImageView>();
					for (int i = 0; i < 4; i++) {
						ImageView imageView=new ImageView(AtyViewPagerAsListViewItem.this);
						imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,100));
						imageView.setScaleType(ScaleType.FIT_XY);
						imageView.setImageResource(R.drawable.ic_launcher);
						imageViews.add(imageView);
					}
					ViewPageAdapter viewPageAdapter=new ViewPageAdapter(imageViews);
					viewPagerHolder.viewPager.setAdapter(viewPageAdapter);
					view.setTag(viewPagerHolder);
				}
				else {
					view=convertView;
					viewPagerHolder=(ViewPagerHolder)view.getTag();
				}
			}
			else if(itemViewType==1)
			{
				ViewHolder viewHolder=null;
				if(view==null)
				{
					view=mInflater.inflate(R.layout.aty_sliderlistview_item, null);
					viewHolder=new ViewHolder();
					viewHolder.textView=(TextView)view.findViewById(R.id.sliderlistview_listitem);
					view.setTag(viewHolder);
				}
				else {
					viewHolder=(ViewHolder)view.getTag();
				}
				viewHolder.textView.setText("我的位置："+position);
			}
			
			return view;
		}
		
		
		
	}
	
	public static class ViewHolder
	{
		public TextView textView;
	}
	
	public static class ViewPagerHolder
	{
		public ViewPager viewPager;
	}
}
