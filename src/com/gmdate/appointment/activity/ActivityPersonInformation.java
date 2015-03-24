package com.gmdate.appointment.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmdate.appointment.R;
import com.gmdate.appointment.util.MyUtilImageWorker;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ActivityPersonInformation
 * @description 个人详情
 * @date 2015-3-13
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ActivityPersonInformation extends BaseActivity {
	MyUtilImageWorker mImageWorker;
	// UI
	ImageView mImageView;
	TextView textName;
	TextView textInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializingView();
		initializingData();
	}

	@Override
	protected void initializingView() {
		setContentView(R.layout.activity_person_information);
	}

	@Override
	protected void initializingData() {
		//getImageWorker().loadImage("http://h.hiphotos.baidu.com/image/pic/item/dbb44aed2e738bd424ebf02ba28b87d6267ff9e6.jpg", (ImageView) findViewById(R.id.id_0));
	}

	public MyUtilImageWorker getImageWorker() {
		if (mImageWorker != null) return mImageWorker;
		mImageWorker = ImageWorkerFragment.getInstance(this, getSupportFragmentManager()).getImageWorker();
		mImageWorker.setImageFadeIn(true);
		mImageWorker.setCornerRadio(5);
		return mImageWorker;
	}

	public static class ImagViewCorners extends ImageView {

		public ImagViewCorners(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
	}
}
