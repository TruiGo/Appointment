package com.gmdate.appointment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gmdate.appointment.R;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ViewEmptyRelativeLayout
 * @description 无数据页面
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ViewEmptyRelativeLayout extends RelativeLayout {
	ImageView imageView;
	TextView textEmpty;
	TextView textHint;
	//
	int emptyImage;
	int emptyText;
	int hintText;

	public ViewEmptyRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		String NS = getResources().getString(R.string.XIAOTIAN_NS);
		emptyImage = attrs.getAttributeResourceValue(NS, "emptyImage", -1);
		emptyText = attrs.getAttributeResourceValue(NS, "emptyText", -1);
		hintText = attrs.getAttributeResourceValue(NS, "hintText", -1);
		View empty = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, new FrameLayout(getContext()), true);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		imageView = (ImageView) empty.findViewById(R.id.id_empty_0);
		textEmpty = (TextView) empty.findViewById(R.id.id_empty_1);
		textHint = (TextView) empty.findViewById(R.id.id_empty_2);
		if (emptyImage != -1) {
			imageView.setImageResource(emptyImage);
			imageView.setVisibility(View.VISIBLE);
		}
		if (emptyText != -1) {
			textEmpty.setText(emptyText);
			textEmpty.setVisibility(View.VISIBLE);
		}
		if (hintText != -1) {
			textHint.setText(hintText);
			textHint.setVisibility(View.VISIBLE);
		}
		addView(empty, params);
	}

	public void setImage(int resImage) {
		if (emptyImage != -1) {
			imageView.setImageResource(emptyImage);
			if (imageView.getVisibility() != View.VISIBLE) {
				imageView.setVisibility(View.VISIBLE);
			}
		} else if (imageView.getVisibility() != View.GONE) {
			imageView.setVisibility(View.GONE);
		}
	}

	public void setTextEmpty(int resEmpty) {
		if (resEmpty != -1) {
			textEmpty.setText(resEmpty);
			if (textEmpty.getVisibility() != View.VISIBLE) {
				textEmpty.setVisibility(View.VISIBLE);
			}
		} else if (textEmpty.getVisibility() != View.GONE) {
			textEmpty.setVisibility(View.GONE);
		}
	}

	public void setTextHint(int resHint) {
		if (resHint != -1) {
			textHint.setText(resHint);
			if (textHint.getVisibility() != View.VISIBLE) {
				textHint.setVisibility(View.VISIBLE);
			}
		} else if (textHint.getVisibility() != View.GONE) {
			textHint.setVisibility(View.GONE);
		}
	}
}
