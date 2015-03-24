package com.gmdate.appointment.activity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.VideoView;

import com.gmdate.appointment.R;
import com.gmdate.appointment.common.Mylog;
import com.gmdate.appointment.net.HttpException;
import com.gmdate.appointment.net.VCRDownload;
import com.xiaotian.frameworkxt.android.util.UtilAsyncTask;
import com.xiaotian.frameworkxt.android.util.UtilUriMatcher;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ActivityFullScreamVideo
 * @description 全屏播放视频
 * @date 2015-3-13
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ActivityFullScreamVideo extends BaseActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
	public static final String EXTRA_VRC = "com.gmdate.appointment.activity.VCR";
	VideoView mVideoView;
	View viewLoading, viewEmpty;
	boolean isVideoPlaying;
	DisplayMetrics mDisplayMetrics;
	String videoPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		videoPath = getIntent().getExtras().getString(EXTRA_VRC);
		initializingView();
		initializingData();
	}

	@Override
	protected void initializingView() {
		setContentView(R.layout.activity_fullscream_video);
		viewLoading = findViewById(R.id.id_loading);
		viewEmpty = findViewById(R.id.id_empty);
		mVideoView = (VideoView) findViewById(R.id.VideoView);
		mVideoView.setOnTouchListener(new OnTouchListener() {
			boolean mHasPerformedLongPress;
			PerformClick mPerformClick;
			UnsetPressedState mUnsetPressedState;
			CheckForLongPress mPendingCheckForLongPress;
			View cv;

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				cv = view;
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					boolean focusTaken = false;
					if (cv.isFocusable() && cv.isFocusableInTouchMode() && !cv.isFocused()) {
						focusTaken = cv.requestFocus();
					}
					if (!mHasPerformedLongPress) {
						// This is a tap, so remove the longpress check
						removeLongPressCallback();
						if (!focusTaken) {
							// Use a Runnable and post this rather than calling
							// performClick directly. This lets other visual state
							// of the view update before click actions start.
							if (mPerformClick == null) {
								mPerformClick = new PerformClick(cv);
							}
							if (!cv.post(mPerformClick)) {
								onClick(cv);
							}
						}
					} else {}
					if (mUnsetPressedState == null) {
						mUnsetPressedState = new UnsetPressedState();
					}
					if (!cv.post(mUnsetPressedState)) {
						// If the post failed, unpress right now
						mUnsetPressedState.run();
					}
					break;
				case MotionEvent.ACTION_DOWN:
					mHasPerformedLongPress = false;
					cv.setPressed(true);
					checkForLongClick(0);
					break;
				}
				return true;
			}

			private void checkForLongClick(int delayOffset) {
				mHasPerformedLongPress = false;
				if (mPendingCheckForLongPress == null) {
					mPendingCheckForLongPress = new CheckForLongPress();
				}
				cv.postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout() - delayOffset);
			}

			private void removeLongPressCallback() {
				if (mPendingCheckForLongPress != null) {
					cv.removeCallbacks(mPendingCheckForLongPress);
				}
			}

			final class PerformClick implements Runnable {
				View view;

				public PerformClick(View view) {
					this.view = view;
				}

				public void run() {
					onClick(view);
				}
			}

			class CheckForLongPress implements Runnable {
				public void run() {
					if (cv.isPressed()) {
						if (startLongPress(cv)) {
							mHasPerformedLongPress = true;
						}
					}
				}
			}

			final class UnsetPressedState implements Runnable {
				public void run() {
					cv.setPressed(false);
				}
			}
		});
	}

	@Override
	protected void initializingData() {
		playVCR(videoPath);
	}

	public void playVCR(String url) {
		isVideoPlaying = true;
		mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
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
				VCRDownload vcr = new VCRDownload(getBaseContext());
				try {
					return vcr.downloadFile(videoPath);
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
					mVideoView.requestFocus();
					//mVideoView.setZOrderOnTop(true);
					mVideoView.start();
				}
			}
		});
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// 准备好播放视频源
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {
		finish();
	}

	@Override
	public void onPause() {
		mVideoView.pause();
		super.onPause();
	}

	public void onClick(View view) {
		if (isVideoPlaying && mVideoView.isPlaying() && mVideoView.canPause()) {
			isVideoPlaying = false;
			mVideoView.pause();
		} else {
			isVideoPlaying = true;
			mVideoView.start();
		}
	}

	public boolean startLongPress(View view) {
		return true;
	}
}
