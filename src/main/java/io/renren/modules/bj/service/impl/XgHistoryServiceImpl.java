package io.renren.modules.bj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bj.dao.XgHistoryDao;
import io.renren.modules.bj.entity.XgHistoryEntity;
import io.renren.modules.bj.service.XgHistoryService;


@Service("xgHistoryService")
public class XgHistoryServiceImpl extends ServiceImpl<XgHistoryDao, XgHistoryEntity> implements XgHistoryService {

    @Autowired
    private XgHistoryDao xgHistoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XgHistoryEntity> page = this.selectPage(
                new Query<XgHistoryEntity>(params).getPage(),
                new EntityWrapper<XgHistoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer xgzs(String xgr, String date) {
        return xgHistoryDao.xgzs(xgr,date);
    }

    @Override
    public Integer xgws(String xgr, String date) {
        return xgHistoryDao.xgws(xgr,date);
    }

}
