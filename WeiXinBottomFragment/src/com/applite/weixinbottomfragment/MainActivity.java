package com.applite.weixinbottomfragment;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	//private Fragment contentFragment;
	private RadioGroup myTabRg;
	private FragmentChat chat;
	private FragmentAddress address;
	private FragmentFind find;
	private FragmentMe me;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}
	
	private void initView() {
		chat = new FragmentChat();
		getSupportFragmentManager().beginTransaction().replace(R.id.main_content, chat).commit();
		myTabRg = (RadioGroup)findViewById(R.id.tab_menu);
		myTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rbChat:
					chat = new FragmentChat();
					getSupportFragmentManager().beginTransaction().replace(R.id.main_content, chat).commit();
					break;
				case R.id.rbAddress:
					if (address==null) {
						address =new FragmentAddress();
					}
					Log.i("MyFragment", "FragmentAddress");
					getSupportFragmentManager().beginTransaction().replace(R.id.main_content, address).commit();
					break;
				case R.id.rbFind:
					find = new FragmentFind();
					getSupportFragmentManager().beginTransaction().replace(R.id.main_content, find).commit();
					break;
				case R.id.rbMe:
					me = new FragmentMe();
					getSupportFragmentManager().beginTransaction().replace(R.id.main_content, me).commit();
					break;
				default:
					break;
				}

			}
		});
	}
}
