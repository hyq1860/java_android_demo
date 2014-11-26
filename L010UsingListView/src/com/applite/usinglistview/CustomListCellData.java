package com.applite.usinglistview;

import android.R.interpolator;

public class CustomListCellData {
	public CustomListCellData(String name,String desc,int iconId){
		this.name=name;
		this.desc=desc;
		this.iconId=iconId;
	}
	
	private String name="";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	private String desc="";
	private int iconId=0;
}
