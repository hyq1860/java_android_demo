package com.applite.uicontrols;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AtyUsingScrollView extends Activity {

	private TextView textScrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_scrollview);
		textScrollView=(TextView) findViewById(R.id.textScrollView);
		String str="";
		for (int i = 0; i < 100; i++) {
			str+="行"+i+"\n";
			
		}
		textScrollView.setText(str);
	}
	
}
