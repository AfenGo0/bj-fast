package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户和RFID关系表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-09 08:54:25
 */
@TableName("bj_user_rfid")
public class UserRfidEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 登录用户名
	 */
	private String name;
	/**
	 * 卡id
	 */
	private String rfid;

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
	 * 设置：登录用户名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：登录用户名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：卡id
	 */
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	/**
	 * 获取：卡id
	 */
	public String getRfid() {
		return rfid;
	}
}
