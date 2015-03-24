package com.gmdate.appointment.net;

import com.xiaotian.framework.net.HttpsServerConnector;
import com.xiaotian.frameworkxt.net.HttpAction;
import com.xiaotian.frameworkxt.net.HttpNetworkException;
import com.xiaotian.frameworkxt.net.HttpParam;
import com.xiaotian.frameworkxt.net.HttpServer;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name RequestCommon
 * @description 公共数据请求接口
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
@HttpServer(serverName = Constant.SERVER, serverPort = Constant.PORT)
public class RequestCommon extends HttpsServerConnector {
	public static final String ARE_TYPE_HOT = "1";
	public static final String ARE_TYPE_PROVINCE = "2";
	public static final String ARE_TYPE_CITY = "3";

	// 获取热门区域
	@HttpAction(action = "gmdate_vip/area_ls.json", method = HttpAction.METHOD_POST)
	public HttpResponse getHotAre() throws HttpException {
		String result;
		try {
			result = sendAnnotatedRequest(new HttpParam("Type", ARE_TYPE_HOT));
		} catch (HttpNetworkException e) {
			throw new HttpException(e);
		}
		return HttpResponse.paseResponse(result);
	}

	// 获取城市列表
	@HttpAction(action = "gmdate_vip/area_ls.json", method = HttpAction.METHOD_POST)
	public HttpResponse getCity() throws HttpException {
		String response = "";
		return HttpResponse.paseResponse(response);
	}

	// 获取省份+城市列表
	@HttpAction(action = "gmdate_vip/area_ls.json", method = HttpAction.METHOD_POST)
	public HttpResponse getProviceAndCity() throws HttpException {
		String response = "";
		return HttpResponse.paseResponse(response);
	}

	// 获取籍贯列表
	@HttpAction(action = "gmdate_vip/area_ls.json", method = HttpAction.METHOD_POST)
	public HttpResponse getNativePlace() throws HttpException {
		String response = "";
		return HttpResponse.paseResponse(response);
	}
}
