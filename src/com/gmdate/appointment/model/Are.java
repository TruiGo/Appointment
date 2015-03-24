package com.gmdate.appointment.model;

import com.xiaotian.frameworkxt.serializer.json.JSONEntity;
import com.xiaotian.frameworkxt.serializer.json.JSONField;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name Are
 * @description 区域
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
@JSONEntity
public class Are {
	@JSONField(name = "pid")
	private String id;
	@JSONField(name = "name")
	private String name;

	public Are() {}

	public Are(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
