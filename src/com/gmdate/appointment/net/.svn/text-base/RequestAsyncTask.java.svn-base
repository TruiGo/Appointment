package com.gmdate.appointment.net;

import android.os.AsyncTask;

import com.gmdate.appointment.common.Mylog;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name RequestAsyncTask
 * @description
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public abstract class RequestAsyncTask extends AsyncTask<String, Integer, HttpResponse> {

	@Override
	protected HttpResponse doInBackground(String... params) {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {}
		try {
			return HttpResponse.paseResponse("{'Status':200,'ErrorMessage':'Success','Data':{},'DataList':[{}]}");
		} catch (HttpException e) {
			Mylog.printStackTrace(e);
			return new HttpResponse(HttpResponse.STATUS_ERROR_UNKONW, e.getMessage());
		}
	}
}
