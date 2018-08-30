package io.renren.modules.bj.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bj.dao.GpsDao;
import io.renren.modules.bj.entity.GpsEntity;
import io.renren.modules.bj.service.GpsService;


@Service("gpsService")
public class GpsServiceImpl extends ServiceImpl<GpsDao, GpsEntity> implements GpsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GpsEntity> page = this.selectPage(
                new Query<GpsEntity>(params).getPage(),
                new EntityWrapper<GpsEntity>()
        );

        return new PageUtils(page);
    }

}
