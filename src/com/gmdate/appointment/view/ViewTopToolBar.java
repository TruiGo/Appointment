package com.gmdate.appointment.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xiaotian.framework.R;

public class ViewTopToolBar extends com.xiaotian.framework.view.ViewTopToolBar {

	public ViewTopToolBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void constructContent(Context context, AttributeSet attrs) {
		ImageButton button;
		TextView textView;
		int resource = 0;
		int padding10 = getResources().getDimensionPixelSize(R.dimen.dimen_10);
		View root = LayoutInflater.from(context).inflate(com.gmdate.appointment.R.layout.model_view_toptoolbar_xiaotian, this);
		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.model_view_toptoolbar_xiaotian);
		for (int i = 0; i < typeArray.getIndexCount(); i++) {
			int arrtIndex = typeArray.getIndex(i);
			switch (arrtIndex) {
			case R.styleable.model_view_toptoolbar_xiaotian_textTitle:
				resource = typeArray.getResourceId(arrtIndex, R.string.content_notfound_xiaotian);
				textView = ((TextView) root.findViewById(R.id.view_model_toptoolbar_title_xiaotian));
				textView.setText(resource);
				break;
			case R.styleable.model_view_toptoolbar_xiaotian_iconLeft:
				resource = typeArray.getResourceId(arrtIndex, 0);
				button = (ImageButton) root.findViewById(R.id.view_model_toptoolbar_button_left_xiaotian);
				button.setImageResource(resource);
				button.setVisibility(VISIBLE);
				button.setPadding(padding10, padding10, padding10, padding10);
				break;
			case R.styleable.model_view_toptoolbar_xiaotian_iconRight:
				resource = typeArray.getResourceId(arrtIndex, 0);
				button = (ImageButton) root.findViewById(R.id.view_model_toptoolbar_button_right_xiaotian);
				button.setImageResource(resource);
				button.setVisibility(VISIBLE);
				button.setPadding(padding10, padding10, padding10, padding10);
				break;
			case R.styleable.model_view_toptoolbar_xiaotian_background:
				resource = typeArray.getResourceId(arrtIndex, 0);
				root.findViewById(R.id.view_model_toptoolbar_root_xiaotian).setBackgroundResource(resource);
				break;
			}
		}
		typeArray.recycle();
	}
}
