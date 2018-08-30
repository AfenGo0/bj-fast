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

import io.renren.modules.bj.entity.GpsEntity;
import io.renren.modules.bj.service.GpsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 车辆GPS信息表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-07 14:33:42
 */
@RestController
@RequestMapping("bj/gps")
public class GpsController {
    @Autowired
    private GpsService gpsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:gps:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gpsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:gps:info")
    public R info(@PathVariable("id") Integer id){
			GpsEntity gps = gpsService.selectById(id);

        return R.ok().put("gps", gps);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:gps:save")
    public R save(@RequestBody GpsEntity gps){
			gpsService.insert(gps);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:gps:update")
    public R update(@RequestBody GpsEntity gps){
			gpsService.updateById(gps);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:gps:delete")
    public R delete(@RequestBody Integer[] ids){
			gpsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
