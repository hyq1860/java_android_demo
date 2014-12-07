package com.applite.androiduniversalimageloaderdemo;


import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

//应用程序启动
public class AppliteApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("应用程序启动");
		
		initImageLoader(getApplicationContext());
	}
	
	/*初始化ImageLoader加载类配置信息*/
	private static void initImageLoader(Context context){
		ImageLoaderConfiguration imageLoaderConfig=new ImageLoaderConfiguration.Builder(context)
		.threadPriority(Thread.NORM_PRIORITY-2)//加载图片的线程数
		.denyCacheImageMultipleSizesInMemory()//解码图像的大尺寸将在内存中缓存先前解码图像的小尺寸
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())//设置磁盘缓存文件名称
		.tasksProcessingOrder(QueueProcessingType.LIFO)//设置加载显示图片队列进程
		.writeDebugLogs()//Remove for release app
		.build();
		
		//Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(imageLoaderConfig);
	}
}
