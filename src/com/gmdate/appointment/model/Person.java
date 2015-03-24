package com.gmdate.appointment.model;

import java.io.Serializable;

import com.xiaotian.frameworkxt.serializer.json.JSONEntity;

/**
 * @version 1.0.0
 * @author XiaoTian
 * @name Person
 * @description 会员实体
 * @date 2015-3-11
 * @link gtrstudio@qq.com
 * @copyright Copyright © 2013-2015 深圳名仕之约信息科技有限公司 Ltd, All Rights Reserved.
 */
@JSONEntity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int ATTENTION_UNKNOW = 0;
	public static final int ATTENTION_FOCUS = 1;
	private String pathPicture;
	private String pathVCR;
	private Integer attention;
	private String name;
	private Integer vipLevel;
	private String vipLevelName;
	private Integer starLevel;
	private Integer age;
	private Integer stature;
	private String are;
	private String education;

	public String getPathPicture() {
		return pathPicture;
	}

	public void setPathPicture(String pathPicture) {
		this.pathPicture = pathPicture;
	}

	public String getPathVCR() {
		return pathVCR;
	}

	public void setPathVCR(String pathVCR) {
		this.pathVCR = pathVCR;
	}

	public Integer getAttention() {
		return attention;
	}

	public void setAttention(Integer attention) {
		this.attention = attention;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}

	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public String getEducation() {
		return education;
	}

	public String getAre() {
		return are;
	}

	public void setAre(String are) {
		this.are = are;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getVipLevelName() {
		return vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}

}
