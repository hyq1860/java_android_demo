package com.applite.fragmentdemo;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//fragment需要最小的sdk为11
public class MyFragment extends Fragment {
	
	//静态的传值 强制定义方法或者属性 强制转换在进行调用
	private String data;
	
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
		
		Button button=(Button)view.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String data=getData();//从属性中获取值
				Toast.makeText(getActivity(), "value="+data, Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
