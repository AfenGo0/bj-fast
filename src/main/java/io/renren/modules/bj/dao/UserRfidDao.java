package io.renren.modules.bj.dao;

import io.renren.modules.bj.entity.UserRfidEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和RFID关系表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-09 08:54:25
 */
@Mapper
public interface UserRfidDao extends BaseMapper<UserRfidEntity> {
	
}
