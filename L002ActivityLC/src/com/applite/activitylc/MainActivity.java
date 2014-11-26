package com.applite.activitylc;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button btnStartAty1;
	private TextView tvOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvOut = (TextView)findViewById(R.id.tvOut);
        
        
        btnStartAty1=(Button) findViewById(R.id.btnStartAty1);
        btnStartAty1.setOnClickListener(new View.OnClickListener() {
		 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this, Aty1.class);
				//两种传递方式
				//传递简单简单数据
				//i.putExtra("txt", "你好 at1y1");
				
				//传递复杂数据
				Bundle data=new Bundle();
				data.putString("txt", "你好 aty1");
				i.putExtras(data);
				
				// 无返回值的启动activity
				//startActivity(i);
			    // 有返回值的启动activity
				startActivityForResult(i, 0);
			}
		});
        
        System.out.println("onCreate");
    }
    
    //接收返回值
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
    	if(data!=null)
    	{
    		String result=data.getStringExtra("result");
    		tvOut.setText(result);
    	};
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	System.out.println("onStart");
    	
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	System.out.println("onResume");
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	System.out.println("onPause");
    }
    
    @Override
    protected void onStop(){
    	super.onStop();
    	System.out.println("onStop");
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	System.out.println("onDestory");
    }
    
    @Override
    protected void onRestart(){
    	super.onRestart();
    	System.out.println("onRestart");
    }
}
