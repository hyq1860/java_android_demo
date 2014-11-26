package com.applite.externalstorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		File dir = Environment.getExternalStorageDirectory();
		File dataFile = new File(dir, "data.txt");

		//ctrl+shift+f代码格式化
		try {
			if (!dataFile.exists()) {
				dataFile.createNewFile();
			}
			
			FileOutputStream fos=new FileOutputStream(dataFile);
			fos.write(new String("hello android").getBytes("utf-8"));
			fos.flush();
			fos.close();
			
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis=new FileInputStream(dataFile);
			//读取小文件
			byte[] bytes=new byte[fis.available()];
			fis.read(bytes);
			fis.close();
			String str=new String(bytes,"utf-8");
			TextView txtView=(TextView)findViewById(R.id.txtView);
			txtView.setText(str);
			System.out.println(str);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
