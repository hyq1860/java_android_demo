package com.applite.usingbroadcastreceiver;

import javax.security.auth.PrivateCredentialPermission;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	private Button btnSendBroadcast;
	
	private Button btnRegisterBroadButton;
	
	private Button btnUnRegisterBroadButton;
	
	private final MyBroadcastReceiver myBroadcastReceiver=new MyBroadcastReceiver();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //发送
        btnSendBroadcast=(Button)findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent i=new Intent(MainActivity.this,MyBroadcastReceiver.class);
				Intent i=new Intent(MyBroadcastReceiver.ACTION);
				i.putExtra("app", "applite");
				sendBroadcast(i);
				
			}
		});
        
        
        
        //注册
        btnRegisterBroadButton=(Button)findViewById(R.id.btnRegisterBroadcast);
        btnRegisterBroadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerReceiver(myBroadcastReceiver, new IntentFilter(MyBroadcastReceiver.ACTION));
			}
		});
        
        //反注册
        btnUnRegisterBroadButton=(Button)findViewById(R.id.btnUnRegisterBroadcast);
        btnUnRegisterBroadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unregisterReceiver(myBroadcastReceiver);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
