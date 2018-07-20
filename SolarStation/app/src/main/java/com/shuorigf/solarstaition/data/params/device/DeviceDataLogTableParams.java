package com.shuorigf.solarstaition.data.params.device;

import com.google.gson.annotations.SerializedName;
import com.shuorigf.solarstaition.data.params.PageParams;

/**
 * auther: chenlixin on 18/1/23.
 */

public class DeviceDataLogTableParams{
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String DATE = "date";

    public static final String TYPE_ALL = "0";
    public static final String TYPE_STORAGE_BATTERY = "1";
    public static final String TYPE_BATTERY_BOARD = "2";
    public static final String TYPE_LOAD = "3";


    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public String type;
    @SerializedName("date")
    public String date;

    public PageParams pageParams;


    public DeviceDataLogTableParams() {
    }

    /**
     *
     * @param id 设备id
     * @param type  类型(0所有参数, 1蓄电池, 2电池板, 3负载)
     * @param date 日期(格式2017-12-12)
     * @param pageParams 分页参数
     */
    public DeviceDataLogTableParams(String id, String type, String date, PageParams pageParams) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.pageParams = pageParams;
    }
}
