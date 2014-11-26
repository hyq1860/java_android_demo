package com.applite.uicontrols;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtyUsingEditText extends Activity {

	private EditText editText;
	private Button btnGetText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_using_edittext);
		
		editText=(EditText)findViewById(R.id.editText);
		btnGetText=(Button)findViewById(R.id.btnGetText);
		
		btnGetText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text=editText.getText().toString();
				
				if(TextUtils.isEmpty(editText.getText()))
				{
					Toast.makeText(AtyUsingEditText.this, "文本不能为空",Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(AtyUsingEditText.this, text,Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
}
