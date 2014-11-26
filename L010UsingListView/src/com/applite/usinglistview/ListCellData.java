package com.applite.usinglistview;

import android.R.interpolator;
import android.R.string;

public class ListCellData {
	
	public ListCellData(String userName,int sex,int age)
	{
		this.userName=userName;
		this.sex=sex;
		this.age=age;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getUserName()+" "+getAge();
	}
	
	private String userName="美女";
	
	private int sex=1;
	
	private int age=66;
}
