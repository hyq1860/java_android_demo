package com.applite.androidallinone;

import android.content.Context;
import android.content.Intent;

public class ListItem {
	
	private Context context;
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	//demo名称
	private String name;
	
	private Intent intent;
	
	public ListItem(Context context,String name,Intent intent){
		this.context = context;
		this.name=name;
		this.intent=intent;
	}
	
	
	
	public void startActivity(){
		getContext().startActivity(getIntent());
	}
	
	@Override
	public String toString() {
		
		return getName();
	}
}
