package com.applite.usingbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

public class MyBroadcastReceiver extends BroadcastReceiver {
	
	public static final String ACTION="cn.applite.usingbroadcastreceiver.action.MyBC";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//静态注册 记得在AndroidManifest.xml中的Application nodes中添加receiver
		
		//动态注册
		System.out.println("onReceive,data="+intent.getStringExtra("app"));
	}

}
