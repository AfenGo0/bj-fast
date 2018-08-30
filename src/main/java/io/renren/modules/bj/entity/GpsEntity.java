package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 车辆GPS信息表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@TableName("bj_gps")
public class GpsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 设备编号
	 */
	private String sbbh;
	/**
	 * 驾驶人
	 */
	private String username;
	/**
	 * 车牌号
	 */
	private String cph;
	/**
	 * 当前状态  1停驶 2巡逻 3处警
	 */
	private Integer status;
	/**
	 * 当前时间
	 */
	private String createTime;
	/**
	 * 
	 */
	private Double lng;
	/**
	 * 
	 */
	private Double lat;

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
	 * 设置：设备编号
	 */
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	/**
	 * 获取：设备编号
	 */
	public String getSbbh() {
		return sbbh;
	}
	/**
	 * 设置：驾驶人
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：驾驶人
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：车牌号
	 */
	public void setCph(String cph) {
		this.cph = cph;
	}
	/**
	 * 获取：车牌号
	 */
	public String getCph() {
		return cph;
	}
	/**
	 * 设置：当前状态  1停驶 2巡逻 3处警
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：当前状态  1停驶 2巡逻 3处警
	 */
	public Integer getStatus() {
		return status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置：
	 */
	public void setLng(Double lng) {
		this.lng = lng;
	}
	/**
	 * 获取：
	 */
	public Double getLng() {
		return lng;
	}
	/**
	 * 设置：
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}
	/**
	 * 获取：
	 */
	public Double getLat() {
		return lat;
	}
}
