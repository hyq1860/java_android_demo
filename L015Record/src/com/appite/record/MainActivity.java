package com.appite.record;

import java.io.File;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {
	
	private MediaRecorder recorder=null;
	
	private void startRecord() {
		if(recorder==null)
		{
			File dir=new File(Environment.getExternalStorageDirectory(),"sounds");
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			
			File soundFile=new File(dir,System.currentTimeMillis()+".amr");
			if(!soundFile.exists())
			{
				try {
					soundFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			recorder=new MediaRecorder();
			//注意必须按照这个顺序，要不会出错
			//输入源
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			//格式
			recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);
			//编码
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
			
			recorder.setOutputFile(soundFile.getAbsolutePath());
			try {
				recorder.prepare();
				recorder.start();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void stopRecord(){
		if(recorder!=null)
		{
			recorder.stop();
			recorder.release();
			recorder=null;
		}
	}
	
	private OnClickListener btnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnStartRecord:
				startRecord();
				break;
			case R.id.btnStopRecord:
				stopRecord();
				break;
			default:
				break;
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnStartRecord).setOnClickListener(btnClickListener);
        
        findViewById(R.id.btnStopRecord).setOnClickListener(btnClickListener);
        
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
