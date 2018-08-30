package io.renren.modules.bj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bj.dao.JqbDao;
import io.renren.modules.bj.entity.JqbEntity;
import io.renren.modules.bj.service.JqbService;


@Service("jqbService")
public class JqbServiceImpl extends ServiceImpl<JqbDao, JqbEntity> implements JqbService {

    @Autowired
    private JqbDao jqbDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JqbEntity> page = this.selectPage(
                new Query<JqbEntity>(params).getPage(),
                new EntityWrapper<JqbEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer cxjjsl(String jjr, String jjsl) {
        return jqbDao.cxjjsl(jjr,jjsl);
    }
}
