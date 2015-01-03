package com.applite.webviewdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String url="http://2014.qq.com";
	
	private WebView webView;
	
	//刷新按钮
	private Button btnRefresh;
	
	//后退按钮
	private Button btnBack;
	
	//webview的url地址
	private TextView webUrl;
	
	//错误码展示
	private TextView tv_webview_error;
	
	//进度dialog
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		/*
		Uri uri=Uri.parse(url);
		Intent intent=new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		*/
		init();
	}
	
	//改写手机物理按键的返回逻辑
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println(keyCode);
		System.out.println(KeyEvent.KEYCODE_BACK);
		//返回键+判断webview是否能够返回
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			
			Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
			System.out.println(webView.canGoBack());
			if(webView.canGoBack())
			{
				webView.goBack();
				//注意这一行
				return true;
			}
		}
		else {
			//退出程序
			System.exit(0);
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	//后退和刷新
	class WebViewListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_refresh:
				webView.reload();
				break;
			case R.id.btn_back:
				webView.goBack();
				break;
			default:
				break;
			}
		}
		
	}
	
	//下载文件
	class Download implements DownloadListener
	{

		@Override
		public void onDownloadStart(String url, String userAgent,
				String contentDisposition, String mimetype, long contentLength) {
			System.out.println("------------->"+url);
			if(url.endsWith(".apk"))
			{
				//第一种方式 自定义的
				new HttpThread(url).start();
				
				//通过系统的浏览器下载
				Uri uri=Uri.parse(url);
				Intent intent=new Intent(Intent.ACTION_VIEW,uri);
				startActivity(intent);
			}
			
		}
		
	}
	
	private void init(){
		btnBack=(Button)findViewById(R.id.btn_back);
		btnRefresh=(Button)findViewById(R.id.btn_refresh);
		webUrl=(TextView)findViewById(R.id.tv_webview_url);
		tv_webview_error=(TextView)findViewById(R.id.tv_webview_error);
		webView=(WebView)findViewById(R.id.webView);
		
		//启用使用javascript
		WebSettings webSettings=webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		//webview缓存的使用（优先使用缓存 默认webview不使用缓存）
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		btnBack.setOnClickListener(new WebViewListener());
		btnRefresh.setOnClickListener(new WebViewListener());
		
		webView.setDownloadListener(new Download());
		
		//页面加载进度
		webView.setWebChromeClient(new WebChromeClient(){
			//自定义webview的title
			@Override
			public void onReceivedTitle(WebView view, String title) {
				
				if(webView.getVisibility()==View.GONE)
				{
					tv_webview_error.setVisibility(View.GONE);
					webView.setVisibility(View.VISIBLE);
				}
				webUrl.setText(title);
				super.onReceivedTitle(view, title);
			}
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				//newProgress 范围1-100
				if(newProgress==100)
				{
					//网页加载完毕 关闭进度条
					closeLoadDialog();
				}
				else {
					//网页正在加载
					openLoadDialog(newProgress);
				}
				super.onProgressChanged(view, newProgress);
			}

			private void openLoadDialog(int newProgress) {
				// TODO Auto-generated method stub
				if(progressDialog==null)
				{
					progressDialog=new ProgressDialog(MainActivity.this);
					progressDialog.setTitle("正在加载中...");
					//设置为横向的进度条
					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					progressDialog.setProgress(newProgress);
				}
				else {
					progressDialog.setProgress(newProgress);
				}
				progressDialog.show();
				
			}

			private void closeLoadDialog() {
				// TODO Auto-generated method stub
				if(progressDialog!=null && progressDialog.isShowing())
				{
					progressDialog.dismiss();
					progressDialog=null;
				}
			}
		});
		
		//覆盖webview默认通过第三方或者系统浏览器打开浏览器的行为
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//返回值为true 控制在webview中打开
				//返回值为false 控制在系统或者第三方中打开
				view.loadUrl(url);
				return true;
			};
			
			//webview错误时候的处理
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				
				super.onReceivedError(view, errorCode, description, failingUrl);
				//加载本地页面展示错误
				//view.loadUrl("file:///android_asset/error.html");
				
				//显示错误信息
				tv_webview_error.setText("404错误");
				webView.setVisibility(View.GONE);
			}

		});
		
		//加载本地文件
		//"file:///"
		//webView.loadUrl("file:///android_asset/example.html");
		
		//加载web资源
		webView.loadUrl("http://2014.qq.com");
		
	}
}
