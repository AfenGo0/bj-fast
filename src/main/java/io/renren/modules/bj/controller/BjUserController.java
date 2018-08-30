package io.renren.modules.bj.controller;

import java.util.*;

import io.renren.modules.bj.entity.BjUserEntity;
import io.renren.modules.bj.service.UserHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.bj.service.BjUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户登录信息表
 *
 * @author afeng
 * @email chenbin824@163.com
 * @date 2018-08-06 13:40:46
 */
@RestController
@RequestMapping("bj/user")
public class BjUserController {
    @Autowired
    private BjUserService userService;
    @Autowired
    private UserHistoryService historyService;


    /**
     * 实现登录并写入到登录信息表和历史表
     * @param username
     * @param pwd
     * @return
     */
    /*@RequestMapping("/login")
    public R login(@RequestParam("username")String username,@RequestParam("pwd")String pwd,@RequestParam("rfidDevice")String rfidDevice){
        boolean login = false;
        //登录北京接口
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",pwd);
        map.put("grant_type","password");
        String post = HttpClientHelper.sendPost(Http.token,map,"UTF-8");
        BjUser bjUser =new BjUser();
        JSONObject json = JSONObject.fromObject(post);
        if (json != null) {
            bjUser.setAccess_token(json.get("access_token").toString());
            bjUser.setToken_type(json.get("token_type").toString());
            bjUser.setUserName(json.get("userName").toString());
            bjUser.setExpires_in(Integer.parseInt(json.get("expires_in").toString()));
            login = true;
        }
        if (login && bjUser != null){
            List<RfidInfo> list = userService.lastList();
            //写数据到bj_user表
            BjUserEntity userEntity = userService.selectOne(new EntityWrapper<BjUserEntity>().where("rfidDevice = {0}",rfidDevice));
            //存在数据，转到历史表中
            if (userEntity != null){
                UserHistoryEntity historyEntity = new UserHistoryEntity();
                historyEntity.setLightSbbh(userEntity.getLightSbbh());
                historyEntity.setCreateTime(userEntity.getCreateTime());
                historyEntity.setEndTime(new Date());
                historyEntity.setName(userEntity.getName());
                historyEntity.setRfidCard(userEntity.getRfidCard());
                historyEntity.setRfidDevice(userEntity.getRfidDevice());
                historyService.insert(historyEntity);
            }else{
                userEntity.setCreateTime(new Date());
                userEntity.setName(bjUser.getUserName());
                userService.insertOrUpdate(userEntity);
            }
            return R.ok();
        }else {
            return R.error(201,"登录失败，账号或密码错误！");
        }
    }*/



    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bj:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bj:user:info")
    public R info(@PathVariable("id") Integer id){
			BjUserEntity user = userService.selectById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bj:user:save")
    public R save(@RequestBody BjUserEntity user){
			userService.insert(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bj:user:update")
    public R update(@RequestBody BjUserEntity user){
			userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bj:user:delete")
    public R delete(@RequestBody Integer[] ids){
			userService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
