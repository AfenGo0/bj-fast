package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录信息历史表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-06 13:40:46
 */
@TableName("bj_user_history")
public class UserHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * rfid读取设备号
	 */
	private String rfidDevice;
	/**
	 * 警灯设备编号
	 */
	private String lightSbbh;
	/**
	 * rfid卡编号
	 */
	private String rfidCard;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 结束时间
	 */
	private Date endTime;

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
	 * 设置：用户名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户名
	 */
	public String getName() {
		return name;
	}

	public String getRfidDevice() {
		return rfidDevice;
	}

	public void setRfidDevice(String rfidDevice) {
		this.rfidDevice = rfidDevice;
	}

	/**
	 * 设置：警灯设备编号
	 */
	public void setLightSbbh(String lightSbbh) {
		this.lightSbbh = lightSbbh;
	}
	/**
	 * 获取：警灯设备编号
	 */
	public String getLightSbbh() {
		return lightSbbh;
	}
	/**
	 * 设置：rfid卡编号
	 */
	public void setRfidCard(String rfidCard) {
		this.rfidCard = rfidCard;
	}
	/**
	 * 获取：rfid卡编号
	 */
	public String getRfidCard() {
		return rfidCard;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
}
