package io.renren.modules.bj.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bj.entity.UserHistoryEntity;

import java.util.Map;

/**
 * 用户登录信息历史表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-06 13:40:46
 */
public interface UserHistoryService extends IService<UserHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);


}

