package com.applite.androidpulltorefreshdemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private PullToRefreshListView  mPullToRefreshListView;
	private PullToRefreshAdapter adapter;
	
	private LinkedList<DataItemInfo> dataSources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mPullToRefreshListView=(PullToRefreshListView)findViewById(R.id.mylv);
		dataSources=new LinkedList<DataItemInfo>();
		dataSources.add(new DataItemInfo(null,"中国","测试"));
		dataSources.add(new DataItemInfo(null,"中国","测试"));
		
		adapter=new PullToRefreshAdapter(this, dataSources);
		mPullToRefreshListView.setAdapter(adapter);
		
		
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				new AsyncTask<Void, Void, Void>() {
					
					//异步任务
					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(2000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						return null;
					}
					
					//异步任务完成后更新ui主界面
					protected void onPostExecute(Void result) {
						dataSources.add(new DataItemInfo(null,"中国2","测试"));
						dataSources.add(new DataItemInfo(null,"中国3","测试"));
						adapter.notifyDataSetChanged();
						//通知UI界面数据更新完毕
						mPullToRefreshListView.onRefreshComplete();
					};
					
				}.execute();
			}
			 
		});
		
	}
}
