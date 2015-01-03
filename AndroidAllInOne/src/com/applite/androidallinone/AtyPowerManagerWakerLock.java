package com.applite.androidallinone;

import org.apache.http.impl.conn.tsccm.WaitingThread;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class AtyPowerManagerWakerLock extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	private WakeLock wakeLock;
	
	private void acquireWakeLock() {
	    if (wakeLock ==null) {
	    	Log.d("wakeLock", "Acquiring wake lock");
	        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, this.getClass().getCanonicalName());
	        wakeLock.acquire();
	    }

	}

	private void releaseWakeLock() {
	    if (wakeLock !=null&& wakeLock.isHeld()) {
	        wakeLock.release();
	        wakeLock =null;
	    }
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		acquireWakeLock();
	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
		releaseWakeLock();
	}
	/*
	 * 
	<uses-permission android:name="android.permission.WAKE_LOCK"/>
	<uses-permission android:name="android.permission.DEVICE_POWER"/>
	 * 
	 * */
}
