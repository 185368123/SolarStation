package com.shuorigf.solarstaition.constants;

import android.text.TextUtils;

import com.shuorigf.solarstaition.BuildConfig;
import com.shuorigf.solarstaition.data.response.login.UserInfo;


/**
 * auther: chenlixin on 17/11/15.
 * mail: lixin.chen@9leaf.com
 */

public class ApiConstants {





    public final static String DEFAULT_WEB_ROOT = "http://station-iot.com/";// 默认网点
    public final static String API_VERSION = "api/";
    public final static String IMAGE_URL = "http://station-iot.com";// 图片网址前缀
    public static UserInfo mUserInfo;

    //user
    private final static String USER_PATH = "user/";
    public final static String USER_LOGIN = USER_PATH + "login";//登录


    //home
    private final static String HOME_PATH = "home/";
    public final static String HOME_DATA = HOME_PATH + "data";//首页数据

    //project
    private final static String PROJECT_PATH = "project/";
    public final static String PROJECT_DATA = PROJECT_PATH + "data";//项目数据
    public final static String PROJECT_GET = PROJECT_PATH + "get";//项目列表
    public final static String PROJECT_SAVE = PROJECT_PATH + "save";//添加或修改项目
    public final static String PROJECT_DEL = PROJECT_PATH + "del";//删除项目

    //station
    private final static String STATION_PATH = "station/";
    public final static String STATION_DATA = STATION_PATH + "data";//电站数据
    public final static String STATION_GET = STATION_PATH + "get";//电站列表
    public final static String STATION_SAVE = STATION_PATH + "save";//添加或修改电站
    public final static String STATION_DETAIL = STATION_PATH + "detail";//电站详情
    public final static String STATION_GET_POWER_LOG = STATION_PATH + "get_power_log";//查询充电/放电功率日志
    public final static String STATION_DEL = STATION_PATH + "del";//删除电站


    //common
    private final static String COMMON_PATH = "common/";
    public final static String COMMON_LOGIN = COMMON_PATH + "login";//登录
    public final static String COMMON_UPLOAD = COMMON_PATH + "upload";// 上传图片
    public final static String COMMON_GET_PANEL_TYPE = COMMON_PATH + "get_panel_type";// 获取电池板类型
    public final static String COMMON_GET_BATTERY_TYPE = COMMON_PATH + "get_battery_type";// 获取蓄电池类型
    public final static String COMMON_GET_LOAD_TYPE = COMMON_PATH + "get_load_type";// 获取负载类型
    public final static String COMMON_GET_COUNTRY = COMMON_PATH + "get_country";// 获取国家列表
    public final static String COMMON_GET_PROVINCE = COMMON_PATH + "get_province";// 获取省份列表
    public final static String COMMON_GET_LOAD_WORK_MODE = COMMON_PATH + "get_load_work_mode";// 获取负载工作模式

    //device
    private final static String DEVICE_PATH = "device/";
    public final static String DEVICE_GET = DEVICE_PATH + "get";// 查询设备列表
    public final static String DEVICE_GET_PATROL_INTERVAL = DEVICE_PATH + "get_patrol_interval";// 获取巡检时间间隔
    public final static String DEVICE_SET_PATROL_INTERVAL = DEVICE_PATH + "set_patrol_interval";// 设置巡检时间间隔
    public final static String DEVICE_SYNC_DATA = DEVICE_PATH + "sync_data";// 同步数据
    public final static String DEVICE_UPDATE = DEVICE_PATH + "update";// 升级设备
    public final static String DEVICE_DEL = DEVICE_PATH + "del";// 删除设备
    public final static String DEVICE_SAVE = DEVICE_PATH + "save";// 添加/修改设备
    public final static String DEVICE_GET_REAL_TIME_DATA = DEVICE_PATH + "get_real_time_data";// 获取设备实时监控数据
    public final static String DEVICE_GET_DATA_LOG_CHART = DEVICE_PATH + "get_data_log_chart";// 设备运行日志(实时曲线)
    public final static String DEVICE_GET_DATA_LOG_TABLE = DEVICE_PATH + "get_data_log_table";// 获取设备数据明细
    public final static String DEVICE_EXPORT_TO_EXCEL = DEVICE_PATH + "export_to_excel";// 导出excel
    public final static String DEVICE_READ_SETTING = DEVICE_PATH + "read_setting";// 读取设备参数
    public final static String DEVICE_SAVE_SETTING = DEVICE_PATH + "save_setting";// 修改设备参数

    //alarm
    private final static String ALARM_PATH = "alarm/";
    public final static String ALARM_GET_NEW_MSG_COUNT = ALARM_PATH + "get_new_msg_count";// 获取新报警信息数
    public final static String ALARM_GET = ALARM_PATH + "get";// 获取报警信息列表/搜索报警信息






    public static String getBaseApiUrl() {
        String host;
        if (!TextUtils.isEmpty(BuildConfig.HOST)) {
            host = BuildConfig.HOST;
        } else {
            host = DEFAULT_WEB_ROOT;
        }
        return host + API_VERSION;
    }

    public static String getBaseImageUrl() {
        String host;
        if (!TextUtils.isEmpty(BuildConfig.HOST)) {
            host = BuildConfig.HOST;
        } else {
            host = DEFAULT_WEB_ROOT;
        }
        return host;
    }

    public static String getImageUrl(String path) {
        if (TextUtils.isEmpty(path)) return getBaseImageUrl();
        return getBaseImageUrl() + path;
    }

    public static UserInfo getUserInfo() {
        if (mUserInfo == null) {
            mUserInfo = new UserInfo();
        }
        return mUserInfo;
    }
}
