package com.applite.fragmentdemo;

import com.applite.fragmentdemo.MyFragment5.MyListener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity4 extends Activity implements MyListener {
	
	private EditText editText;
	
	private Button send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_4);
		
		editText=(EditText)findViewById(R.id.editText);
		send=(Button)findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text= editText.getText().toString();
				
				MyFragment5 myFragment5=new MyFragment5();
				Bundle bundle=new Bundle();
				bundle.putString("name", text);
				myFragment5.setArguments(bundle);
				
				FragmentManager fragmentManager= getFragmentManager();
				FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
				fragmentTransaction.add(R.id.layout4, myFragment5, "myFragment5");
				fragmentTransaction.commit();
				
				Toast.makeText(Activity4.this, "向MyFragment5发送数据"+text, Toast.LENGTH_SHORT).show();
			}
		});
		
		//
		FragmentManager fragmentManager=getFragmentManager();
		Fragment fragment=fragmentManager.findFragmentById(R.id.frag);
		//强制类型转换
		MyFragment frag=(MyFragment)fragment;
		frag.setData("fragment静态传值");
	}

	@Override
	public void thank(String code) {
		Toast.makeText(Activity4.this, "已经成功接收到MyFragment5回传的数据："+code, Toast.LENGTH_SHORT).show();
	}
}
