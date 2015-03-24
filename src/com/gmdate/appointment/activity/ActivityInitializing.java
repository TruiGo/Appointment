package com.gmdate.appointment.activity;

import android.os.Bundle;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name ActivityInitializing
 * @description 初始化页面
 * @date 2015-3-9
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class ActivityInitializing extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializingView();
		initializingData();
	}

	@Override
	protected void initializingView() {
		super.initializingView();
	}

	@Override
	protected void initializingData() {
		super.initializingData();
		inputDate("选择时间日期", null);
		//new SlideDateTimePicker.Builder(getSupportFragmentManager()).setListener(null).setInitialDate(new Date())
		//				.setMinDate(minDate)
		//				.setMaxDate(maxDate)
		//				.setIs24HourTime(true)
		//		.setTheme(SlideDateTimePicker.HOLO_LIGHT).setIndicatorColor(Color.parseColor("#990000")).build().show();
	}
}
