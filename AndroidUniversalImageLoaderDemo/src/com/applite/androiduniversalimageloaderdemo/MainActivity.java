package com.applite.androiduniversalimageloaderdemo;

import com.applite.androiduniversalimageloaderdemo.Constants.Extra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btn_lv, btn_gv, btn_vp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_lv = (Button) this.findViewById(R.id.btn_lv);
		btn_gv = (Button) this.findViewById(R.id.btn_gv);
		btn_vp = (Button) this.findViewById(R.id.btn_vp);
		
		btn_lv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//listView
				Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
				intent.putExtra(Extra.IMAGES, Constants.IMAGES);
				startActivity(intent);
			}
		});
		btn_gv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
				intent.putExtra(Extra.IMAGES, Constants.IMAGES);
				startActivity(intent);
			}
		});
		btn_vp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ImagePagerActivity.class);
				intent.putExtra(Extra.IMAGES, Constants.IMAGES);
				startActivity(intent);
			}
		});
	}
}
