package com.taxi.admin.authenticate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-5-19 上午09:49:19
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class Area implements Serializable {

	/**   */
	private static final long serialVersionUID = -5469369296177701050L;

	private Long areaID;

	private String province;

	public Area() {
		super();
	}

	public Area(Map<String, Object> data) {
		this.areaID = ((BigDecimal) data.get("id")).longValue();
		this.province = (String) data.get("province");
	}

	public Long getAreaID() {
		return areaID;
	}

	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
