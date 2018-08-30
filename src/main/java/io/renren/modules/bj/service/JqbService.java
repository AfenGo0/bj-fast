package io.renren.modules.bj.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.bj.entity.JqbEntity;

import java.util.Map;

/**
 * 警情表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
public interface JqbService extends IService<JqbEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer cxjjsl(String jjr,String jjsl);
}

