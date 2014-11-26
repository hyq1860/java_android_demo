package com.applite.backbutton;

import android.support.v7.app.ActionBarActivity;
import android.R.interpolator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	//后退点击次数
	private int backClickCount=0;
	
	//最后一次后退点击时间
	private long lastBackClickTime=0;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //退出操作
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	//super.onBackPressed();
    	/*
    	if(backClickCount<1)
    	{
    		Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
    		backClickCount++;
    	}
    	else {
			finish();
		}
		*/
    	
    	if(lastBackClickTime<=0)
    	{
    		Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
    		lastBackClickTime=System.currentTimeMillis();
    	}
    	else {
			long currentBackClickTime=System.currentTimeMillis();
			if(currentBackClickTime-lastBackClickTime<1000)
			{
				finish();
			}
			else {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				lastBackClickTime=currentBackClickTime;
			}
		}
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
