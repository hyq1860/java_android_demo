package com.applite.flowlayoutdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	/*
	 1.流式布局特点，应用场景
	 2.自定义ViewGroup
	 	1.onMeasure:测量子view的宽和高，设置自己的宽和高
	 	根据子view的布局文件，为子view设置测量模式和测量值;
	 	测量=测量模式+测量值;
	 	测量模式：
	 	EXACTLY:100dp,match_parent
	 	AT_MOST:wrap_content
	 	UNSPCIFIED:子空间想要多大就多大，很少见
	 	
	 	2.onLayout:设置子view的位置
	 
	 ViewGroup对应一个LayoutParams
	 子View.getLayoutParams =>
	 
	 
	 * */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
