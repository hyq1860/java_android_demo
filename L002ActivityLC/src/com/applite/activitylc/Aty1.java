package com.applite.activitylc;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;

public class Aty1 extends Activity {
	
	private Button btnClose;
	private TextView tvOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty1);
		btnClose=(Button)findViewById(R.id.btnClose);
		btnClose.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//
				//setResult(resultCode)
				Intent i=new Intent();
				i.putExtra("result", "你好 mainactivity");
				setResult(0,i);
				
				finish();
			}
		});
		
		tvOut=(TextView)findViewById(R.id.tvOut);
		//tvOut.setText(getIntent().getStringExtra("txt"));
		
		Bundle data=getIntent().getExtras();
		String txt=data.getString("txt");
		tvOut.setText(txt);
	}
}
