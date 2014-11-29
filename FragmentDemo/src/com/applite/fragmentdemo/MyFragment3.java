package com.applite.fragmentdemo;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//fragment需要最小的sdk为11
public class MyFragment3 extends Fragment {
    /*
     * 每次创建都会绘制Fragment的view组件时候回调该方法
     * */
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
		View view=inflater.inflate(R.layout.fragment2, container, false);
		//此处不能直接写findViewbyid
		TextView text=(TextView)view.findViewById(R.id.text2);
		text.setText("Fragment3");
		Log.i("Main", "Fragment3---onCreatView()");
		return view;
	}
	
	/*
	 * 当Fragment被添加到Activity时候回调，并且只会调用一次
	 * 
	 * */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.i("Main","Fragment3---OnAttach()");
	}
	
	/*
	 * 创建Fragment会回调，并且只会调用一次
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Main","Fragment3---onCreate()");
	}
	
	/*
	 * 当Fragment所在的Activity启动完成后调用
	 * 
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("Main","Fragment3---onActivityCreated()");
	}
	
	/*
	 * 启动Fragment
	 * */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("Main","Fragment3---onStart()");
	}
	
	/*
	 * 恢复Fragment时候被回调，调用onStart()后面一定会调用onResume()方法
	 * */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("Main","Fragment3---onResume()");
	}
	
	/*
	 * 暂停Fragment的时候回调
	 * */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("Main","Fragment3---onPause()");
	}
	
	/*
	 * 停止Fragment回调
	 * */
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("Main","Fragment3---onStop()");
	}
	
	/*
	 * 销毁Fragment所包含的View组件时调用
	 * */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i("Main","Fragment3---onDestroyView()");
	}
	
	/*
	 * 销毁Fragment时调用
	 * */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("Main","Fragment3---onDestroy()");
	}
	
	/*
	 * Fragment从Activity中删除的时候会调用 只会调用一次
	 * */
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.i("Main","Fragment3---onDetach()");
	}
	
}
