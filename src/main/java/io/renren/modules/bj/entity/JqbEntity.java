package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 警情表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@TableName("bj_jqb")
public class JqbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 事件名称
	 */
	private String sjmc;
	/**
	 * 事件内容
	 */
	private String sjnr;
	/**
	 * 接警人
	 */
	private String jjr;
	/**
	 * 返回车牌号数量
	 */
	private String fhcphsl;
	/**
	 * 紧急程度  1紧急   2一般  3不急
	 */
	private Integer jjcd;
	/**
	 * 状态 0未接  1进行中 2完成 3到达，处理中
	 */
	private Integer status;
	/**
	 * lng
	 */
	private Double lng;
	/**
	 * lat
	 */
	private Double lat;
	/**
	 * 
	 */
	private String createTime;

	private Integer bjId;

	public Integer getBjId() {
		return bjId;
	}

	public void setBjId(Integer bjId) {
		this.bjId = bjId;
	}

	public String getSjnr() {
		return sjnr;
	}

	public void setSjnr(String sjnr) {
		this.sjnr = sjnr;
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
	 * 设置：事件名称
	 */
	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}
	/**
	 * 获取：事件名称
	 */
	public String getSjmc() {
		return sjmc;
	}
	/**
	 * 设置：接警人
	 */
	public void setJjr(String jjr) {
		this.jjr = jjr;
	}
	/**
	 * 获取：接警人
	 */
	public String getJjr() {
		return jjr;
	}
	/**
	 * 设置：返回车牌号数量
	 */
	public void setFhcphsl(String fhcphsl) {
		this.fhcphsl = fhcphsl;
	}
	/**
	 * 获取：返回车牌号数量
	 */
	public String getFhcphsl() {
		return fhcphsl;
	}
	/**
	 * 设置：紧急程度  1紧急   2一般  3不急
	 */
	public void setJjcd(Integer jjcd) {
		this.jjcd = jjcd;
	}
	/**
	 * 获取：紧急程度  1紧急   2一般  3不急
	 */
	public Integer getJjcd() {
		return jjcd;
	}
	/**
	 * 设置：状态 0未接  1进行中 2完成 3到达，处理中
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0未接  1进行中 2完成 3到达，处理中
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：lng
	 */
	public void setLng(Double lng) {
		this.lng = lng;
	}
	/**
	 * 获取：lng
	 */
	public Double getLng() {
		return lng;
	}
	/**
	 * 设置：lat
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}
	/**
	 * 获取：lat
	 */
	public Double getLat() {
		return lat;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
