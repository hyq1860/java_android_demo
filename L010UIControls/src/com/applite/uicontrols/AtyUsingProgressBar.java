package com.applite.uicontrols;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class AtyUsingProgressBar extends Activity {

	private ProgressBar pb;
	private Timer timer=null;
	private TimerTask timerTask=null;
	private int progress=0;
	
	public void  startTimer() {
		if(timer==null)
		{
			timer=new Timer();
			timerTask=new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					progress++;
					pb.setProgress(progress);
				}
			};
			
			timer.schedule(timerTask, 1000,1000);
		}
	}
	
	public void stopTimer(){
		if(timer!=null)
		{
			timerTask.cancel();
			timer.cancel();
			
			timerTask=null;
			timer=null;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_using_progressbar);
		pb=(ProgressBar)findViewById(R.id.progressBar4);
		pb.setMax(100);
		//pb.setProgress(50);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		startTimer();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stopTimer();
	}
}
