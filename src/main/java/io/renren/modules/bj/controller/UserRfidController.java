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

import io.renren.modules.bj.entity.UserRfidEntity;
import io.renren.modules.bj.service.UserRfidService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户和RFID关系表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-09 08:54:25
 */
@RestController
@RequestMapping("bj/userrfid")
public class UserRfidController {
    @Autowired
    private UserRfidService userRfidService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:userrfid:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRfidService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:userrfid:info")
    public R info(@PathVariable("id") Integer id){
			UserRfidEntity userRfid = userRfidService.selectById(id);

        return R.ok().put("userRfid", userRfid);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:userrfid:save")
    public R save(@RequestBody UserRfidEntity userRfid){
			userRfidService.insert(userRfid);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:userrfid:update")
    public R update(@RequestBody UserRfidEntity userRfid){
			userRfidService.updateById(userRfid);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:userrfid:delete")
    public R delete(@RequestBody Integer[] ids){
			userRfidService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
