package com.applite.androidlibrary;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class CommonRequest extends Request<String> {

	private Listener<String> mListener;
	
	private Map<String, String> mMap;
	
	public CommonRequest(int method, String url,Map<String, String> map,Listener<String> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener=listener;
		this.mMap=map;
	}
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		if(mMap == null)
		{
			mMap=new HashMap<String, String>();
		}
		return mMap;
	}
	
	public CommonRequest(String url,Map<String, String> map,Listener<String> listener, ErrorListener errorListener)
	{
		this(Method.GET, url, map, listener, errorListener);
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} 
		return Response.success(parsed,HttpHeaderParser.parseCacheHeaders(response));
	}


	//回调
	@Override
	protected void deliverResponse(String response) {
		if(mListener!=null)
		{
			mListener.onResponse(response);
		}
	}
	

}
