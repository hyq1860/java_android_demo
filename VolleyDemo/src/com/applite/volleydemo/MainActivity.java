package com.applite.volleydemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
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
	private ImageView imageView3;
	
	//请求队列对象 非常适合高并发 不必为了为一个http请求创建一个对象 一般一个Activity中创建一个
	RequestQueue requestQueue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		requestQueue=Volley.newRequestQueue(this);
		init();
		getStringVolley();
		postVolley();
		getJSONVolley();
		
		imageRequestVolley();
		loadImageVolley();
		NetWorkImageVolley();
	}
	
    private void init(){
    	imageView=(ImageView)findViewById(R.id.imageView);
    	networkImageView=(NetworkImageView)findViewById(R.id.imageView2);
    	imageView3=(ImageView)findViewById(R.id.imageView3);
    }
	
    public void getStringVolley(){
    	String JSONDataUrl="http://fanyi.youdao.com/openapi.do?keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q=sex";
    	StringRequest stringRequest=new StringRequest(JSONDataUrl, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.d("getStringVolley", response);
			}
    		
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.e("getStringVolley", error.getMessage(),error);
			}
		});
    	requestQueue.add(stringRequest);
    }
    
    public void postVolley(){
    	String JSONDataUrl="http://fanyi.youdao.com/openapi.do?keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q=sex";
    	StringRequest stringRequest=new StringRequest(Method.POST, JSONDataUrl, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.d("postVolley", response);
			}
    		
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.e("postVolley", error.getMessage(),error);
			}
		}){
    		@Override
    		protected Map<String, String> getParams() throws AuthFailureError {
    			Map<String,String> map=new HashMap<String,String>();
    			map.put("key", "你好");
    			map.put("value", "测试");
    			return super.getParams();
    		}
    	};
    	requestQueue.add(stringRequest);
    }
    
	/*
	 * 获取json字符串 别忘记添加网络访问权限
	 * */
	public void getJSONVolley(){
		
		String JSONDataUrl="http://fanyi.youdao.com/openapi.do?keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q=sex";
		//请求对象
		JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, JSONDataUrl, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						System.out.println("相应内容："+response);
						Log.e("getJSONVolley", response.toString());
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
	/*
	 * 
	 ImageRequest的构造函数接收六个参数，
	 第一个参数就是图片的URL地址，这个没什么需要解释的。
	 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。
	 第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
	 第五个参数用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小。
	 第六个参数是图片请求失败的回调，这里我们当请求失败时在ImageView中显示一张默认图片。*/
	public void imageRequestVolley(){
		String imageUrl="http://b.hiphotos.baidu.com/image/pic/item/574e9258d109b3debd2d5269cebf6c81800a4c24.jpg";
		ImageRequest imageRequest=new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap) {
				imageView3.setImageBitmap(bitmap);
				
			}
		}, 0, 0, Config.RGB_565, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				imageView3.setImageResource(R.drawable.ic_launcher);
			}
		});
		requestQueue.add(imageRequest);
	}
	
	public void loadImageVolley(){
		
		String imageUrl="http://b.hiphotos.baidu.com/image/pic/item/574e9258d109b3debd2d5269cebf6c81800a4c24.jpg";
		//RequestQueue requestQueue=Volley.newRequestQueue(this);
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
		
		//创建imageloader对象
		ImageLoader imageLoader=new ImageLoader(requestQueue, imageCache);
		
		//获取一个imagelistenser对象
		ImageListener listener=imageLoader.getImageListener(imageView,R.drawable.ic_launcher, R.drawable.ic_launcher);
		
		//调用imageloader的get方法加载网络上的图片
		imageLoader.get(imageUrl, listener);
	}
	
	
	public void NetWorkImageVolley(){
		String imageUrl="http://b.hiphotos.baidu.com/image/pic/item/574e9258d109b3debd2d5269cebf6c81800a4c24.jpg";
		//RequestQueue requestQueue=new Volley().newRequestQueue(this);
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
