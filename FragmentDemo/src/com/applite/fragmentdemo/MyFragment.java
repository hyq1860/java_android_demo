package com.applite.fragmentdemo;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//fragment需要最小的sdk为11
public class MyFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		//知识点：layout布局文件转换为view对象
		/*
		 * resource Fragment要加载的布局文件
		 * root 加载layout的父viewgroup中
		 * attachtoRoot false 不返回父viewgroup
		 * 
		 * */
		View view=inflater.inflate(R.layout.fragment, container, false);
		//此处不能直接写findViewbyid
		TextView text=(TextView)view.findViewById(R.id.text);
		text.setText("静态加载页面");
		return view;
	}
}
