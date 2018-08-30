package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡更历史信息表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@TableName("bj_xg_history")
public class XgHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 巡更点名称
	 */
	private String name;
	/**
	 * lng
	 */
	private Float lng;
	/**
	 * lat
	 */
	private Float lat;
	/**
	 * 巡更时间
	 */
	private String xgTime;
	/**
	 * 巡更人
	 */
	private String xgr;
	/**
	 * 巡更车辆
	 */
	private String xgcl;

	private Integer bjId;

	public Integer getBjId() {
		return bjId;
	}

	public void setBjId(Integer bjId) {
		this.bjId = bjId;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：巡更点名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：巡更点名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：lng
	 */
	public void setLng(Float lng) {
		this.lng = lng;
	}
	/**
	 * 获取：lng
	 */
	public Float getLng() {
		return lng;
	}
	/**
	 * 设置：lat
	 */
	public void setLat(Float lat) {
		this.lat = lat;
	}
	/**
	 * 获取：lat
	 */
	public Float getLat() {
		return lat;
	}

	public String getXgTime() {
		return xgTime;
	}

	public void setXgTime(String xgTime) {
		this.xgTime = xgTime;
	}

	/**
	 * 设置：巡更人
	 */
	public void setXgr(String xgr) {
		this.xgr = xgr;
	}
	/**
	 * 获取：巡更人
	 */
	public String getXgr() {
		return xgr;
	}
	/**
	 * 设置：巡更车辆
	 */
	public void setXgcl(String xgcl) {
		this.xgcl = xgcl;
	}
	/**
	 * 获取：巡更车辆
	 */
	public String getXgcl() {
		return xgcl;
	}
}
