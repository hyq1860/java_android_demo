package com.applite.usinglistview;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

//自定义listview
public class CustomListView extends Activity {
	
	private ListView lv;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.custom_listview);
		
		lv=(ListView)findViewById(R.id.lv);
		
		lv.setAdapter(new CustomListViewAdapter(this));
	}
}
