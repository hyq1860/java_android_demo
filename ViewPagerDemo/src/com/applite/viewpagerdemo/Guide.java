package com.applite.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//监听页面变化事件
public class Guide extends Activity implements OnPageChangeListener {
	private ViewPager vp;
	
	private ViewPagweAdapter vpAdapter;
	
	private List<View> views;
	
	private ImageView[] dots;
	
	private int[] ids={R.id.iv1,R.id.iv2,R.id.iv3};
	
	private Button btnStart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		initViews();
		initDots();
	}
	
	private void initViews(){
		LayoutInflater inflater=LayoutInflater.from(this);
		views=new ArrayList<View>();
		views.add(inflater.inflate(R.layout.viewpagerone, null));
		views.add(inflater.inflate(R.layout.viewpagertwo, null));
		views.add(inflater.inflate(R.layout.viewpagerthree, null));
		
		vpAdapter=new ViewPagweAdapter(views, this);
		vp=(ViewPager)findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		
		btnStart=(Button)views.get(2).findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Guide.this, MainActivity.class);
				startActivity(i);
				
				//当前activity不需要了 关闭
				finish();
			}
		});
		
		//添加回调
		vp.setOnPageChangeListener(this);
	}
	
	private void initDots(){
		int length=views.size();
		dots=new ImageView[length];
		for (int i = 0; i < length; i++) {
			dots[i]=(ImageView)findViewById(ids[i]);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		for (int i = 0; i < ids.length; i++) {
			if(arg0==i)
			{
				dots[i].setImageResource(R.drawable.login_point_selected);
			}
			else {
				dots[i].setImageResource(R.drawable.login_point);
			}
		}
		
	}
	
}
