package com.applite.androidallinone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AtyScrollViewAndWebView extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_scrollview_webview);
		webView = (WebView) findViewById(R.id.wv_show);

		//启用使用javascript
				WebSettings webSettings=webView.getSettings();
				webSettings.setJavaScriptEnabled(true);
		// 覆盖webview默认通过第三方或者系统浏览器打开浏览器的行为
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值为true 控制在webview中打开
				// 返回值为false 控制在系统或者第三方中打开
				view.loadUrl(url);
				return true;
			};
			@Override
            public void onPageFinished(WebView view, String url) {
               
            }
		});
//http://stackoverflow.com/questions/22878069/android-get-height-of-webview-content-once-rendered
		ViewTreeObserver viewTreeObserver  = webView.getViewTreeObserver();

		viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {
		                   @Override
		                   public boolean onPreDraw() {                                
		                           int height = webView.getMeasuredHeight();
		                           if( height != 0 ){
		                                   //Toast.makeText(getActivity(), "height:"+height,Toast.LENGTH_SHORT).show();
		                                   webView.getViewTreeObserver().removeOnPreDrawListener(this);
		                           }
		                           return false;
		                   }
		           });
		webView.loadUrl("http://www.sina.com");
	}
}
