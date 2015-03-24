package com.gmdate.appointment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name FixableGridView
 * @description 不滚动GridView
 * @date 2015-3-12
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class FixableImageGridView extends GridView {
	public FixableImageGridView(Context context) {
		super(context);
	}

	public FixableImageGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FixableImageGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
