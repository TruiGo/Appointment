package com.gmdate.appointment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabWidget;

import com.gmdate.appointment.R;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ActivityMain
 * @description 主页面
 * @date 2015-3-9
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ActivityMain extends BaseActivity {
	public static final String TAB_TAG_TA = "TA";
	public static final String TAB_TAG_MESSAGE = "MESSAGE";
	public static final String TAB_TAG_DISCOVER = "DISCOVER";
	public static final String TAB_TAG_INFORMATION = "INFORMATION";
	public static final int REQUEST_CODE_SELECTOR_ARE = 0X001;
	public static final int REQUEST_CODE_PUBLIC_FELLING = 0X002;
	// 1.Tab
	// 2.第一个Page ScrollView + Scroll Menu
	private FragmentTabHost mTabHost;
	private RadioGroup mRadioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializingView();
		initializingData();
	}

	@Override
	protected void initializingView() {
		setContentView(R.layout.activity_main);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mRadioGroup = (RadioGroup) findViewById(R.id.id_0);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_TA).setIndicator(TAB_TAG_TA), FragmentMainTa.class, null);
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_DISCOVER).setIndicator(TAB_TAG_DISCOVER), FragmentMainDiscover.class, null);
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_MESSAGE).setIndicator(TAB_TAG_MESSAGE), FragmentMainMessage.class, null);
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TAG_INFORMATION).setIndicator(TAB_TAG_INFORMATION), FragmentMainInformation.class, null);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String currentTabTag = mTabHost.getCurrentTabTag();
				if (currentTabTag == TAB_TAG_TA) {
					FragmentMainTa fragment = (FragmentMainTa) getSupportFragmentManager().findFragmentByTag(TAB_TAG_TA);
					if (fragment.isOpenMenu()) fragment.onCloseMenu();
				}
				mTabHost.setCurrentTab(checkedId - R.id.id_1);
			}
		});
		//
		//		View tab1 = findViewById(R.id.id_1);
		//		BadgeView badge4 = new BadgeView(this, tab1);
		//		badge4.setText("123");
		//		badge4.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		//		badge4.setBadgeMargin(15, 10);
		//		badge4.setBadgeBackgroundColor(Color.parseColor("#A4C639"));
		//		badge4.show();
	}

	@Override
	protected void initializingData() {
		super.initializingData();
	}

	@Override
	public void onBackPressed() {
		String currentTabTag = mTabHost.getCurrentTabTag();
		if (currentTabTag == TAB_TAG_TA) {
			FragmentMainTa fragment = (FragmentMainTa) getSupportFragmentManager().findFragmentByTag(TAB_TAG_TA);
			if (fragment.isOpenMenu()) {
				fragment.onCloseMenu();
				return;
			}
		}
		super.onBackPressed();
	}

	/**************************************** Inner Class ****************************************/
	public static class MyTabWidget extends TabWidget {

		public MyTabWidget(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		public void addView(View child) {
			child.setVisibility(View.GONE);
			super.setStripEnabled(false); // Strip Line
			super.addView(child);
		}
	}
}
