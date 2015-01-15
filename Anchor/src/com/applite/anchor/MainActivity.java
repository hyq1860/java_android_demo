package com.applite.anchor;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MainActivity extends Activity implements OnItemClickListener {
	
	private DrawerLayout mDrawerLayout;
	
	private ListView mListView;
	
	private ArrayList<String> menuLists;
	
	private ArrayAdapter<String> adapter;
	
	private ActionBarDrawerToggle mDrawerToggle;
	
	private String mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_drawerlayout);
		
		mTitle=getTitle().toString();
		
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		mListView=(ListView)findViewById(R.id.left_drawer);
		menuLists=new ArrayList<String>();
		menuLists.add("美女广场");
		menuLists.add("我的点评");
		menuLists.add("设置");
		menuLists.add("搜索");

		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menuLists);
		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(this);
		
		mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
		{
			//打开事件
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle("请选择");
				invalidateOptionsMenu();//重绘菜单项 系统会自动调用onPrepareOptionsMenu()
			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();//重绘菜单项
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		//开启actionbar上面的app icon功能
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		Fragment contentFragment=new ContentFragment();
		
		
		FragmentManager fragmentManager=getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment).commit();
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean isDrawerOpen= mDrawerLayout.isDrawerOpen(mListView);
		menu.findItem(R.id.action_websearch).setVisible(!isDrawerOpen);
		
		// TODO Auto-generated method stub
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//动态常见一个fragment到maincontent
		Fragment contentFragment=new ContentFragment();
		Bundle extras=new Bundle();
		extras.putString("text", menuLists.get(position));
		contentFragment.setArguments(extras);
		
		FragmentManager fragmentManager=getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment).commit();
		
		mDrawerLayout.closeDrawer(mListView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		
		//需要将ActionDrawerToggle与DrawerLayout状态同步
		//讲ActionBarDrawerToggle中drawer图标 设置为ActionBar中的Home-button
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//讲actionbar上面的图标与drawer结合起来 点击显示收藏
		if(mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		
		switch (item.getItemId()) {
		case R.id.action_websearch:
			Intent intent=new Intent("android.intent.action.VIEW", Uri.parse("http://www.baidu.com"));
			startActivity(intent);
			break;
		
		default:
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
}

