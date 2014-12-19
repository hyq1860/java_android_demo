package com.applite.volleydemo;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

/*
 * 如何导入  Properties=》Libraries=》add external files
 * 
 * Volley是android平台的网络通讯库，目的：更快，更简单，更健壮
 * 适合通讯不大，但是通讯频繁的东西
 * volley提供的功能：
 * 1.json，图片数据的获取（异步）
 * 2.网路请求的偏序
 * 3.网络请求的优先级处理
 * 4.缓存
 * 5.多级别的取消请求
 * 6.与activity生命周期联动
 * 
 * 获取Volley
 * 
 * */

public class MainActivity extends Activity {

	private ImageView imageView;
	private NetworkImageView networkImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getJSONVolley();
		init();
		loadImageVolley();
		NetWorkImageVolley();
	}
	
    private void init(){
    	imageView=(ImageView)findViewById(R.id.imageView);
    	networkImageView=(NetworkImageView)findViewById(R.id.imageView2);
    }
	
	/*
	 * 获取json字符串 别忘记添加网络访问权限
	 * */
	public void getJSONVolley(){
		//请求队列
		RequestQueue requestQueue=Volley.newRequestQueue(this);
		
		String JSONDataUrl="http://fanyi.youdao.com/openapi.do?keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q=sex";
		//请求对象
		JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, JSONDataUrl, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						System.out.println("相应内容："+response);
						Log.e("tag", response.toString());
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
							System.out.println("网络数据获取异常");
						}
					}
				);
		//一定要注意要把请求添加进来
		requestQueue.add(jsonObjectRequest);
	}

	public void loadImageVolley(){
		
		String imageUrl="http://b.hiphotos.baidu.com/image/pic/item/574e9258d109b3debd2d5269cebf6c81800a4c24.jpg";
		RequestQueue requestQueue=Volley.newRequestQueue(this);
		//缓存
		final LruCache<String, Bitmap> lruCache=new LruCache<String, Bitmap>(20);
		
		ImageCache imageCache=new ImageCache() {
			
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}
			
			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		
		ImageLoader imageLoader=new ImageLoader(requestQueue, imageCache);
		
		ImageListener listener=imageLoader.getImageListener(imageView,R.drawable.ic_launcher, R.drawable.ic_launcher);
		
		imageLoader.get(imageUrl, listener);
	}
	
	
	public void NetWorkImageVolley(){
		String imageUrl="http://b.hiphotos.baidu.com/image/pic/item/574e9258d109b3debd2d5269cebf6c81800a4c24.jpg";
		RequestQueue requestQueue=new Volley().newRequestQueue(this);
		final LruCache<String, Bitmap> lruCache=new LruCache<String, Bitmap>(20);
		
		ImageCache imageCache=new ImageCache() {
			
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}
			
			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		
		ImageLoader imageLoader=new ImageLoader(requestQueue, imageCache);
		networkImageView.setTag("url");//why
		networkImageView.setImageUrl(imageUrl, imageLoader);
	}
}
