package com.gmdate.appointment.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gmdate.appointment.R;
import com.viewpagerindicator.PageIndicator;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name WellcomActivity
 * @description 欢迎引导页面
 * @date 2015-3-9
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ActivityWelcome extends BaseActivity {
	// UI
	SectionsPagerAdapter mSectionsPagerAdapter;
	PageIndicator mIndicator;
	ViewPager mViewPager;
	//
	int[] viewImageRes = new int[] { R.drawable.welcome_1,
			R.drawable.welcome_2, R.drawable.welcome_3 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wellcom);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.ViewPager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mIndicator = (PageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mViewPager);
		mIndicator.setCurrentItem(0);
	}

	// 登录
	public void onClickLogin(View view) {
		startActivity(ActivityLogin.class);
		finish();
	}

	// 注册
	public void onClickRegister(View view) {
		startActivity(ActivityRegister.class);
		finish();
	}

	/*********************************** Inner Class ***********************************/
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			PlaceholderFragment fragment = PlaceholderFragment
					.newInstance(viewImageRes[position]);
			fragment.setShowButton(position == viewImageRes.length - 1);
			return fragment;
		}

		@Override
		public int getCount() {
			return viewImageRes.length;
		}
	}

	public static class PlaceholderFragment extends Fragment {
		private static final String ARG_IMAGE_RES = "image_res";
		ImageView mImageView;
		boolean showButton;
		View mView;

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_IMAGE_RES, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_wellcom,
					container, false);
			mImageView = (ImageView) rootView.findViewById(R.id.id_0);
			mImageView.setImageResource(getArguments().getInt(ARG_IMAGE_RES));
			return rootView;
		}

		public void setShowButton(boolean showButton) {
			this.showButton = showButton;
		}
	}

}
