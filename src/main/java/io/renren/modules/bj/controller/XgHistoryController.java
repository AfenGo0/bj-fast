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

import io.renren.modules.bj.entity.XgHistoryEntity;
import io.renren.modules.bj.service.XgHistoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 巡更历史信息表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@RestController
@RequestMapping("bj/xghistory")
public class XgHistoryController {
    @Autowired
    private XgHistoryService xgHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:xghistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = xgHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:xghistory:info")
    public R info(@PathVariable("id") Integer id){
			XgHistoryEntity xgHistory = xgHistoryService.selectById(id);

        return R.ok().put("xgHistory", xgHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:xghistory:save")
    public R save(@RequestBody XgHistoryEntity xgHistory){
			xgHistoryService.insert(xgHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:xghistory:update")
    public R update(@RequestBody XgHistoryEntity xgHistory){
			xgHistoryService.updateById(xgHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:xghistory:delete")
    public R delete(@RequestBody Integer[] ids){
			xgHistoryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
