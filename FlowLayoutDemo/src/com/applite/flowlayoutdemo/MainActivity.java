package com.applite.flowlayoutdemo;

import com.applite.view.FlowLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.TextView;

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
	 
	 ViewGroup对应一个LayoutParams 类型由父决定
	 子View.getLayoutParams =>
	 
	 
	 * */
	
	private String[] labeStrings=new String[]{
			"小清新","素颜","烟熏","浓妆","淡抹","丝袜","重口味"
	};
	
	private FlowLayout mFlowLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mFlowLayout=(FlowLayout)findViewById(R.id.id_flowlayout);
		
		initData();
	}
	
	public void initData(){
		/*
		for (int i = 0; i < labeStrings.length; i++) {
			Button btn=new Button(this);
			MarginLayoutParams lp=new MarginLayoutParams(
					MarginLayoutParams.WRAP_CONTENT, 
					MarginLayoutParams.WRAP_CONTENT);
			//btn.setLayoutParams(lp);
			btn.setText(labeStrings[i]);
			mFlowLayout.addView(btn,lp);
		}
		
		*/
		LayoutInflater inflater=LayoutInflater.from(this);
		for (int i = 0; i < labeStrings.length; i++) {
			TextView tv=(TextView)inflater.inflate(R.layout.tv, mFlowLayout,false);
			tv.setText(labeStrings[i]);
			mFlowLayout.addView(tv);
		}
	}
}
