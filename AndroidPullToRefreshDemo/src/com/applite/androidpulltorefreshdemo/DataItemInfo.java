package com.applite.androidpulltorefreshdemo;

import android.R.string;
import android.graphics.Bitmap;

public class DataItemInfo {
	
	private String Title;
	private String Date;
	private Bitmap SmallImage;
	
	public  DataItemInfo(Bitmap smallImage,String title,String date)
	{
		setSmallImage(smallImage);
		setTitle(title);
		setDate(date);
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Bitmap getSmallImage() {
		return SmallImage;
	}
	public void setSmallImage(Bitmap smallImage) {
		SmallImage = smallImage;
	}
	
}
