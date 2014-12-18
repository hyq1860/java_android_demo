package com.applite.swiperefreshlayoutdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.support.v4.widget.SwipeRefreshLayout;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	private ArrayList<String> list = new ArrayList<String>();
	
	private ArrayAdapter<String> adapter;
	public int i=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.listview);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				getData());
		mListView.setAdapter(adapter);

		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light, android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
	}

	private ArrayList<String> getData() {
		list.add("Hello");
		list.add("This is stormzhang");
		list.add("An Android Developer");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		list.add("Love Open Source");
		list.add("My GitHub: stormzhang");
		list.add("weibo: googdev");
		return list;
	}

	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mSwipeLayout.setRefreshing(false);
				i++;
				list.add("展示"+String.format("%d", i));
				list.add("展");
				list.add("展");
				list.add("展");
				list.add("展");
				list.add("展");
				
				adapter.notifyDataSetChanged();
			}
		}, 2000);
	}
}
