package io.renren.modules.bj.dao;

import io.renren.modules.bj.entity.JqbEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 警情表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@Mapper
public interface JqbDao extends BaseMapper<JqbEntity> {

    /**
     *
     * @param jjr 接警人
     * @param date 时间
     * @return
     */
    Integer cxjjsl(@Param("jjr")String jjr,@Param("jjsj")String date);
	
}
