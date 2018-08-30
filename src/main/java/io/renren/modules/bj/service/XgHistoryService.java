package io.renren.modules.bj.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bj.entity.XgHistoryEntity;

import java.util.Map;

/**
 * 巡更历史信息表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
public interface XgHistoryService extends IService<XgHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer xgzs(String xgr,String date);

    Integer xgws(String xgr,String date);
}

