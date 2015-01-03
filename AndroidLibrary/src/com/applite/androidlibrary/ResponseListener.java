package com.applite.androidlibrary;

import com.android.volley.VolleyError;

public interface ResponseListener {
	public void onRequestSuccess(String response,int tag);
	
	public void onRequestFailure(Object error,int tag);
}
