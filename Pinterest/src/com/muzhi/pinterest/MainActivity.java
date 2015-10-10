package com.muzhi.pinterest;

import java.util.ArrayList;


import com.muzhi.pinterest.adapter.MyFragmentPagerAdapter;
import com.muzhi.widget.views.PagerSlidingTabStrip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

	
	private MyFragmentPagerAdapter fragmentAdapter;
	private ArrayList<Fragment> fragments;
	
	
	private PagerSlidingTabStrip mpager_tab_strip;
	private ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mpager_tab_strip=(PagerSlidingTabStrip)findViewById(R.id.pager_tab_strip);
		
		mViewPager=(ViewPager)findViewById(R.id.pager);

		fragments = new ArrayList<Fragment>();	
		
		FragmentFeeds mHomeFeedsFragment1=FragmentFeeds.newInstance(1);
		FragmentFeeds2 mHomeFeedsFragment2=FragmentFeeds2.newInstance(1);
		
		fragments.add(mHomeFeedsFragment1);
		fragments.add(mHomeFeedsFragment2);
		
		
		fragmentAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,new String[]{"最新","热门"});
		mViewPager.setAdapter(fragmentAdapter);		
		mpager_tab_strip.setViewPager(mViewPager);
		mViewPager.setOffscreenPageLimit(1);
		mpager_tab_strip.setSelectedTextColor(Color.parseColor("#ff8500"));	//选中文字
		
	}
	
	

	
}
