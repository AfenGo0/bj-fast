package io.renren.modules.bj.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bj.bjEntity.CarParking;
import io.renren.modules.bj.entity.SclbEntity;

import java.util.List;
import java.util.Map;

/**
 * 已上传的列表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-11 14:33:30
 */
public interface SclbService extends IService<SclbEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CarParking> bjsc1();

    List<CarParking> bjsc2();

    List<CarParking> bjsc3();
}

