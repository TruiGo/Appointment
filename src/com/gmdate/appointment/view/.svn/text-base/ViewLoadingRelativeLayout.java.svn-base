package com.gmdate.appointment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gmdate.appointment.R;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ViewLoadingRelativeLayout
 * @description 加载页面
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ViewLoadingRelativeLayout extends RelativeLayout {
	ProgressBar progressBar;
	ImageView imageView;
	TextView textView;
	//
	int loadingImage;
	int loadingText;
	int hintText;

	public ViewLoadingRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		String NS = getResources().getString(R.string.XIAOTIAN_NS);
		loadingText = attrs.getAttributeResourceValue(NS, "loadingText", -1);
		loadingImage = attrs.getAttributeResourceValue(NS, "loadingImage", -1);
		boolean showProgressBar = attrs.getAttributeBooleanValue(NS, "showProgressBar", true);
		View empty = LayoutInflater.from(getContext()).inflate(R.layout.loading_view, new FrameLayout(getContext()), true);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		imageView = (ImageView) empty.findViewById(R.id.id_loading_0);
		progressBar = (ProgressBar) empty.findViewById(R.id.id_loading_1);
		textView = (TextView) empty.findViewById(R.id.id_loading_2);
		progressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
		if (loadingText != -1) {
			textView.setText(loadingText);
			textView.setVisibility(View.VISIBLE);
		}
		if (loadingImage != -1) {
			imageView.setImageResource(loadingImage);
			imageView.setVisibility(View.VISIBLE);
		}
		addView(empty, params);
	}

	public void setImage(int resImage) {
		if (resImage != -1) {
			imageView.setImageResource(resImage);
			if (imageView.getVisibility() != View.VISIBLE) {
				imageView.setVisibility(View.VISIBLE);
			}
		} else if (imageView.getVisibility() != View.GONE) {
			imageView.setVisibility(View.GONE);
		}
	}

	public void setProgressBarVisibility(int visibility) {
		progressBar.setVisibility(visibility);
	}

	public void setText(int resText) {
		if (resText != -1) {
			textView.setText(resText);
			if (textView.getVisibility() != View.VISIBLE) {
				textView.setVisibility(View.VISIBLE);
			}
		} else if (textView.getVisibility() != View.GONE) {
			textView.setVisibility(View.GONE);
		}
	}
}
