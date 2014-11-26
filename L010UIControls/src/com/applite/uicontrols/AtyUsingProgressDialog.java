package com.applite.uicontrols;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AtyUsingProgressDialog extends Activity {

	private Button btnShowProgressDialog;
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_progressdialog);
		btnShowProgressDialog=(Button)findViewById(R.id.btnShowProgressDialog);
		btnShowProgressDialog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				progressDialog=ProgressDialog.show(AtyUsingProgressDialog.this, "加载中", "正在加载中，请稍候...");
				new Thread(){
					public void run() {
						try {
							Thread.sleep(3000);
							progressDialog.dismiss();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
	}
	
}
