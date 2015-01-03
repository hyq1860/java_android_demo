package com.applite.androidlibrary;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;


import libcore.io.DiskLruCache;
import android.R.integer;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Environment;
//import android.util.LruCache;
import android.support.v4.util.LruCache;

/**
 * @author heyongqiang
 * 单例模式
 * https://developer.android.com/training/volley/index.html
 */
/**
 * @author pcher_000
 *
 */
public class VolleyHelper {
	
	
	private static class SingletonHolder{
		private static final VolleyHelper INSTANCE = new VolleyHelper();
	}
	
	private VolleyHelper(){}
	
	public static final VolleyHelper getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	private static final String TAG = "Volley";
	
	private Context mContext;
	
	private Context getContext(){
		//return AppliteApplication.getApplicationContext();
		return mContext;
	}
	
	//是否设置过context 确保只调用一次
	private boolean isSetContexted=false;
	
	public void setContext(Context context){
		if(!isSetContexted)
		{
			this.mContext=context;
			isSetContexted=true;
		}
	}
	
	//volley请求队列
	private RequestQueue mRequestQueue;
	
	private ImageLoader mImageLoader;
	
	public RequestQueue getRequestQueue(){
		if(mRequestQueue==null)
		{
			mRequestQueue=Volley.newRequestQueue(getContext());
		}
		return this.mRequestQueue;
	}
	
	
	
	public ImageLoader getImageLoader() {
		getRequestQueue();
		if(mImageLoader==null)
		{
			initCache();
			mImageLoader=new ImageLoader(this.mRequestQueue, mLruBitmapCache);
		}
		
		return this.mImageLoader;
	}
	
	/*
	 * 图片内存缓存 设置为程序可用内存的8分之一
	 * int maxMemory = (int) Runtime.getRuntime().maxMemory();  
     * int cacheSize = maxMemory / 8; 
	 * */
	//private LruCache<String, Bitmap> mMemoryCache;
	
	/*
	 * 外部存储缓存
	 * */
	private DiskLruCache mDiskLruCache;
	
    //private ImageCache mImageCache;
	
	private LruBitmapCache mLruBitmapCache;
	
	/*
	 * 初始化内存 和磁盘lru cache
	 * */
	private void initCache(){
		/*
		if(mMemoryCache==null)
		{
			// 获取应用程序最大可用内存  
	        int maxMemory = (int) Runtime.getRuntime().maxMemory();  
	        int cacheSize = maxMemory / 8;  
	        // 设置图片缓存大小为程序最大可用内存的1/8  
			mMemoryCache=new LruCache<String, Bitmap>(cacheSize){
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getByteCount();
				}
			};
		}
		
		if(mImageCache==null)
		{
			mImageCache=new ImageCache() {
				
				@Override
				public void putBitmap(String key, Bitmap bitmap) {
					if(getBitmap(key)==null)
					{
						mMemoryCache.put(key, bitmap);
					}
				}
				
				@Override
				public Bitmap getBitmap(String key) {
					return mMemoryCache.get(key);
				}
			};
		}
		*/
		
		if(mLruBitmapCache==null)
		{
	        //LruBitmapCache.getCacheSize(getContext())
			mLruBitmapCache=new LruBitmapCache(LruBitmapCache.getCacheSize(getContext()));
		}
		
		//获取图片缓存
		File cacheDir=getDiskCacheDir(getContext(), "thumb");
		if(!cacheDir.exists())
		{
			cacheDir.mkdirs();
		}
		try {
			mDiskLruCache=DiskLruCache.open(cacheDir, getAppVersion(getContext()), 1, 10 * 1024 * 1024);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** 
     * 获取当前应用程序的版本号。 
     */  
    public int getAppVersion(Context context) {  
        try {  
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),0);  
            return info.versionCode;  
        } catch (NameNotFoundException e) {  
            e.printStackTrace();  
        }  
        return 1;  
    } 
	
	/*
	 * 根据传入的uniqueName获取图片缓存的路径
	 * */
	public File getDiskCacheDir(Context context,String uniqueName)
	{
		String cachePath;
		//sd卡挂载
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				||!Environment.isExternalStorageRemovable())
		{
			cachePath=context.getExternalCacheDir().getPath();
		}
		else {
			cachePath=context.getCacheDir().getPath();
		}
		return new File(cachePath+File.separator+uniqueName);
	}
	
	/*
	 * 讲缓存记录同步到journal文件中
	 * */
	public void flushDiskCache(){
		if(mDiskLruCache!=null)
		{
			try {
				mDiskLruCache.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 使用md5对传入的key进行加密并返回 防止有些url中字符不能作为key的情况
	 * */
	public String hashKeyForDisk(String key)
	{
		String cacheKey;
		try {
			final MessageDigest mDigest= MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey=bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey=String.valueOf(key.hashCode());
		}
		return cacheKey;
	}
	
	private String bytesToHexString(byte[] bytes)
	{
		StringBuilder sb=new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			String hex=Integer.toHexString(0xFF & bytes[i]);
			if(hex.length()==1){
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	/*
	 * 
	 * */
	public <T> void addToRequestQueue(Request<T> request,String tag)
	{
		request.setTag(tag.equals("")||tag==null?TAG:tag);
		VolleyLog.d("Adding request to queue: %s", request.getUrl());
		getRequestQueue().add(request);
	}
	
	public <T> void addToRequestQueue(Request<T> request)
	{
		addToRequestQueue(request,null);
	}
	
	/*
	 * 取消请求 可以再activity的onStop中使用
	 * */
	public void cancelRequest(Object tag){
		if(mRequestQueue!=null)
		{
			mRequestQueue.cancelAll(tag);
		}
	}
	
	
	
	public void request(String method,String url,Map<String, String> map,int tag,ResponseListener responseListener){
		final ResponseListener tempListener=responseListener;
		final int tempTag=tag;
		
		Listener<String> listener=new Listener<String>() {

			@Override
			public void onResponse(String response) {
				tempListener.onRequestSuccess(response, tempTag);
			}
		};
		
		ErrorListener errorListener=new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				tempListener.onRequestFailure(error, tempTag);
			}
		};
		String httpType=method.toLowerCase();
		CommonRequest commonRequest=null;;
		if(httpType.equals("get"))
		{
			commonRequest=new CommonRequest(url, map, listener, errorListener);
		}
		else if(httpType.equals("post")||httpType!=null || httpType.equals("")){
			commonRequest=new CommonRequest(Method.POST,url, map, listener, errorListener);
		}
		
		addToRequestQueue(commonRequest,tag+"");
	}
	
}
