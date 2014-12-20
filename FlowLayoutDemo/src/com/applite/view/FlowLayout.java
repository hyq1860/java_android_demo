package com.applite.view;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.provider.LiveFolders;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		//调用三个构造函数方法
		this(context,attrs,0);
	}

	public FlowLayout(Context context) {
		//调用两个构造函数方法
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//宽度
		int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
		//宽模式
		int modeWidth=MeasureSpec.getMode(widthMeasureSpec);
		
		//高度
		int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
		//高模式
		int modeHeight=MeasureSpec.getMode(heightMeasureSpec);
		
		//wrap_content
		int width=0;
		int height=0;
		
		//记录每一行的宽度和高度
		int lineWidth=0;
		int lineHeight=0;
		
		//得到ViewGroup内部子元素个数
		int childCount=getChildCount();
		
		for (int i = 0; i < childCount; i++) {
			//根据索引获取子view
			View child=getChildAt(i);
			//测量子view的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			//得到LayoutParams(为何  查看generateLayoutParams )
			MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
			
			//子view占据的宽度
			int childWidth=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
			//子view占据的高度
			int childHeight=child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
			
			//判断是否换行
			if(lineWidth+childWidth > sizeWidth-getPaddingLeft()-getPaddingRight())
			{
				//对比得到最大的宽度
				width=Math.max(width, lineWidth);
				//重置lineHeight
				lineWidth=childWidth;
				
				//记录行高
				height+=lineHeight;
				lineHeight=childHeight;		
			}
			else//未换行
			{
				//叠加行宽
				lineWidth+=childWidth;
				//当前行最大的高度设置给行高
				lineHeight=Math.max(height, childHeight);
			}
			
			//到达最后一个控件
			if(i==childCount-1)
			{
				width=Math.max(lineWidth, width);
				height+=lineHeight;
			}
		}
		
		Log.e("Tag", "sizeWidth:"+sizeWidth);
		Log.e("Tag", "sizeHeight:"+sizeHeight);
		
		//wrap_content
		setMeasuredDimension(
				modeWidth==MeasureSpec.EXACTLY ? sizeWidth:width+getPaddingLeft()+getPaddingRight(), 
				modeHeight==MeasureSpec.EXACTLY? sizeHeight:height+getPaddingTop()+getPaddingBottom()
		);
	}
	
	/*
	 * 存储所有的view
	 */
	private List<List<View>> mAllViews=new ArrayList<List<View>>();
	
	/*
	 * 每一行的高度
	 * */
	private List<Integer> mLineHeight=new ArrayList<Integer>();
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();
		mLineHeight.clear();
		
		//获取当前ViewGroup宽度
		int width=getWidth();
		
		int lineWidth=0;
		int lineHeight=0;
		
		List<View> lineViews=new ArrayList<View>();
		
		int childCount=getChildCount();
		
		for (int i = 0; i < childCount; i++) {
			View child=getChildAt(i);
			
			MarginLayoutParams lp= (MarginLayoutParams)child.getLayoutParams();
			
			int childWidth=child.getMeasuredWidth();
			int childHeight=child.getMeasuredHeight();
			
			//如果需要换行 考虑flowlayout padding 
			if(childWidth + lineWidth + lp.leftMargin + lp.rightMargin>width - getPaddingLeft() - getPaddingRight())
			{
				//记录LineHeight
				mLineHeight.add(lineHeight);
				
				//记录当前行的Views
				mAllViews.add(lineViews);
				
				//重置行宽和行高
				lineWidth=0;
				lineHeight=childHeight+lp.topMargin+lp.bottomMargin;
				
				lineViews=new ArrayList<View>();
			}
			
			lineWidth+=childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight=Math.max(lineHeight, childHeight+lp.topMargin+lp.bottomMargin);
			lineViews.add(child);
		}
		
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);	
		
		//设置子view的位置
		int left=getPaddingLeft();
		int top=getPaddingTop();
		
		int lineNum=mAllViews.size();
		for (int i = 0; i < lineNum; i++) {
			//当前行所有的view
			lineViews=mAllViews.get(i);
			lineHeight=mLineHeight.get(i);
			for (int j = 0; j < lineViews.size(); j++) {
				View child=lineViews.get(j);
				//判断child的状态 不显示就continue
				if(child.getVisibility()==View.GONE)
				{
					continue;
				}
				
				MarginLayoutParams lp=(MarginLayoutParams)child.getLayoutParams();
				
				//获取子view的left top right bottom
				int lc=left+lp.leftMargin;
				int tc=top+lp.topMargin;
		        int rc=lc+child.getMeasuredWidth();
		        int bc=tc+child.getMeasuredHeight();
		        
		        //为子view进行布局
		        child.layout(lc, tc, rc, bc);
		        
		        //一行内只改变自view的离左边距的位置
		        left+=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
			}
			//换行的时候left需要重置 top需要叠加
			left=getPaddingLeft();//考虑flowlayout的padding
			top+=lineHeight;
		}
	}
	
	/*
	 * 与当前ViewGroup对应的LayoutParams
	 * */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}
}
