package com.applite.androidallinone.scratchcard;

import java.text.BreakIterator;

import com.applite.androidallinone.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author pcher_000
 *
 */
public class ScratchCradView extends View {

	//画笔 外部
	private Paint mOutterPaint;
	
	//记录用户绘制路径
	private Path mPath;
	
	//画布
	private Canvas mCanvas;
	
	//图片
	private Bitmap mBitmap;
	
	private int mLastX;
	
	private int mLastY;
	
	//背景图
	private Bitmap backgroundBitmap;
	
	//刮刮卡内容区域外的图片
	private Bitmap mOutterBitmap;
	
	//绘制的文本
	private String mText;
	
	public void setText(String mText) {
		this.mText = mText;
		//注意 字体设置后 重新设置
		mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}

	//设置刮刮卡文字大小
	private int mTextSize;
	
	//设置刮刮卡字体颜色
	private int mTextColor;
	
	//
	private Paint mBackPaint;
	
	
	/**
	 * 记录刮刮卡刮奖信息文本的宽和高
	 */
	private Rect mTextBound;
	
	//判断遮盖区域是否消除达到阈值
	//注意并发 脏读和可见性
	volatile boolean mComplete=false;
	
	/*
	 * 刮完的回调
	 * */
	public interface OnScratchCompleteListener{
		void complete();
	};
	
	private OnScratchCompleteListener mListener;
	
	
	public ScratchCradView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public ScratchCradView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	//自定义属性的话 还是调用两个参数的构造函数 调用三个参数的方法，必须显式的调用 系统一般不会自动调用
	
	
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public ScratchCradView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
		
		//读取自定义属性
		TypedArray array = null;
		try {
			array=context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScratchCradView, defStyleAttr,0);
			int length=array.getIndexCount();
			for (int i = 0; i < length; i++) {
				int index=array.getIndex(i);
				switch (index) {
				case R.styleable.ScratchCradView_text:
					mText=array.getString(index);
					break;
				case R.styleable.ScratchCradView_textSize:
					mTextSize=(int)array.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics()));
					break;
				case R.styleable.ScratchCradView_textColor:
					mTextColor=array.getColor(index, 0x000000);
					break;
				}
			}
		} finally {
			if(array!=null)
			{
				array.recycle();
			}
		}
		
	}
	
	/**
	 * 初始化操作
	 */
	private void init()
	{
		mOutterPaint=new Paint();
		mPath=new Path();
		
		backgroundBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.t2);
		
		mOutterBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.fg_guaguaka);
		
		mText="我中了500万啦";
		
		mTextBound=new Rect();
		
		mBackPaint=new Paint();
		
		mTextSize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics());;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//widthMeasureSpec和heightMeasureSpec用来描述当前正在处理的视图可以获得的最大宽度和高度
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		//拿到空间的宽和高
		int width=getMeasuredWidth();
		int height=getMeasuredHeight();
		
		//bitmap的配置 不同配置 字节占用不同
		mBitmap=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		mCanvas=new Canvas(mBitmap);
		
		setupOutPaint();
		setupBackPaint();
		//画布灰色 遮住获奖信息
		//mCanvas.drawColor(Color.parseColor("#c0c0c0"));
		mCanvas.drawRoundRect(new RectF(0,0,width,height), 30, 30, mOutterPaint);
		mCanvas.drawBitmap(mOutterBitmap, null, new Rect(0,0,width,height), null);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action=event.getAction();
		//获取坐标值
		int x=(int)event.getX();
		int y=(int)event.getY();
		Log.d("d", "x:"+x+"y:"+y);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastX=x;
			mLastY=y;
			mPath.moveTo(mLastX, mLastY);//代表开启了一个新的路径
			break;
		case MotionEvent.ACTION_MOVE:
			//坐标差值
			int dx=Math.abs(x-mLastX);
			int dy=Math.abs(y-mLastX);
			if(dx>3||dy>3)
			{
				//注意注意 错误写成moveto
				mPath.lineTo(x, y);
			}
			mLastX=x;
			mLastY=y;
			break;
		case MotionEvent.ACTION_UP:
			new Thread(mRunnable).start();
			break;

		default:
			break;
		}
		//？请求重绘View树
		invalidate();
		return true;
	}
	
	private Runnable mRunnable=new Runnable() {
		
		@Override
		public void run() {
			int width=getWidth();
			int height=getHeight();
			
			//擦除区域面积
			float wipeArea=0;
			
			float totalArea=width*height;
			
			Bitmap bitmap=mBitmap;
			
			int[] mPixels=new int[width*height];
			
			//获取bitmap上所有的像素信息
			bitmap.getPixels(mPixels, 0, width, 0, 0, width, height);
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					//获取每个像素点的索引
					int index=i+j*width;
					if(mPixels[index]==0)
					{
						wipeArea++;
					}
				}
			}
			
			if(wipeArea>0&&totalArea>0)
			{
				//擦除区域百分比
				int percent=(int)(wipeArea*100/totalArea);
				Log.d("d", percent+"");
				if(percent>60)
				{
					//清除图层区域
					mComplete=true;
					//子线程 调用postInvalidate() 注意不能直接调用invalidate()
					postInvalidate();
					
				}
			}
		}
	};
	
	@Override
	protected void onDraw(Canvas canvas) {
		//canvas.drawBitmap(backgroundBitmap, 0, 0, null);
		
		//图片显示在最底层
		canvas.drawText(mText, getWidth()/2-mTextBound.width()/2, getHeight()/2+mTextBound.height()/2, mBackPaint);
		
		if(mComplete)
		{
			//放到主线程中
			if(mListener!=null)
			{
				mListener.complete();
			}
		}
		
		//需要好好理解清除到了阈值后
		if(!mComplete)
		{
			
			drawPath();
			
			canvas.drawBitmap(mBitmap,0,0,null);
		}
		
	}
	
	private void drawPath(){
		mOutterPaint.setStyle(Style.STROKE);
		mOutterPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
		
		//
		mCanvas.drawPath(mPath, mOutterPaint);
	}

	//alt+shift+m 选中代码 生成方法
	// 设置绘制path画笔的一些属性
	private void setupOutPaint() {
		mOutterPaint.setColor(Color.parseColor("#c0c0c0"));
		mOutterPaint.setAntiAlias(true);//设置防止锯齿
		mOutterPaint.setDither(true);//设置防止抖动
		mOutterPaint.setStrokeJoin(Join.ROUND);//设置接洽点类型
		mOutterPaint.setStrokeCap(Cap.ROUND);//
		mOutterPaint.setStyle(Style.FILL);//设置画笔样式
		mOutterPaint.setStrokeWidth(40);
	}
	
	/*
	 * 设置获奖信息的画笔
	 * */
	private void setupBackPaint(){
		mBackPaint.setColor(mTextColor);
		
		mBackPaint.setStyle(Style.FILL);
		
		mBackPaint.setTextSize(mTextSize);
		/*
		 * 获取当前画笔绘制文本的宽和高
		 * */
		mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}

	public void setOnScratchCompleteListener(OnScratchCompleteListener listener) {
		this.mListener = listener;
	}
}
