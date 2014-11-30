package com.applite.androidpulltorefreshdemo;

import java.util.ArrayList;
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
	
	private PullToRefreshListView  myListView;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myListView=(PullToRefreshListView)findViewById(R.id.mylv);
		List<String> data=new ArrayList<String>();
		data.add("美女");
		data.add("美女");
		data.add("美女");
		
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
		myListView.setAdapter(adapter);
		
		
		myListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

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
						adapter.addAll("骗子","90后");
						
						//通知ui界面数据更新完毕
						myListView.onRefreshComplete();
					};
					
				}.execute();
			}
			 
		});
		
	}
}
