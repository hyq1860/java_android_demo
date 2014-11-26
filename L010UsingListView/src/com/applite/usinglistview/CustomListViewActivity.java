package com.applite.usinglistview;

import android.app.ListActivity;
import android.os.Bundle;

public class CustomListViewActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//快速方法
		
		setContentView(R.layout.custom_listview_activity);
		
		setListAdapter(new CustomListViewAdapter(this));
	}
}
