package com.applite.uicontrols;

import android.content.Context;
import android.content.Intent;

public class ListCellData {
	
	public ListCellData(Context context,String controlName,Intent relatedIntent){
		this.context=context;
		this.controlName=controlName;
		this.relatedIntent=relatedIntent;
	}
	
	private String controlName="";

	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	
	private Context context=null;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	private Intent relatedIntent=null;

	public Intent getRelatedIntent() {
		return relatedIntent;
	}

	public void setRelatedIntent(Intent relatedIntent) {
		this.relatedIntent = relatedIntent;
	}
	
	public void startActivity(){
		//todo
		getContext().startActivity(getRelatedIntent());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getControlName();
	}
	
}
