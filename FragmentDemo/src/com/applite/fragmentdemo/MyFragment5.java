package com.applite.fragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment5 extends Fragment {
	
	private String code="礼尚往来，回敬你一堆数据";
	
	public interface MyListener{
		public void thank(String code);
	}
	
	public MyListener myListener;
	
	@Override
	public void onAttach(Activity activity) {

		myListener=(MyListener)activity;

		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment2, container, false);
		//此处不能直接写findViewbyid
		TextView text=(TextView)view.findViewById(R.id.text2);
		
		//获取数据
		String data= getArguments().get("name").toString();
		text.setText(data);
		Toast.makeText(getActivity(), "成功接收到来自Activity4的数据："+data, Toast.LENGTH_LONG).show();
		
		//回传数据
		Toast.makeText(getActivity(), "向Activity4回传数据："+code, Toast.LENGTH_LONG).show();
		myListener.thank(code);
		
		return view;
	}
}
