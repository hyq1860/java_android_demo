package com.applite.uicontrols;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class AtyUsingImageSwitcher extends Activity {
	
	private ImageSwitcher imageSwitcher;
	private boolean showImage1=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_using_imageswitcher);
		
		imageSwitcher=(ImageSwitcher)findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
			
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				return new ImageView(AtyUsingImageSwitcher.this);
			}
		});
	
		//设置图片动画效果
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(AtyUsingImageSwitcher.this, android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(AtyUsingImageSwitcher.this, android.R.anim.fade_out));
		
		imageSwitcher.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showImage1=!showImage1;
				showCurrentImage();
			}
		});
		
		showCurrentImage();
	};
	
	private void showCurrentImage() {
		if(showImage1)
		{
			imageSwitcher.setImageResource(R.drawable.img1);
		}
		else {
			imageSwitcher.setImageResource(R.drawable.img2);
		}
	}
	
	
}
