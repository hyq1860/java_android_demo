package com.applite.androidallinone;

import com.applite.androidallinone.scratchcard.AtyScratchCard;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	private ArrayAdapter<ListItem> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		adapter=new ArrayAdapter<ListItem>(this, android.R.layout.simple_list_item_1);
		init();
        setListAdapter(adapter);
	}
	
	private void init(){
		adapter.add(new ListItem(this, "耳机插入状态监测", new Intent(this, AtyHeadSetPlug.class)));
		adapter.add(new ListItem(this, "应用屏幕保持常亮", new Intent(this, AtyPowerManagerWakerLock.class)));
		adapter.add(new ListItem(this, "VolleyHelperDemo", new Intent(this, AtyVolleyHelperDemo.class)));
		adapter.add(new ListItem(this, "刮刮卡", new Intent(this, AtyScratchCard.class)));
		adapter.add(new ListItem(this, "ViewPager作为ListView的HeaderView", new Intent(this, AtySliderAndListView.class)));
		adapter.add(new ListItem(this, "AtyViewPagerAsListViewItem", new Intent(this, AtyViewPagerAsListViewItem.class)));
		adapter.add(new ListItem(this, "属性动画Animator与Animation", new Intent(this, AtyAnimatorAndAnimation.class)));
		adapter.add(new ListItem(this, "DrawerLayoutDemo", new Intent(this,AtyDrawerLayoutDemo.class)));
		adapter.add(new ListItem(this, "ScrollViewAndWebView", new Intent(this,AtyScrollViewAndWebView.class)));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListItem ListItem=adapter.getItem(position);
		ListItem.startActivity();
		super.onListItemClick(l, v, position, id);
	}
	
}
