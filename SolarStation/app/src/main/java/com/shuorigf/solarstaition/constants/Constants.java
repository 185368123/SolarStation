package com.shuorigf.solarstaition.constants;

/**
 * Created by clx on 2017/9/28.
 */

public class Constants {

    // HTTP
    public static final long HTTP_CONNECT_TIMEOUT = 15000L;// HTTP连接超时时间

    public static final long HTTP_READ_TIMEOUT = 15000L;// HTTP读取超时时间

    public static final int HTTP_MAX_CONNECT_COUNT = 10;// HTTP最大连接数

    public static final long HTTP_KEEP_ALIVE_CONNECT_COUNT = 5;// HTTP保持KeepAlive的连接数

    //countdown
    public static final int DEFAULT_GET_VERIFY_COUNT_TIME = 60;

    //intent key
    public static final String POWER_CHART_TYPE = "power_chart_type";
    public static final String DIALOG_MENU_TYPE = "dialog_menu_type";
    public static final String REAL_TIME_CURVE_CHART_TYPE = "real_time_curve_chart_type";
    public static final String STATION_ID = "station_id";
    public static final String STATION_NAME = "station_name";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_NAME = "device_name";
    public static final String SELECT_STATION = "select_station";
    public static final String DEVICE_INFO = "device_info";
    public static final String INFORMATION_CHANGE = "information_change";

    //request code
    public static final int REQUEST_CODE_SELECT_STATION = 100;

}
