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

import io.renren.modules.bj.entity.JqbEntity;
import io.renren.modules.bj.service.JqbService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 警情表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@RestController
@RequestMapping("bj/jqb")
public class JqbController {
    @Autowired
    private JqbService jqbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:jqb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jqbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:jqb:info")
    public R info(@PathVariable("id") Integer id){
			JqbEntity jqb = jqbService.selectById(id);

        return R.ok().put("jqb", jqb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:jqb:save")
    public R save(@RequestBody JqbEntity jqb){
			jqbService.insert(jqb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:jqb:update")
    public R update(@RequestBody JqbEntity jqb){
			jqbService.updateById(jqb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:jqb:delete")
    public R delete(@RequestBody Integer[] ids){
			jqbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
