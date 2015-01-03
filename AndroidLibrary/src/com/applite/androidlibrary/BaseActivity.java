package com.applite.androidlibrary;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author heyongqiang
 * 应用程序Activity的基类
 */
public abstract class BaseActivity extends Activity {
	
	public BaseActivity(){
		
	}
	
	//是否允许全屏
	private boolean mAllFullScreen=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_base);
	}
	
}
