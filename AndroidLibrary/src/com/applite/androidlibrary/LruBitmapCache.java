package com.applite.androidlibrary;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;

public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache{

	public LruBitmapCache(int maxSize) {
		super(maxSize);
	}

	@Override
	protected int sizeOf(String key, Bitmap bitmap) {
		return bitmap.getRowBytes() * bitmap.getHeight();
	}
	
	@Override
	public Bitmap getBitmap(String key) {
		return get(key);
	}

	@Override
	public void putBitmap(String key, Bitmap bitmap) {
		if(getBitmap(key)!=null)
		{
			put(key, bitmap);
		}
	}
	
	// Returns a cache size equal to approximately three screens worth of images.
    public static int getCacheSize(Context ctx) {
        final DisplayMetrics displayMetrics = ctx.getResources().
                getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;
        // 4 bytes per pixel
        final int screenBytes = screenWidth * screenHeight * 4;

        // 获取应用程序最大可用内存  
        int maxMemory = (int) Runtime.getRuntime().maxMemory();  
        int cacheSize = maxMemory / 8;  
        // 设置图片缓存大小为程序最大可用内存的1/8  
        
        
        //return screenBytes * 3;
        //根据用户手机屏幕像素动态分配 而不是所有一刀切
        return screenBytes * (cacheSize / screenBytes);
    }


}
