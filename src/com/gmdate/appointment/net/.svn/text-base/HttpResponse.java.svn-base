package com.gmdate.appointment.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.xiaotian.frameworkxt.serializer.json.JSONSerializer;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name HttpResponse
 * @description 网络请求返回实体
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class HttpResponse extends JSONSerializer {
	public static final String STATUS_SUCCESS = "0";
	public static final String STATUS_ERROR_UNKONW = "-1";
	private Exception e;
	private String status;
	private String message;
	private JSONObject jsonData;
	private JSONArray jsonDataList;

	public static HttpResponse paseResponse(String response) throws HttpException {
		HttpResponse hr = new HttpResponse();
		JSONTokener tokener = new JSONTokener(response);
		Object obj;
		try {
			obj = tokener.nextValue();
			if (obj instanceof JSONObject) {
				JSONObject responseObj = (JSONObject) obj;
				if (responseObj.has("Status")) hr.status = responseObj.getString("Status");
				if (responseObj.has("ErrorMessage")) hr.message = responseObj.getString("ErrorMessage");
				if (responseObj.has("Data")) hr.jsonData = responseObj.getJSONObject("Data");
				if (responseObj.has("DataList")) hr.jsonDataList = responseObj.getJSONArray("DataList");
			}
		} catch (JSONException e) {
			throw new HttpException(e);
		}
		return hr;
	}

	public static HttpResponse createException(Exception e) {
		return new HttpResponse(STATUS_ERROR_UNKONW, e);
	}

	public HttpResponse() {}

	public HttpResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpResponse(String status, Exception e) {
		this.e = e;
		this.status = status;
		this.message = e.getMessage();
	}

	public boolean isSuccess() {
		return status.equals(STATUS_SUCCESS);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getJsonData() {
		return jsonData;
	}

	public JSONArray getJsonDataList() {
		return jsonDataList;
	}
}
