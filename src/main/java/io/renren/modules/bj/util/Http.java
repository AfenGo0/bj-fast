package io.renren.modules.bj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public  class Http {

    /**
     * 验证登录
      */
    public static String token="http://118.26.168.81/token";

    /**
     *问题车辆
     * String1 问题车辆车牌号
     * String2 采集用户
     * DataTime 录入时间
     */
    public static String MyData001="http://118.26.168.81/odata/MyData001";

    /**
     *过车车牌号
     * String1 回传车牌号，用英文,隔开
     * Int001 回传车牌号数量
     * DataTime 录入时间
     */
    public static String MyData002="http://118.26.168.81/odata/MyData002";

    /**
     * String1 回传车牌号
     * String2 回传设备信息
     * String3 回传图片名称；间隔
     * String4 描述信息
     * String5 回传人
     * Double1 116 经纬度
     * Double2 40  经纬度
     * DataTime 录入时间
     */
    public static String MyData003="http://118.26.168.81/odata/MyData003";

    /**
     *
     */
    public static String MyData004="http://118.26.168.81/odata/MyData004";

    /**
     *巡更点信息
     *String1 点名称
     * Int1 巡更时长   单位：分
     * Int5 是否启用   1启用
     * Double1 116 经纬度
     * Double2 40  经纬度
     * DataTime 创建时间a
     */
    public static String MyData005="http://118.26.168.81/odata/MyData005";

    /**
     *
     */
    public static String MyData006="http://118.26.168.81/odata/MyData006";

    /**
     *
     */
    public static String MyData007="http://118.26.168.81/odata/MyData007";

    /**
     * 警情
     *String1 事件名称
     * String2 事件内容
     * String5 接警人
     * Int1 返回车牌号数量
     * Int2 紧急程度  1紧急  2一般 3不急
     * Int5 状态 0未接  1进行中  2完成  3到达，处理中
     * Double1 116 经纬度
     * Double2 40  经纬度
     * DataTime 创建时间
     */
    public static String MyData008="http://118.26.168.81/odata/MyData008";

    /**
     *巡更打卡记录
     * String1 巡更点名称
     * String2 巡更人
     * String3 巡更车辆
     * Double1 经纬度
     * Double2 经纬度
     * DateTime 打卡时间
     */
    public static String MyData011="http://118.26.168.81/odata/MyData011";

    /**
     *GPS实时位置记录
     * Double1 经纬度
     * Double2 经纬度
     * String1 警灯编号
     * String2 车牌号
     * Int1 当前状态 停驶，巡逻，处警
     * DateTime 当前时间
     */
    public static String MyData012="http://118.26.168.81/odata/MyData012";



}
