package com.applite.intents;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewer extends Activity {
	
	//引入imageview组件
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		iv=new ImageView(this);
		
		setContentView(iv);
		
		iv.setImageURI(getIntent().getData());
	}
}
