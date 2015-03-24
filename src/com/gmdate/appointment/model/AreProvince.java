package com.gmdate.appointment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name AreProvince
 * @description 省份
 * @date 2015-3-16
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
public class AreProvince extends Are {
	private List<Are> citys;

	public AreProvince() {
		citys = new ArrayList<Are>();
	}

	public List<Are> getCitys() {
		return citys;
	}

	public void addCitys(Are city) {
		this.citys.add(city);
	}

}
