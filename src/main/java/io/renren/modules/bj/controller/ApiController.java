package io.renren.modules.bj.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.HttpUtil;
import io.renren.common.utils.R;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.bj.bjEntity.BjUser;
import io.renren.modules.bj.bjEntity.CarParking;
import io.renren.modules.bj.bjEntity.RfidInfo;
import io.renren.modules.bj.entity.*;
import io.renren.modules.bj.service.*;
import io.renren.modules.bj.util.Http;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static io.renren.common.utils.FileUtil.upLoadFromProduction;

@RestController
@RequestMapping("/bj/api/")
public class ApiController {

    @Autowired
    private BjUserService bjUserService;
    @Autowired
    private UserHistoryService historyService;
    @Autowired
    private JqbService jqbService;
    @Autowired
    private XgdxxService xgdxxService;
    @Autowired
    private GpsService gpsService;
    @Autowired
    private UserRfidService userRfidService;
    @Autowired
    private XgHistoryService xgHistoryService;
    @Autowired
    private SclbService sclbService;


    /**
     * 实现登录并写入到登录信息表和历史表
     * @param username
     * @param pwd
     * @return
     */
    @RequestMapping("login")
    @DataSource(name = DataSourceNames.SECOND)
    public R login(@RequestParam("username")String username, @RequestParam("pwd")String pwd){
        boolean login = false;
        //登录北京接口
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",pwd);
        map.put("grant_type","password");
        String post = HttpUtil.doPost(Http.token,map) == null?"":HttpUtil.doPost(Http.token,map);
        BjUser bjUser =new BjUser();
        JSONObject json = null;
        if (post != ""){
            json = JSONObject.fromObject(post);
        }
        if (json != null) {
            bjUser.setAccess_token(json.get("access_token").toString());
            bjUser.setToken_type(json.get("token_type").toString());
            bjUser.setUserName(json.get("userName").toString());
            bjUser.setExpires_in(Integer.parseInt(json.get("expires_in").toString()));
            login = true;
        }
        if (login && bjUser != null){
            UserRfidEntity userRfidEntity = userRfidService.selectOne(new EntityWrapper<UserRfidEntity>().where("name = {0}",bjUser.getUserName()));
            RfidInfo info = bjUserService.lastInfo(userRfidEntity.getRfid());
            //写数据到bj_user表
            BjUserEntity userEntity=new BjUserEntity();
            userEntity= bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where("rfid_device = {0}",info.getDevice()));
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

                userEntity.setCreateTime(new Date());
                userEntity.setName(bjUser.getUserName());
                userEntity.setLightSbbh(info.getSbbh());
                userEntity.setRfidCard(info.getRfidId());
                userEntity.setRfidDevice(info.getDevice());
                userEntity.setToken(bjUser.getAccess_token());
                bjUserService.insertOrUpdate(userEntity);
            }else{
                BjUserEntity userEntity1 = new BjUserEntity();
                userEntity1.setCreateTime(new Date());
                userEntity1.setName(bjUser.getUserName());
                userEntity1.setLightSbbh(info.getSbbh());
                userEntity1.setRfidCard(info.getRfidId());
                userEntity1.setRfidDevice(info.getDevice());
                userEntity1.setToken(bjUser.getAccess_token());
                bjUserService.insertOrUpdate(userEntity1);
            }
            return R.ok(bjUser.getAccess_token());
        }else {
            return R.error(201,"登录失败，账号或密码错误！");
        }
    }


    /**
     * 写入警情，并写入到北京接口
     */
    @RequestMapping("xrjq")
    @DataSource(name = DataSourceNames.SECOND)
    public R xrJq(@RequestBody JqbEntity jqbEntity,@RequestParam("token")String token){
        Map<String,Object> map = new HashMap();
        map.put("String1",jqbEntity.getSjmc());
        map.put("String2",jqbEntity.getSjnr());
        map.put("String5",jqbEntity.getJjr());
        map.put("Int1",Integer.parseInt(jqbEntity.getFhcphsl()));
        map.put("Int2",jqbEntity.getJjcd());
        map.put("Int5",jqbEntity.getStatus());
        map.put("Double1",jqbEntity.getLng());
        map.put("Double2",jqbEntity.getLat());
        map.put("DataTime",jqbEntity.getCreateTime());
        String id =bjjk(8,token,map,0);

        jqbEntity.setBjId(Integer.parseInt(id));
        jqbService.insert(jqbEntity);
        //JSONObject jsonObject = JSONObject.fromObject(jqbEntity);
        return R.ok();
    }

    /**
     * 写入巡更点信息
     */
    @RequestMapping("xrxg")
    @DataSource(name = DataSourceNames.SECOND)
    public R xrXgd(@RequestBody XgdxxEntity xgdxxEntity,@RequestParam("token")String token){
        Map<String,Object> map = new HashMap();
        map.put("String1",xgdxxEntity.getName());
        map.put("Int1",xgdxxEntity.getXgsc());
        map.put("Int5",xgdxxEntity.getIsUse());
        map.put("Double1",xgdxxEntity.getLng());
        map.put("Double2",xgdxxEntity.getLat());
        map.put("DataTime",xgdxxEntity.getCreateTime());

        String id =bjjk(11,token,map,0);
        xgdxxEntity.setBjId(Integer.parseInt(id));
        xgdxxService.insert(xgdxxEntity);

        return R.ok();
    }

    /**
     * 写入GPS信息
      */
    @RequestMapping("xrgps")
    @DataSource(name = DataSourceNames.SECOND)
    public R xrgps(@RequestBody GpsEntity gpsEntity,@RequestParam("token")String token){
        BjUserEntity bjUserEntity = bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where(" name = {0}",gpsEntity.getUsername()));
        gpsEntity.setSbbh(bjUserEntity.getLightSbbh());
        Map<String,Object> map = new HashMap();
        map.put("String1",gpsEntity.getSbbh());
        map.put("String2",gpsEntity.getCph());
        map.put("Int1",gpsEntity.getStatus());
        map.put("Double1",gpsEntity.getLng());
        map.put("Double2",gpsEntity.getLat());
        map.put("DataTime",gpsEntity.getCreateTime());

        String id =bjjk(12,token,map,0);
        gpsEntity.setBjId(Integer.parseInt(id));
        gpsService.insert(gpsEntity);
        return R.ok();
    }

    /**
     * 更新警情
     */
    @RequestMapping("gxjq")
    @DataSource(name = DataSourceNames.SECOND)
    public R gxJq(@RequestBody JqbEntity jqbEntity,@RequestParam("token")String token){
        JqbEntity entity = jqbService.selectOne(new EntityWrapper<JqbEntity>().where("bj_id = {0}",jqbEntity.getBjId()));
        if (entity != null &&entity.getBjId() == jqbEntity.getBjId()){
            jqbEntity.setId(entity.getId());
        }
        Map<String,Object> map = new HashMap();
        map.put("String1",jqbEntity.getSjmc());
        map.put("String2",jqbEntity.getSjnr());
        map.put("String5",jqbEntity.getJjr());
        map.put("Int1",Integer.parseInt(jqbEntity.getFhcphsl()));
        map.put("Int2",jqbEntity.getJjcd());
        map.put("Int5",jqbEntity.getStatus());
        map.put("Double1",jqbEntity.getLng());
        map.put("Double2",jqbEntity.getLat());
        map.put("DataTime",jqbEntity.getCreateTime());
        bjjk(8,token,map,jqbEntity.getBjId());
        jqbService.insertOrUpdate(jqbEntity); //,new EntityWrapper<JqbEntity>().where("bj_id = {0}",jqbEntity.getBjId())
        return R.ok();
    }

    /**
     * 更新巡更点
     */
    @RequestMapping("gxxg")
    @DataSource(name = DataSourceNames.SECOND)
    public R gxXgd(@RequestBody XgdxxEntity xgdxxEntity,@RequestParam("token")String token){
        Map<String,Object> map = new HashMap();
        map.put("String1",xgdxxEntity.getName());
        map.put("Int1",xgdxxEntity.getXgsc());
        map.put("Int5",xgdxxEntity.getIsUse());
        map.put("Double1",xgdxxEntity.getLng());
        map.put("Double2",xgdxxEntity.getLat());
        map.put("DataTime",xgdxxEntity.getCreateTime());
        bjjk(5,token,map,xgdxxEntity.getBjId());
        xgdxxService.update(xgdxxEntity,new EntityWrapper<XgdxxEntity>().where("bj_id = {0}",xgdxxEntity.getBjId()));
        return R.ok();
    }

    /**
     * 写入巡更记录
     */
    @RequestMapping("xrxgjl")
    @DataSource(name = DataSourceNames.SECOND)
    public R gxXgd(@RequestBody XgHistoryEntity xgHistoryEntity,@RequestParam("token")String token){
        Map<String,Object> map = new HashMap();
        map.put("String1",xgHistoryEntity.getName());
        map.put("String2",xgHistoryEntity.getXgr());
        BjUserEntity userEntity = bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where("name = {0}",xgHistoryEntity.getXgr()));
        map.put("String3",userEntity.getLightSbbh());
        map.put("Double1",xgHistoryEntity.getLng());
        map.put("Double2",xgHistoryEntity.getLat());
        map.put("DataTime",xgHistoryEntity.getXgTime());
        String id =bjjk(11,token,map,0);
        xgHistoryEntity.setBjId(Integer.parseInt(id));
        xgHistoryService.insert(xgHistoryEntity);
        return R.ok();
    }

    /**
     * 将北京获取的json传入，获取id并保存到自己的系统
     * @param json
     * @return
     */
    public Integer getId(String json){
        JSONObject jsonObject = JSONObject.fromObject(json);
        return Integer.parseInt(jsonObject.get("Id").toString());
    }


    /**
     * 获取北京接口数据
     * map NULL获取列表   有数据获取插入的ID
     */
    @RequestMapping("bjjk")
    public String bjjk(@RequestParam("code") Integer code,@RequestParam("token") String token,Map map,@RequestParam("num") Integer num){
        String post = "";
        switch (code){
            case 1:
                post = Http.MyData001;
                break;
            case 2:
                post = Http.MyData002;
                break;
            case 3:
                post = Http.MyData003;
                break;
            case 4:
                post = Http.MyData004;
                break;
            case 5:
                post = Http.MyData005;
                break;
            case 6:
                post = Http.MyData006;
                break;
            case 7:
                post = Http.MyData007;
                break;
            case 8:
                post = Http.MyData008;
                break;
            case 11:
                post = Http.MyData011;
                break;
            case 12:
                post = Http.MyData012;
                break;
        }
        String out = "";
        //查找北京列表
        if (map == null && num == 0 || map.size() == 0 && num == 0){
            out = HttpUtil.doGet1(post,token);
            //System.out.print(out);
        }
        //写入北京列表
        else if (map != null && num == 0 || map.size() != 0 && num == 0){
            try {
                //JSONObject jsonObject = JSONObject.fromObject(map);
                //out = HttpUtil.doPost2(post,jsonObject.toString(),token);
                out = postDo(post,map,token);
                out = getId(out).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //更新北京列表单个数据
        else if (map != null && num != 0){
            post = post + "("+num+")";
            try {
                map.put("Id",num);
                putDo(post,map,token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //
        else {
            return "0";
        }
        return  out;
    }

    public String postDo(String post,Map map,String token){
        JSONObject jsonObject = JSONObject.fromObject(map);
        return HttpUtil.doPost2(post,jsonObject.toString(),token);
    }

    public void putDo(String post,Map map,String token){
        JSONObject jsonObject = JSONObject.fromObject(map);
        HttpUtil.doPut(post,jsonObject.toString(),token);
    }


    /**
     * 查询个人信息
     * @param username 用户名
     * @return
     */
    @RequestMapping("cxgrxx")
    @DataSource(name = DataSourceNames.SECOND)
    public R cxgrxx(@RequestParam("username")String username){
        BjUserEntity bjUserEntity = bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where("name = {0}",username));
        Map map =new HashMap();
        map.put("name",bjUserEntity.getName());
        map.put("sbbh",bjUserEntity.getLightSbbh());
        map.put("rfid",bjUserEntity.getRfidCard());
        map.put("time",bjUserEntity.getCreateTime());
        map.put("cph",bjUserEntity.getCph());
        return  R.ok(map);
    }


    @RequestMapping("cxtjxx")
    @DataSource(name = DataSourceNames.SECOND)
    public R cxgrxx(@RequestParam("name")String name,@RequestParam("jjsj")String date){
        int xgzs = xgHistoryService.xgzs(name,date);
        int xgws = xgHistoryService.xgws(name,date);
        int jjsl = jqbService.cxjjsl(name,date);
        Map map = new HashMap();
        map.put("xgzs",xgzs);
        map.put("xgws",xgws);
        map.put("jjsl",jjsl);
        map.put("wzzs",0);
        return R.ok(map);
    }

    @RequestMapping("scsjjk")
    @DataSource(name = DataSourceNames.SECOND)
    public R scsjjk() {
        List<CarParking> carParkings1 = sclbService.bjsc1();
        List<CarParking> carParkings2 = sclbService.bjsc2();
        List<CarParking> carParkings3 = sclbService.bjsc3();
        List<Integer> list = new ArrayList<Integer>();


        for (int i = 0; i < carParkings1.size(); i++) {
            BjUserEntity bjUserEntity = bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where("light_sbbh = {0}", carParkings1.get(i).getSbbh()));
            if(bjUserEntity == null){
                continue;
            }
            Map<String, Object> map = new HashMap();
            map.put("String1", carParkings1.get(i).getHphm());
            String Time = carParkings1.get(i).getWfsj().substring(0, 10) + "T" + carParkings1.get(i).getWfsj().substring(11);
            map.put("String2", bjUserEntity.getName());
            map.put("DataTime", Time);
            bjjk(1, bjUserEntity.getToken(), map, 0);
        }

        for (int i = 0; i < carParkings2.size(); i++) {
            BjUserEntity bjUserEntity = bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where("light_sbbh = {0}", carParkings2.get(i).getSbbh()));
            if(bjUserEntity == null){
                continue;
            }
            Map<String, Object> map = new HashMap();
            map.put("String1", carParkings2.get(i).getHphm());
            String Time = carParkings2.get(i).getWfsj().substring(0, 10) + "T" + carParkings2.get(i).getWfsj().substring(11);
            map.put("String2", bjUserEntity.getName());
            map.put("Int1",1);
            map.put("DataTime", Time);
            bjjk(2, bjUserEntity.getToken(), map, 0);
        }

        for (int i = 0; i < carParkings3.size(); i++) {
            BjUserEntity bjUserEntity = bjUserService.selectOne(new EntityWrapper<BjUserEntity>().where("light_sbbh = {0}", carParkings3.get(i).getSbbh().toString()));
            if(bjUserEntity == null){
                SclbEntity sclbEntity = new SclbEntity();
                sclbEntity.setYcId(carParkings3.get(i).getId());
                sclbService.insert(sclbEntity);
                continue;
            }
            Map<String, Object> map = new HashMap();
            map.put("String1", carParkings3.get(i).getHphm());
            String Time = carParkings3.get(i).getWfsj().substring(0, 10) + "T" + carParkings3.get(i).getWfsj().substring(11);
            map.put("String2", bjUserEntity.getLightSbbh());
            //ftp地址，这边需要进行图片处理

            String path="C:/wamp/Apache24/htdocs/car_forensics/public/";
            String filepath1 = carParkings3.get(i).getZpstr1();
            String filepath2 = carParkings3.get(i).getZpstr2();
            String filepath3 = carParkings3.get(i).getZpstr3();
            String string3 = "";
            if ( filepath1 != null){
                upLoadFromProduction("img001.cueb.vip", 21, "img001", "cueb2018", "", filepath1.substring(7,17)+"-"+filepath1.substring(51,59)+"_"+filepath1.substring(60), path+"carParkings1.get(i).getZpstr1()");
                string3 = filepath1.substring(7,17)+"-"+filepath1.substring(51,59)+"_"+filepath1.substring(60)+";";
            }
            if (filepath2 != null){
                upLoadFromProduction("img001.cueb.vip", 21, "img001", "cueb2018", "", filepath2.substring(7,17)+"-"+filepath2.substring(51,59)+"_"+filepath2.substring(60), path+"carParkings1.get(i).getZpstr2()");
                string3 += filepath2.substring(7,17)+"-"+filepath2.substring(51,59)+"_"+filepath2.substring(60)+";";
            }
            if (filepath3 != null){
                upLoadFromProduction("img001.cueb.vip", 21, "img001", "cueb2018", "", filepath3.substring(7,17)+"-"+filepath3.substring(51,59)+"_"+filepath3.substring(60), path+"carParkings1.get(i).getZpstr3()");
                string3 += filepath3.substring(7,17)+"-"+filepath3.substring(51,59)+"_"+filepath3.substring(60);
            }
            //图片名称；间隔
            map.put("String3",string3);
            map.put("String5", bjUserEntity.getName());
            map.put("Double1",Double.parseDouble(carParkings3.get(i).getLng().substring(1)));
            map.put("Double2",Double.parseDouble(carParkings3.get(i).getLat().substring(1)));
            map.put("DataTime", Time);
            bjjk(3, bjUserEntity.getToken(), map, 0);

            //将本列表的数据写入到已存在表，避免重复插入
            SclbEntity sclbEntity = new SclbEntity();
            sclbEntity.setYcId(carParkings3.get(i).getId());
            sclbService.insert(sclbEntity);
        }
        return R.ok();
    }

}
