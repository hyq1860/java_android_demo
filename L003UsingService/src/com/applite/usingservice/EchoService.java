package com.applite.usingservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EchoService extends Service {

	//注意在AndroidManifest.xml添加Service节点
	@Override
	public IBinder onBind(Intent arg0) {
		//删除行 ctrl+d
		// TODO Auto-generated method stub
		System.out.println("onBind");
		return echoServiceBinder;
	}
	
	//访问服务内部 定义内部binder类
	private final EchoServiceBinder echoServiceBinder=new EchoServiceBinder();
	
	public class EchoServiceBinder extends Binder{
		
	}
	
	// service只要还在运行 再次创建 不会再次创建 只有一个实例
	// startService 退出 不会销毁服务
	//bindserver启动的服务 退回 会销毁服务
	//服务的创建
	@Override
	public void onCreate(){
		System.out.println("serviceOnCreate");
		super.onCreate();
	}
	
	//服务的销毁
	@Override
	public void onDestroy() {
		System.out.println("serviceOnDestory");
		super.onDestroy();
	}
}
