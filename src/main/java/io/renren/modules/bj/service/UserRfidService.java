package io.renren.modules.bj.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bj.entity.UserRfidEntity;

import java.util.Map;

/**
 * 用户和RFID关系表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-09 08:54:25
 */
public interface UserRfidService extends IService<UserRfidEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

