package com.applite.fragmentdemo;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnCheckedChangeListener {

	private RadioGroup radioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
		//让activity自动实现点击变化的事件
		radioGroup.setOnCheckedChangeListener(this);
	}

	
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.first:
		{
			Intent intent=new Intent(this, Activity2.class);
			startActivity(intent);
		}
		break;
		case R.id.second:
		{
			MyFragment2 myFragment2=new MyFragment2();
			//开启事物
			FragmentManager fragmentManager=getFragmentManager();
			FragmentTransaction beginTransaction=fragmentManager.beginTransaction();
			//注意这里的R.id.frame
			beginTransaction.add(R.id.frame, myFragment2);
			//back按键可以返回上一个的状态
			beginTransaction.addToBackStack(null);
			beginTransaction.commit();
		}
		break;
		case R.id.third:
		{
			Intent intent=new Intent(MainActivity.this,Activity3.class);
			startActivity(intent);
		}
		break;
		case R.id.fourth:
		{
			
		}
		break;

		default:
			break;
		}
	}

	
}
