package com.applite.uicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class AtyUsingRadioGroup extends Activity {

	private RadioButton radioRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//设置视图
		setContentView(R.layout.aty_using_radiogroup);
		
		radioRight=(RadioButton)findViewById(R.id.radioRight);
		
		findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(radioRight.isChecked())
				{
					//当前aty的实例
//					new AlertDialog.Builder(AtyUsingRadioGroup.this).setTitle("判断").setMessage("回答正确").setPositiveButton("关闭", new DialogInterface.OnClickListener() {
//						
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							// TODO Auto-generated method stub
//							
//						}
//					}).show();
					new AlertDialog.Builder(AtyUsingRadioGroup.this).setTitle("判断").setMessage("回答正确").setPositiveButton("关闭",null).show();
				}
				else
				{
					new AlertDialog.Builder(AtyUsingRadioGroup.this).setTitle("判断").setMessage("回答错误").setPositiveButton("关闭",null).show();
				}
			}
			
		});
	}
}
