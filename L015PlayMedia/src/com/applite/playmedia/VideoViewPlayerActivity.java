package com.applite.playmedia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewPlayerActivity extends Activity {
	
	private VideoView vv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		vv=new VideoView(this);
		setContentView(vv);
		//注意添加权限
		vv.setVideoPath("/mnt/sdcard/movie.MP4");
		vv.setMediaController(new MediaController(this));
	}
}
