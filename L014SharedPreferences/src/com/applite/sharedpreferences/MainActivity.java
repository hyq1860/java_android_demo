package com.applite.sharedpreferences;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class MainActivity extends ActionBarActivity {

	
	private CheckBox cb;
	private SharedPreferences sp;
	
	private static String Key_ShowDialogAtStart="showDialogAtStart";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cb=(CheckBox)findViewById(R.id.cb);
        sp = getSharedPreferences("myapp", Context.MODE_PRIVATE);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Editor e= sp.edit();
				e.putBoolean(Key_ShowDialogAtStart, isChecked);
				//提交才会存储
				e.commit();
			}
		});
        
        
        cb.setChecked(sp.getBoolean(Key_ShowDialogAtStart, false));
        
        if(cb.isChecked())
        {
        	new AlertDialog.Builder(this).setTitle("欢迎").setMessage("欢迎猛击我").setPositiveButton("关闭", null).show();
        	
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
