package com.applite.getmyphonenumber;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class PhoneHelper {
	
	public static List<PhoneInfo> phoneList=new ArrayList<PhoneInfo>();
	
	public static List<PhoneInfo> getNumber(Context context)
	{
		Cursor cursor=context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
		String phoneNumber;
		String contractName;
		while (cursor.moveToNext()) {
			phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
			contractName=cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
			System.out.println(contractName+"__"+phoneNumber);
			PhoneInfo phoneInfo=new PhoneInfo(contractName, phoneNumber);
			phoneList.add(phoneInfo);
		}
		return phoneList;
	}
}
