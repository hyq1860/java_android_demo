package com.applite.androidlibrary;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * @author heyongqiang
 * 单例模式的全局Application
 */

public class AppliteApplication extends Application {
	/*
	private static class SingletonHolder{
		private static final AppliteApplication INSTANCE = new AppliteApplication();
	}
	
	private AppliteApplication(){}
	
	public static final AppliteApplication getInstance(){
		return SingletonHolder.INSTANCE;
	}
	*/
	/*
	//volley请求队列
	private RequestQueue mRequestQueue;
	
	private ImageLoader mImageLoader;
	
	public RequestQueue getRequestQueue(){
		if(mRequestQueue==null)
		{
			mRequestQueue=Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}
	*/
	
	/*
	 * 入口
	 * */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//Context context=getApplicationContext();
		//Log.d("d", context.getPackageName());
		VolleyHelper.getInstance().setContext(getApplicationContext());
	}
	
}
