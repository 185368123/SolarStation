package com.shuorigf.solarstaition.data.response.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/3/14.
 */

public class ReadSettingInfo {

    /**
     * capacity : 60
     * sys_vol : 800
     * battery_type : 0
     * over_vol : 38
     * limited_charge_vol : 70
     * balance_charge_vol : 80
     * promote_charge_vol : 1
     * over_discharge_recover_vol : 16
     * under_voltage_warn_vol : 38
     * over_discharge_vol : 80
     * limited_discharge_vol : 90
     * over_discharge_time : 70
     * charge_abort_soc : 760
     * discharge_abort_soc : 0
     * balance_charge_time : 60
     * promote_charge_time : 80
     * balance_interval : 80
     * temp_compensation : 1
     */

    @SerializedName("capacity")
    public String capacity;
    @SerializedName("sys_vol")
    public String sysVol;
    @SerializedName("battery_type")
    public String batteryType;
    @SerializedName("over_vol")
    public String overVol;
    @SerializedName("limited_charge_vol")
    public String limitedChargeVol;
    @SerializedName("balance_charge_vol")
    public String balanceChargeVol;
    @SerializedName("promote_charge_vol")
    public String promoteChargeVol;
    @SerializedName("over_discharge_recover_vol")
    public String overDischargeRecoverVol;
    @SerializedName("under_voltage_warn_vol")
    public String underVoltageWarnVol;
    @SerializedName("over_discharge_vol")
    public String overDischargeVol;
    @SerializedName("limited_discharge_vol")
    public String limitedDischargeVol;
    @SerializedName("over_discharge_time")
    public String overDischargeTime;
    @SerializedName("charge_abort_soc")
    public String chargeAbortSoc;
    @SerializedName("discharge_abort_soc")
    public String dischargeAbortSoc;
    @SerializedName("balance_charge_time")
    public String balanceChargeTime;
    @SerializedName("promote_charge_time")
    public String promoteChargeTime;
    @SerializedName("balance_interval")
    public String balanceInterval;
    @SerializedName("temp_compensation")
    public String tempCompensation;
}
