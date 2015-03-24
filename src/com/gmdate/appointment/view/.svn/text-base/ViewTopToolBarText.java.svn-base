package com.gmdate.appointment.view;

import com.xiaotian.framework.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @文件名称 ViewTopToolBarText.java
 * @创建日期 2015-3-20 下午12:14:34
 * @作者 XiaoTian(Create by Administrator)
 * @联系作者 Email: gtrstudio@qq.com
 * @Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ViewTopToolBarText extends ViewTopToolBar {

	public ViewTopToolBarText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void constructContent(Context context, AttributeSet attrs) {
		TextView textView;
		int resource = 0;
		int padding10 = getResources().getDimensionPixelSize(R.dimen.dimen_10);
		View root = LayoutInflater.from(context).inflate(com.gmdate.appointment.R.layout.model_view_toptoolbar_textview, this);
		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.model_view_toptoolbar_xiaotian);
		for (int i = 0; i < typeArray.getIndexCount(); i++) {
			int arrtIndex = typeArray.getIndex(i);
			switch (arrtIndex) {
			case R.styleable.model_view_toptoolbar_xiaotian_textTitle:
				resource = typeArray.getResourceId(arrtIndex, R.string.content_notfound_xiaotian);
				textView = ((TextView) root.findViewById(R.id.view_model_toptoolbar_title_xiaotian));
				textView.setText(resource);
				textView.setPadding(padding10, padding10, padding10, padding10);
				break;
			case R.styleable.model_view_toptoolbar_xiaotian_iconLeft:
				resource = typeArray.getResourceId(arrtIndex, 0);
				textView = (TextView) root.findViewById(R.id.view_model_toptoolbar_button_left_xiaotian);
				textView.setCompoundDrawablesWithIntrinsicBounds(resource, 0, 0, 0);
				if (textView.getVisibility() != View.VISIBLE) textView.setVisibility(VISIBLE);
				textView.setPadding(padding10, padding10, padding10, padding10);
				break;
			case R.styleable.model_view_toptoolbar_xiaotian_iconRight:
				resource = typeArray.getResourceId(arrtIndex, 0);
				textView = (TextView) root.findViewById(R.id.view_model_toptoolbar_button_right_xiaotian);
				textView.setCompoundDrawablesWithIntrinsicBounds(resource, 0, 0, 0);
				if (textView.getVisibility() != View.VISIBLE) textView.setVisibility(VISIBLE);
				textView.setPadding(padding10, padding10, padding10, padding10);
				break;
			case R.styleable.model_view_toptoolbar_xiaotian_background:
				resource = typeArray.getResourceId(arrtIndex, 0);
				root.findViewById(R.id.view_model_toptoolbar_root_xiaotian).setBackgroundResource(resource);
				break;
			}
		}
		typeArray.recycle();
		String XIAOTIAN_NS = getResources().getString(R.string.XIAOTIAN_NS);
		int leftTextRes = attrs.getAttributeResourceValue(XIAOTIAN_NS, "textLeft", -1);
		int rightTextRes = attrs.getAttributeResourceValue(XIAOTIAN_NS, "textRight", -1);
		if (leftTextRes != -1) {
			textView = (TextView) root.findViewById(R.id.view_model_toptoolbar_button_left_xiaotian);
			textView.setText(leftTextRes);
			if (textView.getVisibility() != View.VISIBLE) textView.setVisibility(VISIBLE);
			textView.setPadding(padding10, padding10, padding10, padding10);
		}
		if (rightTextRes != -1) {
			textView = (TextView) root.findViewById(R.id.view_model_toptoolbar_button_right_xiaotian);
			textView.setText(rightTextRes);
			if (textView.getVisibility() != View.VISIBLE) textView.setVisibility(VISIBLE);
			textView.setPadding(padding10, padding10, padding10, padding10);
		}
	}
}
