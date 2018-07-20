package com.shuorigf.solarstaition.data.response.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class RealTimeDataInfo {
    public static final int STATUS_BATTERY_NORMAL = 0;
    public static final int STATUS_BATTERY_LOW_VOLTAGE_DISCONNECT = 1;
    public static final int STATUS_BATTERY_UNDER_VOLTAGE_ALARM = 2;
    public static final int STATUS_BATTERY_OVER_VOLTAGE_DISCONNECT = 3;
    public static final int STATUS_BATTERY_BATTERY_THERMAL = 4;

    public static final int STATUS_BATTERY_CHARGE_UNOPENED_CHARGE = 0;
    public static final int STATUS_BATTERY_CHARGE_START_CHARGING_MODE = 1;
    public static final int STATUS_BATTERY_CHARGE_MPPT_CHARGING_MODE = 2;
    public static final int STATUS_BATTERY_CHARGE_BALANCED_CHARGING_MODE = 3;
    public static final int STATUS_BATTERY_CHARGE_UPGRADE_CHARGING_MODE = 4;
    public static final int STATUS_BATTERY_CHARGE_FLOAT_CHARGING_MODE = 5;
    public static final int STATUS_BATTERY_CHARGE_CURRENT_LIMITING = 6;

    public static final int STATUS_PANEL_PV_OVER_VOLTAGE = 0;
    public static final int STATUS_PANEL_PV_REVERSE = 1;
    public static final int STATUS_PANEL_PV_OVER_POWER = 2;

    public static final int STATUS_LOAD_CLOSE = 0;
    public static final int STATUS_LOAD_OPEN = 1;
    public static final int STATUS_LOAD_OVER_LOAD = 2;
    public static final int STATUS_LOAD_SHORT_CIRCUIT = 3;

    public static final int STATUS_DEVICE_ABNORMAL = 0;
    public static final int STATUS_DEVICE_NORMAL = 1;
    public static final int STATUS_DEVICE_OVER_TEMPERATURE = 2;
    public static final int STATUS_DEVICE_LOW_TEMPERATURE = 3;

    /**
     * battery_soc : 60
     * battery_vol : 800
     * battery_chg_cur : 80
     * battery_temp : 38
     * battery_max_vol : 70
     * battery_min_vol : 80
     * battery_sts : 1 蓄电池状态(0:正常,1:低压断开,2:欠压告警,3:超压断开,4:电池过温)
     * battery_chg_sts : 16 充电状态(0:未开启充电,1:启动充电模式,2:mppt充电模式,3:均衡充电模式,4:提升充电模式,5:浮充充电模式,6:限流(超功率))
     * panel_vol : 38
     * panel_cur : 80
     * panel_day_max_chg_cur : 90
     * panel_day_max_chg_pow : 70
     * panel_chg_pow : 760
     * panel_sts : 0 电池板状态(0:PV超压,1:PV接反,2:PV超功率)
     * load_dc_vol : 60
     * load_dc_cur : 80
     * load_dc_pow : 80
     * load_dc_sts : 1 交流负载状态(0关, 1开, 2过载, 3短路)
     * load_ac_vol : 90
     * load_ac_cur : 87
     * load_ac_pow : 40
     * load_ac_opt_fqc : 30
     * load_ac_sts : 1 交流负载状态(0关, 1开, 2过载, 3短路)
     * temp : 80
     * status : 1 设备状态(0:异常, 1正常, 2超温, 3低温)
     */

    @SerializedName("battery_soc")
    public String batterySoc;
    @SerializedName("battery_vol")
    public String batteryVol;
    @SerializedName("battery_chg_cur")
    public String batteryChgCur;
    @SerializedName("battery_temp")
    public String batteryTemp;
    @SerializedName("battery_max_vol")
    public String batteryMaxVol;
    @SerializedName("battery_min_vol")
    public String batteryMinVol;
    @SerializedName("battery_sts")
    public int batterySts;
    @SerializedName("battery_chg_sts")
    public int batteryChgSts;
    @SerializedName("panel_vol")
    public String panelVol;
    @SerializedName("panel_cur")
    public String panelCur;
    @SerializedName("panel_day_max_chg_cur")
    public String panelDayMaxChgCur;
    @SerializedName("panel_day_max_chg_pow")
    public String panelDayMaxChgPow;
    @SerializedName("panel_chg_pow")
    public String panelChgPow;
    @SerializedName("panel_sts")
    public int panelSts;
    @SerializedName("load_dc_vol")
    public String loadDcVol;
    @SerializedName("load_dc_cur")
    public String loadDcCur;
    @SerializedName("load_dc_pow")
    public String loadDcPow;
    @SerializedName("load_dc_sts")
    public int loadDcSts;
    @SerializedName("load_ac_vol")
    public String loadAcVol;
    @SerializedName("load_ac_cur")
    public String loadAcCur;
    @SerializedName("load_ac_pow")
    public String loadAcPow;
    @SerializedName("load_ac_opt_fqc")
    public String loadAcOptFqc;
    @SerializedName("load_ac_sts")
    public int loadAcSts;
    @SerializedName("temp")
    public String temp;
    @SerializedName("status")
    public int status;
}
