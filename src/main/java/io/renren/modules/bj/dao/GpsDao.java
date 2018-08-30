package io.renren.modules.bj.dao;

import io.renren.modules.bj.entity.GpsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆GPS信息表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@Mapper
public interface GpsDao extends BaseMapper<GpsEntity> {
	
}
