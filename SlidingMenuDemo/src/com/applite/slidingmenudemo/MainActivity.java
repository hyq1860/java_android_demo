package com.applite.slidingmenudemo;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

	private SlidingMenu slidingMenu;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        //
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        //全屏可以拖拽
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //附加到
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    	switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			slidingMenu.toggle(true);
			break;

		default:
			break;
		}

    	return super.onKeyDown(keyCode, event);
    }
}
