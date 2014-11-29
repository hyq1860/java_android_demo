package com.applite.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends Activity {
	
	private Button button;
	private Boolean flag=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_3);
		
		init();
		
		button=(Button)findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager=getFragmentManager();
				FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
				if(flag){
					MyFragment4 myFragment4=new MyFragment4();
					fragmentTransaction.replace(R.id.layout,myFragment4);
					flag=false;
				}
				else {
					MyFragment3 myFragment3=new MyFragment3();
					fragmentTransaction.replace(R.id.layout,myFragment3);
					flag=true;
				}
				fragmentTransaction.commit();
			}
		});
		
	}

	private void init() {
		FragmentManager fragmentManager=getFragmentManager();
		FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
		MyFragment3 myFragment3=new MyFragment3();
		fragmentTransaction.add(R.id.layout, myFragment3);
		fragmentTransaction.commit();
		
	}
}
