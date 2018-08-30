package io.renren.modules.bj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 已上传的列表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-11 14:33:30
 */
@TableName("bj_sclb")
public class SclbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 已传id
	 */
	private Integer ycId;

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
	 * 设置：已传id
	 */
	public void setYcId(Integer ycId) {
		this.ycId = ycId;
	}
	/**
	 * 获取：已传id
	 */
	public Integer getYcId() {
		return ycId;
	}
}
