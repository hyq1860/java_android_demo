package com.applite.getmyphonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private ContractAdapter contractAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv=(ListView)findViewById(R.id.lv);
		contractAdapter=new ContractAdapter(PhoneHelper.getNumber(this), this);
		lv.setAdapter(contractAdapter);
	}

	
}
