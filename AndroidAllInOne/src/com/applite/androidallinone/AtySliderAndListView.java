package com.applite.androidallinone;

import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;

public class AtySliderAndListView extends Activity{
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_sliderlistview_listview);
		init();
	}
	
	private void init(){
		listView=(ListView)findViewById(R.id.sliderlistview_listview);
		
		View view=LayoutInflater.from(this).inflate(R.layout.aty_sliderlistview_slider, null);
		
		ViewPager viewPager=(ViewPager)view.findViewById(R.id.sliderlistview_slider);
		
		List<ImageView> imageViews=new ArrayList<ImageView>();
		for (int i = 0; i < 4; i++) {
			ImageView imageView=new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,100));
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageResource(R.drawable.ic_launcher);
			imageViews.add(imageView);
		}
		
		ViewPageAdapter viewPageAdapter=new ViewPageAdapter(imageViews);
		viewPager.setAdapter(viewPageAdapter);
		listView.addHeaderView(view);
		List<String> data=new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			data.add(i+":"+"数据");
		}
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
		
		
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
}