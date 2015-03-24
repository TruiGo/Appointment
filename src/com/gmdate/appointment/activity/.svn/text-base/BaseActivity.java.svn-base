package com.gmdate.appointment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xiaotian.framework.view.ViewTopToolBar.ViewTopToolBarOnclickEvent;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name BaseActivity
 * @description 基础Activity
 * @date 2015-3-9
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class BaseActivity extends com.xiaotian.framework.activity.BaseFragmentActivity implements ViewTopToolBarOnclickEvent {

	@Override
	public void toolbarBackOnclick(View view) {}

	@Override
	public void toolbarTitleOnclick(View view) {}

	@Override
	public void toolbarForwardOnclick(View view) {}

	public void startActivity(Class<?> tagActivity, Bundle... extras) {
		Intent intent = new Intent(getBaseContext(), tagActivity);
		if (extras.length > 0) {
			intent.putExtras(extras[0]);
		}
		startActivity(intent);
	}

	public void startActivity(Class<?> tagActivity, int requestCode, Bundle... extras) {
		Intent intent = new Intent(getBaseContext(), tagActivity);
		if (extras.length > 0) {
			intent.putExtras(extras[0]);
		}
		startActivityForResult(intent, requestCode);
	}

}
