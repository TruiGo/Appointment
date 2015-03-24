package com.gmdate.appointment.activity;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gmdate.appointment.R;
import com.gmdate.appointment.common.Mylog;
import com.gmdate.appointment.model.Felling;
import com.gmdate.appointment.model.Meeting;
import com.gmdate.appointment.net.HttpResponse;
import com.gmdate.appointment.net.RequestAsyncTask;
import com.haarman.listviewanimations.ArrayAdapter;
import com.haarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.xiaotian.framework.view.JazzyViewPager;
import com.xiaotian.framework.view.JazzyViewPager.TransitionEffect;
import com.xiaotian.framework.view.OutlineContainer;
import com.xiaotian.frameworkxt.android.util.UtilAsyncTask;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name FragmentMainDiscover
 * @description 主页Fragment 发现
 * @date 2015-3-10
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class FragmentMainDiscover extends Fragment implements OnDismissCallback, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

	private int mPagerPosition;
	private int mPagerOffsetPixels;
	private int mActivePosition = 0;
	private AsyncTaskMeetingLoader mAsyncTaskMeetingLoader;
	private AsyncTaskFellingLoader mAsyncTaskFellingLoader;
	//UI
	private View rootView;
	private RadioGroup mRadioGroup;
	private JazzyViewPager mViewPager;
	private PagerAdapter mPagerAdapter;
	private MeetingAdapter mMeetingAdapter;
	private FellingAdapter mFellingAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			// Do first time initialization -- add initial fragment.
			// initView();
		} else {
			// updateView();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.fragment_main_discover, new LinearLayout(getActivity()));
			mViewPager = (JazzyViewPager) rootView.findViewById(R.id.ViewPager);
			rootView.findViewById(R.id.id_0).setOnClickListener(this);
			rootView.findViewById(R.id.id_4).setOnClickListener(this);
			mRadioGroup = (RadioGroup) rootView.findViewById(R.id.id_1);
			((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
			mRadioGroup.setOnCheckedChangeListener(this);
			mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
					mPagerPosition = position;
					mPagerOffsetPixels = positionOffsetPixels;
				}

				@Override
				public void onPageSelected(int position) {
					RadioButton rb = (RadioButton) mRadioGroup.getChildAt(position);
					rb.setChecked(true);
				}
			});
			mPagerAdapter = new MyPagerAdapter(getActivity());
			mViewPager.setTransitionEffect(TransitionEffect.RotateUp);
			mViewPager.setAdapter(mPagerAdapter);
			mViewPager.setPageMargin(30);
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) parent.removeView(rootView);
		return rootView;
	}

	private ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			items.add(i);
		}
		return items;
	}

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			//mGoogleCardsAdapter.remove(position);
		}
	}

	// 主页按钮点击
	@Override
	public void onClick(View view) {
		final int viewId = view.getId();
		if (viewId == R.id.id_0) {
			// 选择地区
			((ActivityMain) getActivity()).startActivity(ActivityAreSelector.class, ActivityMain.REQUEST_CODE_SELECTOR_ARE);
		} else if (viewId == R.id.id_4) {
			// 发表心情随感
			((ActivityMain) getActivity()).startActivity(ActivityPublicFelling.class, ActivityMain.REQUEST_CODE_PUBLIC_FELLING);
		}
	}

	// 分段导航侦听器
	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int id) {
		for (int i = 0; i < radioGroup.getChildCount(); i++) {
			if (radioGroup.getChildAt(i).getId() == id) {
				if (mViewPager.getCurrentItem() != i) {
					mViewPager.setCurrentItem(i, true);
					return;
				}
			}
		}
	}

	/**************************************** Inner Class ****************************************/
	public class MyPagerAdapter extends PagerAdapter {
		private final Context mContext;

		public MyPagerAdapter(Context mContext) {
			this.mContext = mContext;
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			View viewRoot = LayoutInflater.from(mContext).inflate(R.layout.page_listview_cards, new LinearLayout(getActivity()));
			ListView listView = (ListView) viewRoot.findViewById(R.id.ListView);
			View loadingView = viewRoot.findViewById(R.id.id_loading);
			View emptyView = viewRoot.findViewById(R.id.id_empty);
			switch (position) {
			case 0:
				// 聚会加载数据
				if (mMeetingAdapter == null) mMeetingAdapter = new MeetingAdapter(getActivity());
				mAsyncTaskMeetingLoader = new AsyncTaskMeetingLoader(listView, loadingView, emptyView);
				mAsyncTaskMeetingLoader.initaliazing();
				break;
			case 1:
				// 随感加载数据
				if (mFellingAdapter == null) mFellingAdapter = new FellingAdapter(getActivity());
				mAsyncTaskFellingLoader = new AsyncTaskFellingLoader(listView, loadingView, emptyView);
				mAsyncTaskFellingLoader.initaliazing();
				break;
			default:
				break;
			}
			container.addView(viewRoot, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			mViewPager.setObjectForPosition(viewRoot, position);
			return viewRoot;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object obj) {
			container.removeView(mViewPager.findViewFromObject(position));
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}
	}

	private class AsyncTaskMeetingLoader {
		ListView listView;
		View loadingView, emptyView;
		int currentPage;

		public AsyncTaskMeetingLoader(ListView listView, View loadingView, View emptyView) {
			this.listView = listView;
			this.emptyView = emptyView;
			this.loadingView = loadingView;
		}

		public void initaliazing() {
			if (loadingView.getVisibility() != View.VISIBLE) loadingView.setVisibility(View.VISIBLE);
			if (mMeetingAdapter == null) mMeetingAdapter = new MeetingAdapter(getActivity());
			UtilAsyncTask.executeAsyncTask(new AsyncTaskLoador());
		}

		public void loading(String are) {

		}

		class AsyncTaskLoador extends RequestAsyncTask {
			@Override
			protected HttpResponse doInBackground(String... params) {
				Mylog.info("加载约会数据....,");
				return null;
			}

			@Override
			protected void onPostExecute(HttpResponse result) {
				//
				Mylog.info("加载约会数据完成");
				if (listView.getAdapter() == null) {
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					mMeetingAdapter.add(new Meeting());
					SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mMeetingAdapter);
					swingBottomInAnimationAdapter.setAbsListView(listView);
					listView.setAdapter(swingBottomInAnimationAdapter);
				} else {

				}
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
		}
	}

	private class AsyncTaskFellingLoader {
		int currentPage;
		ListView listView;
		View loadingView, emptyView;

		public AsyncTaskFellingLoader(ListView listView, View loadingView, View emptyView) {
			this.listView = listView;
			this.emptyView = emptyView;
			this.loadingView = loadingView;
		}

		public void initaliazing() {
			if (loadingView.getVisibility() != View.VISIBLE) loadingView.setVisibility(View.VISIBLE);
			if (mFellingAdapter == null) mFellingAdapter = new FellingAdapter(getActivity());
			UtilAsyncTask.executeAsyncTask(new AsyncTaskLoador());
		}

		class AsyncTaskLoador extends RequestAsyncTask {
			@Override
			protected HttpResponse doInBackground(String... params) {
				Mylog.info("加载随感数据....,");
				return null;
			}

			@Override
			protected void onPostExecute(HttpResponse result) {
				//
				Mylog.info("加载随感数据完成");
				if (mFellingAdapter == null) mFellingAdapter = new FellingAdapter(getActivity());
				if (listView.getAdapter() == null) {
					SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mFellingAdapter);
					swingBottomInAnimationAdapter.setAbsListView(listView);
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					mFellingAdapter.add(new Felling());
					listView.setAdapter(swingBottomInAnimationAdapter);
				} else {

				}
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
		}
	}

	private class MeetingAdapter extends ArrayAdapter<Meeting> {
		private Context mContext;
		private LruCache<Integer, Bitmap> mMemoryCache;

		public MeetingAdapter(Context context) {
			mContext = context;

			final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

			// Use 1/8th of the available memory for this memory cache.
			final int cacheSize = maxMemory;
			mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
				@Override
				protected int sizeOf(Integer key, Bitmap bitmap) {
					// The cache size will be measured in kilobytes rather than
					// number of items.
					return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
				}
			};
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			View view = convertView;
			if (view == null) {
				view = LayoutInflater.from(mContext).inflate(R.layout.fragment_main_discover_item_meeting, parent, false);

				viewHolder = new ViewHolder();
				//viewHolder.textView = (TextView) view.findViewById(R.id.id_0);
				view.setTag(viewHolder);

				//viewHolder.imageView = (ImageView) view.findViewById(R.id.id_1);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			//viewHolder.textView.setText("This is card " + (getItem(position) + 1));
			//setImageView(viewHolder, position);

			return view;
		}

		private void addBitmapToMemoryCache(int key, Bitmap bitmap) {
			if (getBitmapFromMemCache(key) == null) {
				mMemoryCache.put(key, bitmap);
			}
		}

		private Bitmap getBitmapFromMemCache(int key) {
			return mMemoryCache.get(key);
		}

		private class ViewHolder {
			TextView textView;
			ImageView imageView;
		}
	}

	class FellingAdapter extends ArrayAdapter<Felling> {
		private Context mContext;
		private LruCache<Integer, Bitmap> mMemoryCache;

		public FellingAdapter(Context context) {
			mContext = context;

			final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

			// Use 1/8th of the available memory for this memory cache.
			final int cacheSize = maxMemory;
			mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
				@Override
				protected int sizeOf(Integer key, Bitmap bitmap) {
					// The cache size will be measured in kilobytes rather than
					// number of items.
					return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
				}
			};
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			View view = convertView;
			if (view == null) {
				view = LayoutInflater.from(mContext).inflate(R.layout.fragment_main_discover_item_felling, new LinearLayout(mContext));

				viewHolder = new ViewHolder();
				//				viewHolder.textView = (TextView) view.findViewById(R.id.id_0);
				view.setTag(viewHolder);

				//				viewHolder.imageView = (ImageView) view.findViewById(R.id.id_1);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			//			viewHolder.textView.setText("This is card ");

			return view;
		}

		private void addBitmapToMemoryCache(int key, Bitmap bitmap) {
			if (getBitmapFromMemCache(key) == null) {
				mMemoryCache.put(key, bitmap);
			}
		}

		private Bitmap getBitmapFromMemCache(int key) {
			return mMemoryCache.get(key);
		}

		private class ViewHolder {
			TextView textView;
			ImageView imageView;
		}
	}
}
