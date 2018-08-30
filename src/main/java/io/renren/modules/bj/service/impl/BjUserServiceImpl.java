package io.renren.modules.bj.service.impl;

        import io.renren.modules.bj.bjEntity.RfidInfo;
        import io.renren.modules.bj.dao.BjUserDao;
        import io.renren.modules.bj.entity.BjUserEntity;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.Map;
        import com.baomidou.mybatisplus.mapper.EntityWrapper;
        import com.baomidou.mybatisplus.plugins.Page;
        import com.baomidou.mybatisplus.service.impl.ServiceImpl;
        import io.renren.common.utils.PageUtils;
        import io.renren.common.utils.Query;

        import io.renren.modules.bj.service.BjUserService;


@Service("bjUserService")
public class BjUserServiceImpl extends ServiceImpl<BjUserDao, BjUserEntity> implements BjUserService {

    @Autowired
    private BjUserDao userDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BjUserEntity> page = this.selectPage(
                new Query<BjUserEntity>(params).getPage(),
                new EntityWrapper<BjUserEntity>()
        );

        return new PageUtils(page);
    }

    public RfidInfo lastInfo(String rfidId){
        return userDao.lastInfo(rfidId);
    };
}
