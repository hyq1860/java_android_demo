package com.applite.weixindemo;

import java.util.ArrayList;
import java.util.List;

import com.jauker.widget.BadgeView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	
	private FragmentPagerAdapter fAdapter;
	
	private List<Fragment> fragments;
	
	
	private TextView tvChat;
	private TextView tvFriend;
	private TextView tvContact;
	
	private BadgeView badgeView;
	private LinearLayout layoutChat;
	
	//下划线
	private ImageView mTabline;
	
	//屏幕3分之一变量
	private int mScreen1_3;
	//当前页面索引
	private int mCurrentPageIndex;
	
	//Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉默认的actionbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        //shift+alt+m 可以将一段代码抽取成一个方法
        initTabline();
        
        initView();
    }

	private void initTabline() {
		mTabline = (ImageView)findViewById(R.id.id_iv_tabline);
        //获取屏幕对象
        Display display=getWindow().getWindowManager().getDefaultDisplay();
        //有屏幕的一些信息
        DisplayMetrics outMetrics=new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3=outMetrics.widthPixels/3;
        
        //注意在那个包里
        LayoutParams lp= mTabline.getLayoutParams();
        lp.width=mScreen1_3;
        mTabline.setLayoutParams(lp);
	}

    private void initView(){
    	
    	tvChat=(TextView)findViewById(R.id.id_tv_chat);
    	tvFriend=(TextView)findViewById(R.id.id_tv_friend);
    	tvContact=(TextView)findViewById(R.id.id_tv_contact);
    	
    	layoutChat=(LinearLayout)findViewById(R.id.id_layout_chat);
    	badgeView=new BadgeView(MainActivity.this);
    	viewPager=(ViewPager)findViewById(R.id.id_viewpager);
    	
    	fragments=new ArrayList<Fragment>();
    	ChatMainTabFragment tab01=new ChatMainTabFragment();
    	FriendMainTabFragment tab02=new FriendMainTabFragment();
    	ContactMainTabFragment tab03=new ContactMainTabFragment();
    	fragments.add(tab01);
    	fragments.add(tab02);
    	fragments.add(tab03);
    	
    	//适配器
    	//注意构造函数与上面的继承
    	fAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragments.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return fragments.get(arg0);
			}
			
			
		};
		
		viewPager.setAdapter(fAdapter);
		
		//viewpager变化事件
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				//先全部重置为一种颜色
				resetTextView();
				//然后再改要跟内容同步的标题 表明在哪一个屏下面
				switch (position) {
				case 0:
					
					badgeView.setBadgeCount(7);
					if(badgeView!=null)
					{
						layoutChat.removeView(badgeView);
					}
					layoutChat.addView(badgeView);
					
					tvChat.setTextColor(Color.parseColor("#008000"));
					break;
				case 1:
					tvFriend.setTextColor(Color.GREEN);
					break;
				case 2:
					tvContact.setTextColor(Color.GREEN);
					break;
				}
				
				//设置在那个页面
				mCurrentPageIndex = position;
			}
			
			

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
				//position
				//positionOffset
				//positionOffsetPx
				//观察position的值
				Log.e("Tag", position+" "+positionOffset+" "+positionOffsetPx);
				//注意强制转换  leftmargin 默认的viewgroup的LayoutParams没有这个属性
				LinearLayout.LayoutParams  lp= (LinearLayout.LayoutParams)mTabline.getLayoutParams();
				if(mCurrentPageIndex==0 && position==0)//0=>1
				{
					lp.leftMargin=(int)(positionOffset * mScreen1_3 + mCurrentPageIndex*mScreen1_3);
				}
				else if(mCurrentPageIndex==1&&position==0)//1=>0
				{
					lp.leftMargin=(int)(mCurrentPageIndex * mScreen1_3 + (positionOffset-1)*mScreen1_3);
				}
				else if(mCurrentPageIndex==1&&position==1)//1=>2
				{
					lp.leftMargin=(int) (mCurrentPageIndex*mScreen1_3+positionOffset*mScreen1_3);
				}
				else if(mCurrentPageIndex==2&&position==1)//2=>1
				{
					lp.leftMargin=(int)(mCurrentPageIndex*mScreen1_3+(positionOffset-1)*mScreen1_3);
				}
				mTabline.setLayoutParams(lp);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private void resetTextView() {
		tvChat.setTextColor(Color.BLACK);
		tvFriend.setTextColor(Color.BLACK);
		tvContact.setTextColor(Color.BLACK);
		
	}
    
}
