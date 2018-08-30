package io.renren.modules.bj.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.bj.dao.UserRfidDao;
import io.renren.modules.bj.entity.UserRfidEntity;
import io.renren.modules.bj.service.UserRfidService;


@Service("userRfidService")
public class UserRfidServiceImpl extends ServiceImpl<UserRfidDao, UserRfidEntity> implements UserRfidService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserRfidEntity> page = this.selectPage(
                new Query<UserRfidEntity>(params).getPage(),
                new EntityWrapper<UserRfidEntity>()
        );

        return new PageUtils(page);
    }

}
