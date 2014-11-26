package com.applite.intents;

import java.io.File;

import android.R.interpolator;
import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //绑定事件
        findViewById(R.id.btnStartAty1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				// 显式启动
				Intent i=new Intent();
				//注意名称的全部路径
				//指定包名，类完全名称启动activity
				i.setComponent(new ComponentName("com.applite.intents", "com.applite.intents.Aty1"));
				startActivity(i);
				
				//隐式启动
				//指定intentfilter
				//Intent i=new Intent("com.applite.intents.action.Aty1");
				//startActivity(i);
				
			}
		});
        
        //打开图片按钮绑定事件
        findViewById(R.id.btnOpenImage).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//打开图片
				//记住之前导入mnt sdcard导入图片
				File f=new File("mnt/sdcard/qq.png");
				Intent i=new Intent(Intent.ACTION_VIEW);
				i.setDataAndType(Uri.fromFile(f),"image/*");
				startActivity(i);
			}
		});
        
        //打电话
        findViewById(R.id.btnCall).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse("tel:10086"));
				startActivity(i);
			}
		});
        
        //打开网页
        findViewById(R.id.btnNavToUrl).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
				startActivity(i);
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
