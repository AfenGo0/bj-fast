package io.renren.modules.bj.dao;

import io.renren.modules.bj.bjEntity.CarParking;
import io.renren.modules.bj.entity.SclbEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 已上传的列表
 * 
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-11 14:33:30
 */
@Mapper
public interface SclbDao extends BaseMapper<SclbEntity> {

    List<CarParking> bjsc1();

    List<CarParking> bjsc2();

    List<CarParking> bjsc3();
	
}
