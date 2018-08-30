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

import io.renren.modules.bj.entity.SclbEntity;
import io.renren.modules.bj.service.SclbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 已上传的列表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-11 14:33:30
 */
@RestController
@RequestMapping("bj/sclb")
public class SclbController {
    @Autowired
    private SclbService sclbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:sclb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sclbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:sclb:info")
    public R info(@PathVariable("id") Integer id){
			SclbEntity sclb = sclbService.selectById(id);

        return R.ok().put("sclb", sclb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:sclb:save")
    public R save(@RequestBody SclbEntity sclb){
			sclbService.insert(sclb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:sclb:update")
    public R update(@RequestBody SclbEntity sclb){
			sclbService.updateById(sclb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:sclb:delete")
    public R delete(@RequestBody Integer[] ids){
			sclbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
