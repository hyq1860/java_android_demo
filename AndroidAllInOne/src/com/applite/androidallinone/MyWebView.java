package com.applite.androidallinone;

import android.webkit.WebView;

import android.R.integer;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
//http://tmacsky.diandian.com/post/2014-07-07/40062162347
//http://www.stormzhang.com/android/2013/07/27/android-scrollview-nested-webview/
//http://www.apkbus.com/android-166155-1-1.html
public class MyWebView extends WebView {
	private Context context;
	private boolean isScroll = true;// webview 是否滚动

	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();

	public MyWebView(Context context) {
		super(context);
	}

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 当拦截触摸事件到达此位置的时候，返回true，
		// 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
		// 和android的触屏事件由上至下一层一层传播有关
		return isScroll;
	}
	
	private int mMinDistance=0;
	
	@Override
	protected void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
	     if ( (getContentHeight()*getScale() - (top + getHeight())) <= mMinDistance )
	     {
	         Log.i("MYACT","content height: "+getContentHeight()+" top: "+top+" Height: "+getHeight()+" minDistance: "+mMinDistance);
	         Log.i("MYACT", "Reached bottom");
	         //mOnBottomReachedListener.onBottomReached(this);
	     }
	     else{
	         Log.i("MYACT","content height: "+getContentHeight()+" top: "+top+" Height: "+getHeight()+" minDistance: "+mMinDistance);
	         Log.i("MYACT", "Not at bottom");
	         //mOnBottomReachedListener.onNotAtBottom(this);
	     }
	     super.onScrollChanged(left, top, oldLeft, oldTop);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("d","action"+event.getAction());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("view","ACTION_DOWN");
			// webview被点击到，即可滑动
			isScroll = true;
			curP.x = event.getX();
			curP.y = event.getY();
			// 通知父控件现在进行的是本控件的操作，不要对我的操作进行干扰
			getParent().requestDisallowInterceptTouchEvent(true);
			break;

		case MotionEvent.ACTION_MOVE:
			Log.d("d",getScrollY()+"");
			float lastY = event.getY(event.getPointerCount() - 1);
			if (isBottom())// 如果到达底部，先设置为不能滚动
				isScroll = false;
			// 如果到达底部，但开始向上滚动，那么webview可以滚动
			if (isBottom() && (curP.y - lastY < 0))
				isScroll = true;
			if (isTop())// 滑到顶部不能再滑
				isScroll = false;
			if (isTop() && (curP.y - lastY > 0))// 滑动到顶部，向下滑，可以滑到
				isScroll = true;
			getParent().requestDisallowInterceptTouchEvent(!isScroll);
			break;
		case MotionEvent.ACTION_UP:
			Log.d("d","ACTION_UP");
			isScroll = false;
			break;
		}

		return super.onTouchEvent(event);
	}

	/**
	 * 判断是否到WebView达底部
	 */
	private boolean isBottom() {
		// WebView的总高度
		float contentHeight = getContentHeight() * getScale();
		// WebView的现高度
		float currentHeight = getHeight() + getScrollY();
		Log.d("d","contentHeight"+contentHeight+":currentHeight:"+currentHeight+(contentHeight - currentHeight)+"");
		// 之间的差距小于2便认为滑动到底部
		return contentHeight - currentHeight < 10;
	}

	private boolean isTop() {
		
		// 当ScrollY为0是到达顶部
		return getScrollY() == 0;
	}
	
	private int height;
	
	public void getRealHeight(){
		ViewTreeObserver viewTreeObserver  = getViewTreeObserver();

		viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {
		                   @Override
		                   public boolean onPreDraw() {                                
		                	   height = getMeasuredHeight();
		                           if( height != 0 ){
		                                   //Toast.makeText(getActivity(), "height:"+height,Toast.LENGTH_SHORT).show();
		                                   getViewTreeObserver().removeOnPreDrawListener(this);
		                           }
		                           return false;
		                   }
		           });
	}
	
}