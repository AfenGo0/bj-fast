package io.renren.modules.bj.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bj.dao.XgdxxDao;
import io.renren.modules.bj.entity.XgdxxEntity;
import io.renren.modules.bj.service.XgdxxService;


@Service("xgdxxService")
public class XgdxxServiceImpl extends ServiceImpl<XgdxxDao, XgdxxEntity> implements XgdxxService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XgdxxEntity> page = this.selectPage(
                new Query<XgdxxEntity>(params).getPage(),
                new EntityWrapper<XgdxxEntity>()
        );

        return new PageUtils(page);
    }

}
