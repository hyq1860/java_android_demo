package com.applite.androidallinone;

import android.R.anim;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AtyEffectiveNavigation extends FragmentActivity {
	
	CollectionPagerAdapter mCollectionPagerAdapter;
	
	ViewPager mViewPager;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		
		setContentView(R.layout.aty_effective_navigation);
		
		mCollectionPagerAdapter=new CollectionPagerAdapter(getSupportFragmentManager());
		
		final ActionBar actionBar=getActionBar();
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		mViewPager=(ViewPager)findViewById(R.id.pager);
		mViewPager.setAdapter(mCollectionPagerAdapter);
		
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, AtyAppSection.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public class CollectionPagerAdapter extends FragmentStatePagerAdapter
	{

		public CollectionPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int i) {
			// TODO Auto-generated method stub
			Fragment fragment=new DemoObjectFragment();
			Bundle args=new Bundle();
			args.putInt(DemoObjectFragment.ARG_OBJECT, i+1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 100;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return "Object"+position+1;
		}
		
	}
	
	public static class DemoObjectFragment extends Fragment
	{
		public static final String ARG_OBJECT="object";
		
		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_collection_object, container,false);
			Bundle args=getArguments();
			((TextView)rootView.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
			return rootView;
		}
	}
}
