package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡更点信息表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@TableName("bj_xgdxx")
public class XgdxxEntity implements Serializable {
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
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 巡更时长
	 */
	private Integer xgsc;
	/**
	 * lng
	 */
	private Float lng;
	/**
	 * lat
	 */
	private Float lat;
	/**
	 * 是否启用 1启用   0 null 不启用
	 */
	private Integer isUse;

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
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：巡更时长
	 */
	public void setXgsc(Integer xgsc) {
		this.xgsc = xgsc;
	}
	/**
	 * 获取：巡更时长
	 */
	public Integer getXgsc() {
		return xgsc;
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
	/**
	 * 设置：是否启用 1启用   0 null 不启用
	 */
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	/**
	 * 获取：是否启用 1启用   0 null 不启用
	 */
	public Integer getIsUse() {
		return isUse;
	}
}
