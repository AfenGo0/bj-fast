package io.renren.modules.bj.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bj.entity.GpsEntity;

import java.util.Map;

/**
 * 车辆GPS信息表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
public interface GpsService extends IService<GpsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

