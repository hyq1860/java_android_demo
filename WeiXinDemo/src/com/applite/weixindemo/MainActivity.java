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

import android.view.Window;
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
	
	
	//Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉默认的actionbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initView();
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
			}
			
			

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
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
