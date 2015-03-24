package com.gmdate.appointment.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import net.simonvt.menudrawer.SlidingDrawer;

import org.json.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.VideoView;

import com.gmdate.appointment.R;
import com.gmdate.appointment.common.Constant;
import com.gmdate.appointment.common.Mylog;
import com.gmdate.appointment.model.Are;
import com.gmdate.appointment.model.Person;
import com.gmdate.appointment.net.HttpException;
import com.gmdate.appointment.net.HttpResponse;
import com.gmdate.appointment.net.RequestAsyncTask;
import com.gmdate.appointment.net.RequestCommon;
import com.gmdate.appointment.net.RequestPerson;
import com.gmdate.appointment.net.VCRDownload;
import com.gmdate.appointment.util.MyUtilImageWorker;
import com.gmdate.appointment.view.ViewEmptyRelativeLayout;
import com.gmdate.appointment.view.ViewLoadingRelativeLayout;
import com.xiaotian.framework.view.FlipImageView;
import com.xiaotian.framework.view.JazzyViewPager;
import com.xiaotian.framework.view.JazzyViewPager.TransitionEffect;
import com.xiaotian.framework.view.ViewToggleButton;
import com.xiaotian.frameworkxt.android.common.MyOnClickListener;
import com.xiaotian.frameworkxt.android.util.UtilAsyncTask;
import com.xiaotian.frameworkxt.android.util.UtilUriMatcher;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name FragmentMainTa
 * @description 主页Fragment TA
 * @date 2015-3-10
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class FragmentMainTa extends Fragment implements OnClickListener {
	private int mPagerPosition;
	private int mPagerOffsetPixels;
	private String[] sexArray;
	private AsyncTaskLoader mAsyncTaskLoader;
	private ArrayList<Person> listPerson = new ArrayList<Person>();
	private MyUtilImageWorker mImageWorker;
	// UI
	private View rootView;
	protected MenuDrawer mMenuDrawer;
	protected ListView mList;
	private PagerAdapter mPagerAdapter;
	private JazzyViewPager mViewPager;
	private PopupWindow selectorSexWindow, selectorAreWindow;
	private BaseAdapter sexAdapter, areAdapter;
	private TextView textSex, textAre;
	ViewEmptyRelativeLayout viewEmpty;
	ViewLoadingRelativeLayout viewLoading;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sexArray = getResources().getStringArray(R.array.string_array_sex);
		if (savedInstanceState == null) {
			// Do first time initialization -- add initial fragment.
			// initView();
		} else {
			// updateView();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mMenuDrawer == null) {
			mMenuDrawer = new SlidingDrawer(getActivity());
			// 设置左边展开View
			mMenuDrawer.setMenuView(inflater.inflate(R.layout.fragment_main_ta_menu, new LinearLayout(getActivity())));
			// 左边展开页点击
			View.OnClickListener onClickMenu = new OnClickListener() {
				@Override
				public void onClick(View v) {
					onMenuClick(v);
				}
			};
			mMenuDrawer.getMenuView().findViewById(R.id.id_0).setOnClickListener(onClickMenu);
			mMenuDrawer.getMenuView().findViewById(R.id.id_1).setOnClickListener(onClickMenu);
			mMenuDrawer.getMenuView().findViewById(R.id.id_2).setOnClickListener(onClickMenu);
			textSex = (TextView) mMenuDrawer.getMenuView().findViewById(R.id.id_0);
			textAre = (TextView) mMenuDrawer.getMenuView().findViewById(R.id.id_1);
			textSex.setText(sexArray[0]);
			// 设置内容页
			mMenuDrawer.setContentView(R.layout.fragment_main_ta_container);
			// 内容页点击
			mMenuDrawer.getContentContainer().findViewById(R.id.view_model_toptoolbar_button_left_xiaotian).setOnClickListener(this);
			mMenuDrawer.getContentContainer().findViewById(R.id.view_model_toptoolbar_button_right_xiaotian).setOnClickListener(this);
			mMenuDrawer.getContentContainer().findViewById(R.id.id_3).setOnClickListener(this);
			mMenuDrawer.getContentContainer().findViewById(R.id.id_4).setOnClickListener(this);
			viewLoading = (ViewLoadingRelativeLayout) mMenuDrawer.getContentContainer().findViewById(R.id.id_loading);
			viewEmpty = (ViewEmptyRelativeLayout) mMenuDrawer.getContentContainer().findViewById(R.id.id_empty);
			mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
			mMenuDrawer.setSaveEnabled(false);
			mMenuDrawer.setOnInterceptMoveEventListener(new MenuDrawer.OnInterceptMoveEventListener() {
				@Override
				public boolean isViewDraggable(View v, int dx, int x, int y) {
					if (v == mViewPager) {
						return !(mPagerPosition == 0 && mPagerOffsetPixels == 0) || dx < 0;
					}
					return false;
				}
			});
			mViewPager = (JazzyViewPager) mMenuDrawer.findViewById(R.id.ViewPager);
			mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
					mPagerAdapter.releasePlayer();
					mPagerPosition = position;
					mPagerOffsetPixels = positionOffsetPixels;
				}

				@Override
				public void onPageSelected(int position) {
					if (listPerson.size() - position < 5) mAsyncTaskLoader.asyncLoadingNextPage();
				}
			});
			mPagerAdapter = new PagerAdapter(getActivity(), listPerson);
			mViewPager.setTransitionEffect(TransitionEffect.Tablet);
			mViewPager.setAdapter(mPagerAdapter);
			mViewPager.setPageMargin(30);
			//
			mAsyncTaskLoader = new AsyncTaskLoader(viewLoading, viewEmpty);
			mAsyncTaskLoader.asyncLoading(null, null);
		}
		ViewGroup parent = (ViewGroup) mMenuDrawer.getParent();
		if (parent != null) parent.removeView(mMenuDrawer);
		return mMenuDrawer;
	}

	protected int getDragMode() {
		return MenuDrawer.MENU_DRAG_CONTENT;
	}

	protected Position getDrawerPosition() {
		return Position.LEFT;
	}

	public void onCloseMenu() {
		final int drawerState = mMenuDrawer.getDrawerState();
		if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
			mMenuDrawer.closeMenu();
			return;
		}
	}

	public boolean isOpenMenu() {
		int drawerState = mMenuDrawer.getDrawerState();
		return drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING;
	}

	// 内容页点击
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.view_model_toptoolbar_button_left_xiaotian) {
			// 左上角
			if (isOpenMenu()) {
				onCloseMenu();
			} else {
				mMenuDrawer.openMenu(true);
			}
		} else if (view.getId() == R.id.view_model_toptoolbar_button_right_xiaotian) {
			// 右上角
			if (isOpenMenu()) onCloseMenu();
			Intent intent = new Intent(getActivity(), ActivitySearch.class);
			startActivity(intent);
		} else if (view.getId() == R.id.id_3) {
			// 上一页
			if (mViewPager.getCurrentItem() > 0) {
				mViewPager.setCurrentItem(mPagerPosition - 1, true);
			}
		} else if (view.getId() == R.id.id_4) {
			// 下一页
			if (mViewPager.getCurrentItem() < mPagerAdapter.getCount() - 1) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
			}
		}
	}

	// 左边菜单也点击
	public void onMenuClick(View view) {
		if (view.getId() == R.id.id_0) {
			// 性别
			onClickSelectSex(view);
		} else if (view.getId() == R.id.id_1) {
			// 区域
			onClickSelectAre(view);
		} else if (view.getId() == R.id.id_2) {
			// 搜索
			if (isOpenMenu()) onCloseMenu();
		}
	}

	// 选择性别
	public void onClickSelectSex(final View view) {
		if (selectorSexWindow == null) {
			ListView lv = new ListView(getActivity());
			lv.setId(R.id.ListView);
			lv.setDrawingCacheEnabled(true);
			lv.setCacheColorHint(getResources().getColor(android.R.color.transparent));
			selectorSexWindow = new PopupWindow(lv, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
			selectorSexWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_whitecontent_grayborder));
			lv.setAdapter(sexAdapter = new SexAdapter());
			lv.setDivider(new ColorDrawable(getResources().getColor(R.color.color_line_gray)));
			lv.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.dimen_1));
			selectorSexWindow.setFocusable(true);
		}
		if (selectorSexWindow.isShowing()) {
			selectorSexWindow.dismiss();
		} else {
			selectorSexWindow.showAsDropDown(view);
		}
		sexAdapter.notifyDataSetChanged();
	}

	// 选择区域
	public void onClickSelectAre(final View view) {
		if (selectorAreWindow == null) {
			View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_listview_asyncloading, new LinearLayout(getActivity()));
			ListView lv = (ListView) rootView.findViewById(R.id.ListView);
			lv.setDrawingCacheEnabled(true);
			lv.setCacheColorHint(getResources().getColor(android.R.color.transparent));
			selectorAreWindow = new PopupWindow(rootView, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
			selectorAreWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_whitecontent_grayborder));
			lv.setAdapter(areAdapter = new AreAdapter(rootView.findViewById(R.id.id_loading)));
			selectorAreWindow.setFocusable(true);
		}
		if (selectorAreWindow.isShowing()) {
			selectorAreWindow.dismiss();
		} else {
			selectorAreWindow.showAsDropDown(view);
		}
		((AreAdapter) areAdapter).loadingData();
		areAdapter.notifyDataSetChanged();
	}

	public MyUtilImageWorker getImageWorker() {
		if (mImageWorker != null) return mImageWorker;
		mImageWorker = ImageWorkerFragment.getInstance(getActivity(), getFragmentManager()).getImageWorker();
		mImageWorker.setImageFadeIn(true);
		return mImageWorker;
	}

	/**************************************** Inner Class ****************************************/
	public class SexAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int padding10 = getResources().getDimensionPixelSize(R.dimen.dimen_10);
			TextView tv = new TextView(getActivity(), null, R.style.style_text_label);
			tv.setTextColor(getResources().getColorStateList(R.drawable.selector_color_press_black_white));
			tv.setBackgroundResource(R.drawable.selector_color_blue_listselector);
			tv.setGravity(Gravity.CENTER);
			tv.setPadding(padding10, padding10, padding10, padding10);
			tv.setText(sexArray[position]);
			tv.setOnClickListener(new MyOnClickListener<Integer>(position) {
				@Override
				public void onClick(View v) {
					textSex.setText(sexArray[getInitParams(0)]);
					selectorSexWindow.dismiss();
				}
			});
			return tv;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return sexArray[position];
		}

		@Override
		public int getCount() {
			return sexArray.length;
		}
	}

	public class AreAdapter extends BaseAdapter {
		ArrayList<Are> ares = new ArrayList<Are>();
		boolean successLoadedAre;
		View loadingView;

		public AreAdapter(View loadingView) {
			this.loadingView = loadingView;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv;
			if (convertView == null) {
				int padding10 = getResources().getDimensionPixelSize(R.dimen.dimen_10);
				tv = new TextView(getActivity(), null, R.style.style_text_label);
				tv.setTextColor(getResources().getColorStateList(R.drawable.selector_color_press_black_white));
				tv.setBackgroundResource(R.drawable.selector_color_blue_listselector);
				tv.setGravity(Gravity.CENTER);
				tv.setPadding(padding10, padding10, padding10, padding10);
				tv.setOnClickListener(new MyOnClickListener<Integer>(position) {
					@Override
					public void onClick(View v) {
						textAre.setText(((TextView) v).getText());
						selectorAreWindow.dismiss();
					}
				});
			} else {
				tv = (TextView) convertView;
			}
			Are are = ares.get(position);
			tv.setText(are.getName());
			return tv;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return ares.get(position);
		}

		@Override
		public int getCount() {
			return ares.size();
		}

		public void loadingData() {
			if (successLoadedAre) return;
			UtilAsyncTask.executeAsyncTask(new RequestAsyncTask() {
				@Override
				protected HttpResponse doInBackground(String... params) {
					HttpResponse response;
					try {
						response = new RequestCommon().getHotAre();
					} catch (HttpException e) {
						e.printStackTrace();
						return HttpResponse.createException(e);
					}
					if (response.isSuccess()) {
						JSONArray dataList = response.getJsonDataList();
						Mylog.info(dataList);
					}
					return null;
				}

				@Override
				protected void onPostExecute(HttpResponse result) {
					ares.add(new Are("不限"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					ares.add(new Are("广东茂名"));
					if (loadingView.getVisibility() != View.GONE) loadingView.setVisibility(View.GONE);
					notifyDataSetChanged();
					successLoadedAre = true;
				}
			});
		}
	}

	public class PagerAdapter extends FragmentPagerAdapter {
		private HashMap<Integer, Object> mObjs = new LinkedHashMap<Integer, Object>();
		private final Context mContext;
		private final ArrayList<Person> listPerson;

		public PagerAdapter(FragmentActivity activity, ArrayList<Person> listPerson) {
			super(activity.getSupportFragmentManager());
			this.listPerson = listPerson;
			mContext = activity;
		}

		@Override
		public int getCount() {
			return listPerson.size();
		}

		@Override
		public Fragment getItem(int position) {
			Bundle mArgs = new Bundle();
			mArgs.putSerializable(Constant.EXTRA_PARAMS, listPerson.get(position));
			Fragment fragment = Fragment.instantiate(mContext, PersonFragment.class.getName(), mArgs);
			mObjs.put(position, fragment);
			mViewPager.setObjectForPosition(fragment, position);
			return fragment;
		}

		public void releasePlayer() {
			Object o = mObjs.get(Integer.valueOf(mViewPager.getCurrentItem()));
			if (o != null) {
				((PersonFragment) o).resetView();
			}
		}
	}

	public static class PersonFragment extends Fragment implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, View.OnClickListener {
		DisplayMetrics mDisplayMetrics;
		MyUtilImageWorker mImageWorker;
		VideoView mVideoView;
		ImageView imageView;
		View viewZan;
		ImageButton ibPlay;
		boolean isVideoPlaying;;
		FrameLayout mVideoHolder;
		ViewToggleButton[] stars = new ViewToggleButton[5];
		TextView textName, textLevel, textAge, textStature, textEducation, textAre;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_ta_person, new LinearLayout(getActivity()));
			Person person = (Person) getArguments().getSerializable(Constant.EXTRA_PARAMS);
			// 
			imageView = (ImageView) rootView.findViewById(R.id.ImageView);
			mVideoView = (VideoView) rootView.findViewById(R.id.VideoView);
			viewZan = rootView.findViewById(R.id.id_0);
			textName = (TextView) rootView.findViewById(R.id.id_2);
			textLevel = (TextView) rootView.findViewById(R.id.id_3);
			stars[0] = (ViewToggleButton) rootView.findViewById(R.id.id_star_0);
			stars[1] = (ViewToggleButton) rootView.findViewById(R.id.id_star_1);
			stars[2] = (ViewToggleButton) rootView.findViewById(R.id.id_star_2);
			stars[3] = (ViewToggleButton) rootView.findViewById(R.id.id_star_3);
			stars[4] = (ViewToggleButton) rootView.findViewById(R.id.id_star_4);
			ibPlay = (ImageButton) rootView.findViewById(R.id.id_4);
			textAge = (TextView) rootView.findViewById(R.id.id_5);
			textStature = (TextView) rootView.findViewById(R.id.id_6);
			textEducation = (TextView) rootView.findViewById(R.id.id_7);
			textAre = (TextView) rootView.findViewById(R.id.id_8);
			//
			imageView.setOnClickListener(this);
			viewZan.setOnClickListener(this);
			ibPlay.setOnClickListener(this);
			if (person.getAttention() == Person.ATTENTION_FOCUS) {
				FlipImageView fiv = (FlipImageView) viewZan.findViewById(R.id.FlipImageView);
				fiv.setFlipped(true);
			}
			viewZan.setTag(R.id.id_0, person);
			textName.setText(person.getName());
			switch (person.getVipLevel()) {
			case 1:
				textLevel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.vip_level_0, 0, 0, 0);
				break;
			case 2:
				textLevel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.vip_level_1, 0, 0, 0);
				break;
			case 3:
				textLevel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.vip_level_2, 0, 0, 0);
				break;
			default:
				break;
			}
			switch (person.getStarLevel()) {
			case 5:
				stars[4].setChecked(true);
			case 4:
				stars[3].setChecked(true);
			case 3:
				stars[2].setChecked(true);
			case 2:
				stars[1].setChecked(true);
			case 1:
				stars[0].setChecked(true);
			}
			textLevel.setText(person.getVipLevelName());
			if (verifyHttp(person.getPathVCR())) {
				ibPlay.setImageResource(R.drawable.bt_play);
			} else {
				ibPlay.setImageResource(R.drawable.bt_pause);
			}
			ibPlay.setTag(R.id.id_0, person.getPathVCR());
			textAge.setText(person.getAge() + "岁");
			textStature.setText(person.getStature() + "cm");
			textAre.setText(person.getAre());
			textEducation.setText(person.getEducation());
			getImageWorker().loadImage(person.getPathPicture(), imageView);
			return rootView;
		}

		public MyUtilImageWorker getImageWorker() {
			if (mImageWorker != null) return mImageWorker;
			mImageWorker = ImageWorkerFragment.getInstance(getActivity(), getFragmentManager()).getImageWorker();
			mImageWorker.setImageFadeIn(true);
			return mImageWorker;
		}

		public void playVCR(String url) {
			isVideoPlaying = true;
			if (imageView.getVisibility() != View.GONE) imageView.setVisibility(View.GONE);
			mDisplayMetrics = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
			int height = mDisplayMetrics.heightPixels;
			int width = mDisplayMetrics.widthPixels;
			mVideoView.setMinimumWidth(width);
			mVideoView.setMinimumHeight(height);
			//only this showed the controller for me!!
			mVideoView.setOnPreparedListener(this);
			//finish after playing
			mVideoView.setOnCompletionListener(this);
			UtilAsyncTask.executeAsyncTask(new AsyncTask<String, Integer, String>() {
				@Override
				protected String doInBackground(String... params) {
					VCRDownload vcr = new VCRDownload(getActivity());
					try {
						return vcr.downloadFile(params[0]);
					} catch (HttpException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(String result) {
					if (isVideoPlaying && result != null && mVideoView != null) {
						Mylog.info("开始播放视频");
						if (mVideoView.getVisibility() != View.VISIBLE) mVideoView.setVisibility(View.VISIBLE);
						mVideoView.setVideoPath(UtilUriMatcher.ResourcesScheme.FILE.wrap(result));
						//mVideoView.setZOrderOnTop(true);
						mVideoView.requestFocus();
						mVideoView.start();
					}
				}
			}, url);
		}

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.id_0) {
				// 点击关注TA/赞
				Person p = (Person) v.getTag(R.id.id_0);
				if (p.getAttention() == Person.ATTENTION_UNKNOW) {
					p.setAttention(Person.ATTENTION_FOCUS);
					FlipImageView fiv = (FlipImageView) v.findViewById(R.id.FlipImageView);
					fiv.toggleFlip();
				}
			} else if (v.getId() == R.id.id_4) {
				// 播放视频
				String vcrPath = (String) v.getTag(R.id.id_0);
				if (verifyHttp(vcrPath)) {
					if (!isVideoPlaying) {
						// 播放
						//ibPlay.setImageResource(R.drawable.bt_pause);
						//playVCR(vcrPath); // 嵌套层播放不能刷新/必须置顶
						Bundle extras = new Bundle();
						extras.putString(ActivityFullScreamVideo.EXTRA_VRC, vcrPath);
						//((ActivityMain) getActivity()).startActivity(ActivityFullScreamVideo.class, extras);
					} else {
						// 暂停
						//ibPlay.setImageResource(R.drawable.bt_play);
						//pauseVCR();
					}
				} else {
					// 推荐录制视频

				}
			} else if (v.getId() == R.id.ImageView) {
				// 查看个人信息
				((ActivityMain) getActivity()).startActivity(ActivityPersonInformation.class);
			}
		}

		@Override
		public void onPrepared(MediaPlayer mp) {
			// 准备好播放视频源
		}

		@Override
		public void onCompletion(MediaPlayer mediaPlayer) {
			ibPlay.setImageResource(R.drawable.bt_play);
			resetView();
		}

		public void pauseVCR() {
			if (isVideoPlaying && mVideoView != null) {
				if (mVideoView.isPlaying() && mVideoView.canPause()) mVideoView.pause();
			}
			isVideoPlaying = false;
		}

		public void resetView() {
			if (isVideoPlaying && mVideoView != null) {
				if (mVideoView.isPlaying() && mVideoView.canPause()) {
					mVideoView.pause();
				}
				ibPlay.setImageResource(R.drawable.bt_play);
			}
			if (mVideoView != null && mVideoView.getVisibility() != View.GONE) mVideoView.setVisibility(View.GONE);
			if (imageView.getVisibility() != View.VISIBLE) imageView.setVisibility(View.VISIBLE);
			isVideoPlaying = false;
		}

		public boolean verifyHttp(String url) {
			switch (UtilUriMatcher.ResourcesScheme.ofUri(url)) {
			case HTTP:
			case HTTPS:
				return true;
			default:
				return false;
			}
		}

		@Override
		public void onPause() {
			resetView();
			super.onPause();
		}
	}

	public class AsyncTaskLoader {
		boolean isRequest;
		String sex, are;
		int currentPage;
		View loadingView, emptyView;

		public AsyncTaskLoader(View loadingView, View emptyView) {
			currentPage = 0;
			this.emptyView = emptyView;
			this.loadingView = loadingView;
		}

		public void asyncLoading(String sex, String are) {
			if (isRequest) return;
			showLoadingView();
			currentPage = 0;
			UtilAsyncTask.executeAsyncTask(new AsyncTaskPerson());
		}

		public void asyncLoadingNextPage() {
			if (isRequest) return;
			currentPage += 1;
			UtilAsyncTask.executeAsyncTask(new AsyncTaskPerson());
		}

		public void showLoadingView() {
			if (loadingView.getVisibility() != View.VISIBLE) loadingView.setVisibility(View.VISIBLE);
		}

		class AsyncTaskPerson extends RequestAsyncTask {
			RequestPerson mRequestPerson;

			@Override
			protected void onPostExecute(HttpResponse result) {
				if (viewLoading.getVisibility() != View.GONE) viewLoading.setVisibility(View.GONE);
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				listPerson.add(getPerson());
				mPagerAdapter.notifyDataSetChanged();
				isRequest = false;
			}

			@Override
			protected void onPreExecute() {
				isRequest = true;
			}
		}
	}

	public static class MyFlipImageView extends FlipImageView {
		boolean clickable;

		public MyFlipImageView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public void setClickAble(boolean clickable) {
			this.clickable = clickable;
		}

		@Override
		public void onClick(View v) {
			if (!clickable) return;
			super.onClick(v);
		}
	}

	Person getPerson() {
		Person p = new Person();
		p.setPathPicture("http://pic.cnr.cn/pic/nativepic/20150312/W020150312632696604498.jpg");
		p.setPathVCR("http://www.gmdate.com.cn/upload/demo_3.mp4");
		p.setAttention(Person.ATTENTION_FOCUS);
		p.setName("离细细");
		p.setVipLevel(2);
		p.setVipLevelName("总经理");
		p.setStarLevel(3);
		p.setAge(22);
		p.setStature(165);
		p.setAre("广东");
		p.setEducation("本科");
		return p;
	}
}
