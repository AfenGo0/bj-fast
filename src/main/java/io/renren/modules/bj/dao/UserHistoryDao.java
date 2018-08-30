package io.renren.modules.bj.dao;

import io.renren.modules.bj.entity.UserHistoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录信息历史表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-06 13:40:46
 */
@Mapper
public interface UserHistoryDao extends BaseMapper<UserHistoryEntity> {
	
}
