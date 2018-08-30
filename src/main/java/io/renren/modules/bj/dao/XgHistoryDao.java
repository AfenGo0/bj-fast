package io.renren.modules.bj.dao;

import io.renren.modules.bj.entity.XgHistoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 巡更历史信息表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@Mapper
public interface XgHistoryDao extends BaseMapper<XgHistoryEntity> {
	Integer xgzs(@Param("xgr")String xgr,@Param("date")String date);

	Integer xgws(@Param("xgr")String xgr,@Param("date")String date);
}
