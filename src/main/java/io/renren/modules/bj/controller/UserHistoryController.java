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

import io.renren.modules.bj.entity.UserHistoryEntity;
import io.renren.modules.bj.service.UserHistoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户登录信息历史表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-06 13:40:46
 */
@RestController
@RequestMapping("bj/userhistory")
public class UserHistoryController {
    @Autowired
    private UserHistoryService userHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:userhistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:userhistory:info")
    public R info(@PathVariable("id") Integer id){
			UserHistoryEntity userHistory = userHistoryService.selectById(id);

        return R.ok().put("userHistory", userHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:userhistory:save")
    public R save(@RequestBody UserHistoryEntity userHistory){
			userHistoryService.insert(userHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:userhistory:update")
    public R update(@RequestBody UserHistoryEntity userHistory){
			userHistoryService.updateById(userHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:userhistory:delete")
    public R delete(@RequestBody Integer[] ids){
			userHistoryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
