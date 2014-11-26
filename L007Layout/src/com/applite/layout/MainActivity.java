package com.applite.layout;

import android.support.v7.app.ActionBarActivity;
import android.R.interpolator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

	private Button btn;
	private LinearLayout mainLinearLayout;
	private OnClickListener btnClickHandler=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mainLinearLayout.removeView(v);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(R.layout.linear_layout);
        
        mainLinearLayout=(LinearLayout)findViewById(R.id.mainLinearLayout);
        
        for(int i=0;i<5;i++)
        {
        	btn=new Button(this);
        	btn.setText("移除按钮"+i);
        	//mainLinearLayout.addView(btn);
        	//-1 -2
        	mainLinearLayout.addView(btn,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        
        	//快捷键 ctrl+1 创建属性
        	//
        	btn.setOnClickListener(btnClickHandler);
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
