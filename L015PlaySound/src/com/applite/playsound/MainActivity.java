package com.applite.playsound;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.R.interpolator;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	//soundpool播放比较短的声音
	private SoundPool sp;
	private int soundId;
	
	private MediaPlayer mp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sp = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        soundId=sp.load(this, R.raw.note1, 1);
        
        //播放较短的
        findViewById(R.id.btnPlaySound).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//sp.play(soundId, 1, 1, 0, 0, 0.5f);
				sp.play(soundId, 1, 1, 0, 0, 2.0f);
			}
		});
        
        //播放较长的
        findViewById(R.id.btnPlaySong).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mp!=null)
				{
					mp.start();
				}
			}
		});
    }

    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	if(mp!=null)
    	{
    		//不需要的时候释放资源
    		mp.release();
    	}
    	
    	super.onPause();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	mp=MediaPlayer.create(this, R.raw.song);
    	try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	super.onResume();
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
