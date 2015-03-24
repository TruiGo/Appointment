package com.gmdate.appointment.net;

import com.xiaotian.frameworkxt.net.HttpAction;
import com.xiaotian.frameworkxt.net.HttpServer;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name RequestPerson
 * @description 会员请求
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
@HttpServer(serverName = "", serverPort = "")
public class RequestPerson extends BaseRequest {

	@HttpAction(action = "", method = HttpAction.METHOD_POST)
	public HttpResponse queryPerson(String sex, String are) {

		return null;
	}
}
