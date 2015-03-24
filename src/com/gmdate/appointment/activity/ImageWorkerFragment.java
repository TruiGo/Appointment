package com.gmdate.appointment.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.gmdate.appointment.util.MyUtilImageWorker;
import com.xiaotian.framework.util.async.loadimage.ImageCache.ImageCacheParams;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ImageWorkerFragment
 * @description
 * @date 2015-3-12
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ImageWorkerFragment extends Fragment {
	public static final String TAG = "ImageWorkerFragment";
	private MyUtilImageWorker imageWorker;
	private Context mContext;

	public ImageWorkerFragment(Context context) {
		this.mContext = context;
	}

	public static ImageWorkerFragment getInstance(Context context, FragmentManager fm) {
		Fragment iwf = fm.findFragmentByTag(TAG);
		if (iwf != null) return (ImageWorkerFragment) iwf;
		iwf = new ImageWorkerFragment(context);
		fm.beginTransaction().add(iwf, TAG).commit();
		return (ImageWorkerFragment) iwf;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	public MyUtilImageWorker getImageWorker() {
		if (imageWorker != null) return imageWorker;
		imageWorker = new MyUtilImageWorker(mContext);
		imageWorker.setImageFadeIn(true);
		ImageCacheParams cacheParams = new ImageCacheParams(mContext, "WebImageCache");
		cacheParams.memoryCacheEnabled = true;
		cacheParams.diskCacheEnabled = true;
		cacheParams.memCacheSize = 10 * 1024; // KB
		cacheParams.diskCacheSize = 60 * 1024 * 1024; // B
		imageWorker.addImageCache(getFragmentManager(), cacheParams);
		return imageWorker;
	}
}