package com.applite.uicontrols;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class AtyUsingDatePicker extends Activity {
	
	private Button btnSelectDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.aty_using_datepicker);
		
		btnSelectDate=(Button)findViewById(R.id.btnSelectDate);
		btnSelectDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(AtyUsingDatePicker.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						btnSelectDate.setText(String.format("%d:%d:%d", year,monthOfYear,dayOfMonth));
						
					}
				}, 2013, 10, 21).show();
				
			}
		});
	}
}
