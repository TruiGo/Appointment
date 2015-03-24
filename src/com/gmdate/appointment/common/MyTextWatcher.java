package com.gmdate.appointment.common;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name MyTextWatcher
 * @description Text 侦听抽象类
 * @date 2014-5-23
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2009-2014 广州线上线下信息科技 Ltd, All Rights Reserved.
 */
public abstract class MyTextWatcher implements TextWatcher {
	Object[] initParams;

	public MyTextWatcher(Object... initParams) {
		this.initParams = initParams;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	@Override
	public void afterTextChanged(Editable s) {}
}
