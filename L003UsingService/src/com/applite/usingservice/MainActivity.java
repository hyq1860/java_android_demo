package com.applite.usingservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener, ServiceConnection{

	private Button btnStartService;
	private Button btnStopService;
	private Intent serviceIntent;
	
	private Button btnBindService;
	private Button btnUnbindService;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        serviceIntent=new Intent(this, EchoService.class);
        
        btnStartService=(Button)findViewById(R.id.btnStartService);
        
        btnStopService=(Button)findViewById(R.id.btnStopService);
        
        btnBindService=(Button)findViewById(R.id.btnBindService);
        btnUnbindService=(Button)findViewById(R.id.btnUnbindService);
        
        //设置事件监听器
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.btnStartService:
			startService(serviceIntent);
			break;
		case R.id.btnStopService:
			stopService(serviceIntent);
			break;
		case R.id.btnBindService:
			bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
			break;
		case R.id.btnUnbindService:
			unbindService(this);
			break;
		}
		
	}


	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		System.out.println("onServiceConnected");
	}


	//一般情况下，服务崩溃的时候会触发
	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
	}
}
