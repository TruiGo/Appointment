package com.gmdate.appointment.net;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name HttpRequestException
 * @description 网络异常
 * @date 2015-3-12
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class HttpException extends Exception {
	private static final long serialVersionUID = 1L;
	private HttpResponse error;

	public HttpException(String message) {
		super(message);
	}

	public HttpException(Throwable throwable) {
		super(throwable);
	}

	public HttpException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public HttpException(HttpResponse errorResult) {
		super(errorResult.getMessage());
		this.error = errorResult;
	}

	public HttpResponse getError() {
		if (error != null) return error;
		return error = new HttpResponse(HttpResponse.STATUS_ERROR_UNKONW, "Unknow the error .");
	}
}
