package com.applite.androidallinone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;


/**
 * 耳机插入状态监测
 *
 */
public class AtyHeadSetPlug extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_headset_plug);
		
		//广播绑定响应的过滤器
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("android.intent.action.HEADSET_PLUG");
		
		HeadsetReceiver headsetReceiver=new HeadsetReceiver();
		registerReceiver(headsetReceiver, intentFilter);
	}
	
	//耳机广播接收器
	public class HeadsetReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.hasExtra("state"))
			{
				if(intent.getIntExtra("state", 0)==0)
				{
					Toast.makeText(context, "耳机未插入", Toast.LENGTH_SHORT).show();
				}
				else if(intent.getIntExtra("state", 0)==1)
				{
					Toast.makeText(context, "耳机已插入", Toast.LENGTH_SHORT).show();
				}
			}
		}
		
	}
}
