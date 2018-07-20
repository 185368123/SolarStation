package com.shuorigf.solarstaition.data.params.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class DeviceDataLogChartParams {
    public static final String TYPE_STORAGE_BATTERY_VOLTAGE = "0";
    public static final String TYPE_CHARGING_CURRENT = "1";
    public static final String TYPE_BATTERY_TEMPERATURE = "2";
    public static final String TYPE_LOAD_POWER = "3";
    public static final String TYPE_SOLAR_PANEL_VOLTAGE = "4";
    public static final String TYPE_CHARGING_POWER = "5";
    public static final String TYPE_GENERATING_CAPACITY = "6";

    public static final String DATE_TYPE_ALL = "0";
    public static final String DATE_TYPE_YEAR = "1";
    public static final String DATE_TYPE_MONTH = "2";
    public static final String DATE_TYPE_DAY = "3";

    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String DATE_TYPE = "date_type";
    public static final String DATE = "date";

    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public String type;
    @SerializedName("date_type")
    public String dateType;
    @SerializedName("date")
    public String date;

    public DeviceDataLogChartParams() {
    }

    /**
     *
     * @param id 设备id
     * @param type 类型(0蓄电池电压, 1充电电流, 2蓄电池温度, 3负载功率, 4太阳能板电压, 5充电功率, 6发电量)
     * @param dateType 日期类型(0全部, 1年, 2月, 3日)
     * @param date 日期(date_type为全部时可传空值或不传，年月日格式参考2017、2017-12、2017-12-12)
     */
    public DeviceDataLogChartParams(String id, String type, String dateType, String date) {
        this.id = id;
        this.type = type;
        this.dateType = dateType;
        this.date = date;
    }
}
