package com.applite.uicontrols;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class AtyUsingCheckBox extends Activity {

	private CheckBox cb1,cb2,cb3,cb4;
	private Button btnSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//跟布局绑定
		setContentView(R.layout.aty_using_checkbox);
		
		cb1=(CheckBox)findViewById(R.id.cb1);
		cb2=(CheckBox)findViewById(R.id.cb2);
		cb3=(CheckBox)findViewById(R.id.cb3);
		cb4=(CheckBox)findViewById(R.id.cb4);
		
		findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str="中午午餐是:";
				if(cb1.isChecked())
				{
					str+=cb1.getText()+"\n";
				}
				if(cb2.isChecked())
				{
					str+=cb2.getText()+"\n";
				}
				if(cb3.isChecked())
				{
					str+=cb3.getText()+"\n";
				}
				if(cb4.isChecked())
				{
					str+=cb4.getText()+"\n";
				}
				new AlertDialog.Builder(AtyUsingCheckBox.this).setTitle("菜单").setMessage(str).show();
			}
		});
	}
}
