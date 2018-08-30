package io.renren.modules.bj.dao;

import io.renren.modules.bj.bjEntity.RfidInfo;
import io.renren.modules.bj.entity.BjUserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户登录信息表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-06 13:40:46
 */
@Mapper
public interface BjUserDao extends BaseMapper<BjUserEntity> {

    RfidInfo lastInfo(@Param("rfidId") String rfidId);
	
}
