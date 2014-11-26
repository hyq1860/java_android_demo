package com.applite.viewpagerdemo;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;

public class WelcomeActivity extends Activity {
	private boolean isFirstIn=false;
	private static final int TIME=2000;
	
	private static final int GO_HOME=1000;
	private static final int GO_GUIDE=1001;
	
	private Handler mHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GO_GUIDE:
				goGuide();
				break;

			case GO_HOME:
				goHome();
				break;
			}
		};
	};
	
	private void init(){
		SharedPreferences preferences=getSharedPreferences("app", MODE_PRIVATE);
		isFirstIn=preferences.getBoolean("isFirstIn", true);
		if(!isFirstIn)
		{
			mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
		}
		else
		{
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
			Editor editor=preferences.edit();
			editor.putBoolean("isFirstIn", false);
			editor.commit();
		}
	}
	
	private void goHome(){
		Intent i=new Intent(WelcomeActivity.this, MainActivity.class);
		startActivity(i);
		finish();
	}
	
	private void goGuide(){
		Intent i=new Intent(WelcomeActivity.this, Guide.class);
		startActivity(i);
		finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.welcome);
		
		init();
	}
}
