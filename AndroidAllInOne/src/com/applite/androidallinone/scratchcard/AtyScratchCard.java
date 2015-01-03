package com.applite.androidallinone.scratchcard;

import com.applite.androidallinone.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


/**
 * @author heyongqiang
 * 刮刮卡
 * 
 * 刮刮卡的原理 Paint.setXferMode()
 * 
 * Graphics?Xfermodes
 * 
 */
public class AtyScratchCard extends Activity{
	
	private ScratchCradView  scratchCradView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_scratchcard);
		scratchCradView=(ScratchCradView)findViewById(R.id.sc);
		scratchCradView.setOnScratchCompleteListener(new ScratchCradView.OnScratchCompleteListener() {
			
			@Override
			public void complete() {
				Toast.makeText(getApplicationContext(), "用户已经挂完了 还刮个毛呀", Toast.LENGTH_SHORT).show();
			}
		});
		scratchCradView.setText("哈哈 挂完了");
	}
}
