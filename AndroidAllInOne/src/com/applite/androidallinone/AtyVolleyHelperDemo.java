package com.applite.androidallinone;

import com.android.volley.toolbox.NetworkImageView;
import com.applite.androidlibrary.ResponseListener;
import com.applite.androidlibrary.VolleyHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AtyVolleyHelperDemo extends Activity implements ResponseListener {
	
	private NetworkImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_volleyhelper);
		imageView=(NetworkImageView)findViewById(R.id.niv_volley);
		//VolleyHelper.getInstance().setContext(getApplicationContext());
		VolleyHelper.getInstance().request("get", "http://flash.weather.com.cn/wmaps/xml/china.xml", null, 0, this);
		imageView.setImageUrl("http://img0.bdstatic.com/img/image/2043d07892fc42f2350bebb36c4b72ce1409212020.jpg", VolleyHelper.getInstance().getImageLoader());
	}

	@Override
	public void onRequestFailure(Object error, int tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(String response, int tag) {
		Log.d("d", response);
	}
	
}
