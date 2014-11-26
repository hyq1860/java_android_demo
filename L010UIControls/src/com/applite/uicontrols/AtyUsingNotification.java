package com.applite.uicontrols;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class AtyUsingNotification extends Activity {

	private NotificationManager nm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_using_notification);
		
		nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//取消掉通知
		nm.cancel(R.layout.aty_using_notification);
		
		findViewById(R.id.btnShowNotification).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//new Notification.Builder(AtyUsingNotification.this).add(R.drawable.ic_launcher, "Ticker Text", PendingIntent.getActivity(AtyUsingNotification.this, 1, getIntent(), 0));
				Notification n=new Notification(R.drawable.ic_launcher, "Ticker Text", System.currentTimeMillis());
				n.setLatestEventInfo(AtyUsingNotification.this, "title", "content", PendingIntent.getActivity(AtyUsingNotification.this, 1, getIntent(), 0));
				nm.notify(R.layout.aty_using_notification,n);
			}
		});
	}
	
}
