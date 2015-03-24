package com.gmdate.appointment.activity1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.gmdate.appointment.R;
import com.gmdate.appointment.activity.ActivityFullScreamVideo;
import com.gmdate.appointment.activity.ActivityPersonInformation;
import com.gmdate.appointment.activity.BaseActivity;
import com.gmdate.appointment.activity.ImageWorkerFragment;
import com.gmdate.appointment.common.Constant;
import com.gmdate.appointment.common.Mylog;
import com.gmdate.appointment.model.Person;
import com.gmdate.appointment.net.HttpException;
import com.gmdate.appointment.net.HttpResponse;
import com.gmdate.appointment.net.RequestAsyncTask;
import com.gmdate.appointment.net.RequestPerson;
import com.gmdate.appointment.net.VCRDownload;
import com.gmdate.appointment.util.MyUtilImageWorker;
import com.gmdate.appointment.view.ViewEmptyRelativeLayout;
import com.gmdate.appointment.view.ViewLoadingRelativeLayout;
import com.xiaotian.framework.view.FlipImageView;
import com.xiaotian.framework.view.JazzyViewPager;
import com.xiaotian.framework.view.JazzyViewPager.TransitionEffect;
import com.xiaotian.framework.view.ViewToggleButton;
import com.xiaotian.frameworkxt.android.util.UtilAsyncTask;
import com.xiaotian.frameworkxt.android.util.UtilUriMatcher;

/**
 * @文件名称 ActivityYouXuan.java
 * @创建日期 2015-3-20 下午12:01:34
 * @作者 XiaoTian(Create by Administrator)
 * @联系作者 Email: gtrstudio@qq.com
 * @Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ActivityYouXuan extends BaseActivity implements OnClickListener {
	private JazzyViewPager mViewPager;
	private int mPagerPosition;
	private int mPagerOffsetPixels;
	private ArrayList<Person> listPerson = new ArrayList<Person>();
	private AsyncTaskLoader mAsyncTaskLoader;
	private PagerAdapter mPagerAdapter;
	ViewEmptyRelativeLayout viewEmpty;
	ViewLoadingRelativeLayout viewLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializingView();
		initializingData();
	}

	@Override
	protected void initializingView() {
		setContentView(R.layout.activity_youxuan);
		mViewPager = (JazzyViewPager) findViewById(R.id.ViewPager);
		viewLoading = (ViewLoadingRelativeLayout) findViewById(R.id.id_loading);
		viewEmpty = (ViewEmptyRelativeLayout) findViewById(R.id.id_empty);
		findViewById(R.id.id_3).setOnClickListener(this);
		findViewById(R.id.id_4).setOnClickListener(this);
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
		mPagerAdapter = new PagerAdapter(this, listPerson);
		mViewPager.setTransitionEffect(TransitionEffect.Standard);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setPageMargin(30);
	}

	@Override
	protected void initializingData() {
		mAsyncTaskLoader = new AsyncTaskLoader(viewLoading, viewEmpty);
		mAsyncTaskLoader.asyncLoading(null, null);
	}

	@Override
	public void toolbarBackOnclick(View view) {
		finish();
	}

	@Override
	public void toolbarForwardOnclick(View view) {
		super.toolbarForwardOnclick(view);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.id_3) {
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
				((BaseActivity) getActivity()).startActivity(ActivityPersonInformation.class);
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
