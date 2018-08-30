package io.renren.modules.bj.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.bj.entity.XgdxxEntity;
import io.renren.modules.bj.service.XgdxxService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 巡更点信息表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@RestController
@RequestMapping("bj/xgdxx")
public class XgdxxController {
    @Autowired
    private XgdxxService xgdxxService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:xgdxx:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xgdxxService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:xgdxx:info")
    public R info(@PathVariable("id") Integer id){
			XgdxxEntity xgdxx = xgdxxService.selectById(id);

        return R.ok().put("xgdxx", xgdxx);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:xgdxx:save")
    public R save(@RequestBody XgdxxEntity xgdxx){
			xgdxxService.insert(xgdxx);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:xgdxx:update")
    public R update(@RequestBody XgdxxEntity xgdxx){
			xgdxxService.updateById(xgdxx);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:xgdxx:delete")
    public R delete(@RequestBody Integer[] ids){
			xgdxxService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
