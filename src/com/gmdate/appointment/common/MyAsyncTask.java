package com.gmdate.appointment.common;

import android.os.AsyncTask;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name MyAsyncTask
 * @description My Params AsyncTask
 * @date 2014-3-13
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2009-2014 广州睿塔科技 Ltd, All Rights Reserved.
 */
public abstract class MyAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	protected Object[] constructorParams;

	public MyAsyncTask(Object... params) {
		this.constructorParams = params;
	}
}
