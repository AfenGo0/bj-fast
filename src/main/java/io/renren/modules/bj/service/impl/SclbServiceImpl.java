package io.renren.modules.bj.service.impl;

import io.renren.modules.bj.bjEntity.CarParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bj.dao.SclbDao;
import io.renren.modules.bj.entity.SclbEntity;
import io.renren.modules.bj.service.SclbService;


@Service("sclbService")
public class SclbServiceImpl extends ServiceImpl<SclbDao, SclbEntity> implements SclbService {

    @Autowired
    private  SclbDao sclbDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SclbEntity> page = this.selectPage(
                new Query<SclbEntity>(params).getPage(),
                new EntityWrapper<SclbEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CarParking> bjsc1() {
        return sclbDao.bjsc1();
    }

    @Override
    public List<CarParking> bjsc2() {
        return sclbDao.bjsc2();
    }

    @Override
    public List<CarParking> bjsc3() {
        return sclbDao.bjsc3();
    }

}
