package com.applite.androidallinone;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author heyongqiang
 * android的属性动画Animator与Animation学习示例
 */
public class AtyAnimatorAndAnimation extends Activity implements OnClickListener{
	
	private Button btn_animation;
	
	private Button btn_animator;
	
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_animator_animation);
		init();
	}
	
	private void init(){
		btn_animation=(Button)findViewById(R.id.btn_animation);
		btn_animator=(Button)findViewById(R.id.btn_animator);
		imageView=(ImageView)findViewById(R.id.iv_donghua);
		
		btn_animation.setOnClickListener(this);
		btn_animator.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_animation)
		{
			TranslateAnimation animation=new TranslateAnimation(0, 200, 0, 0);
			animation.setDuration(1000);
			imageView.startAnimation(animation);
		}
		else if(v.getId()==R.id.btn_animator)
		{
			//ObjectAnimator
		}
	}
}
