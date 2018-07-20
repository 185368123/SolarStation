package com.shuorigf.solarstaition.data.params.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class DeviceSaveSettingParams {

    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public String type;
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
    @SerializedName("floating_charge_vol")
    public String floatingChargeVol;
    @SerializedName("promote_recover_vol")
    public String promoteRecoverVol;
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
    @SerializedName("load_work_mode")
    public String loadWorkMode;
    @SerializedName("delay_time")
    public String delayTime;
    @SerializedName("optically_vol")
    public String opticallyVol;
    @SerializedName("work_mode")
    public String workMode;
    @SerializedName("ac_input_min_vol")
    public String acInputMinVol;
    @SerializedName("ac_input_max_vol")
    public String acInputMaxVol;
    @SerializedName("ivt_output_max_pow")
    public String ivtOutputMaxPow;
    @SerializedName("output_vol")
    public String outputVol;
    @SerializedName("output_freq")
    public String outputFreq;
    @SerializedName("output_pow_saving_mode")
    public String outputPowSavingMode;
    @SerializedName("elect_charge_cur")
    public String electChargeCur;
    @SerializedName("elect_switch_point_vol")
    public String electSwitchPointVol;
    @SerializedName("elect_switch_recover_vol")
    public String electSwitchRecoverVol;

    /**
     *
     * @param id 设备id
     * @param type 类型(0蓄电池参数, 1负载参数, 2逆变器参数)
     * @param capacity 标称容量
     * @param sysVol 系统电压
     * @param batteryType 蓄电池类型(0自定义，1开口，2密封，3胶体，4锂电池)
     * @param overVol 超压电压
     * @param limitedChargeVol 充电限制电压
     * @param balanceChargeVol 均衡充电电压
     * @param promoteChargeVol 提升充电电压
     * @param floating_charge_vol 浮充充电电压
     * @param promote_recover_vol 提升充电返回电压
     * @param overDischargeRecoverVol 过放返回电压
     * @param underVoltageWarnVol 欠压警告电压
     * @param overDischargeVol 过放电压
     * @param limitedDischargeVol 放电限制电压
     * @param overDischargeTime 过放延时时间
     * @param chargeAbortSoc 充电截止SOC
     * @param dischargeAbortSoc 放电截止SOC
     * @param balanceChargeTime 均衡充电时间
     * @param promoteChargeTime 提升充电时间
     * @param balanceInterval 均衡充电间隔
     * @param tempCompensation 温度补偿系数
     * @param loadWorkMode 负载工作模式
     * @param delayTime 光控延时时间
     * @param opticallyVol 光控电压
     * @param workMode 工作模式(0太阳能优先，1智能充电，2放电模式)
     * @param acInputMinVol AC允许输入最低电压
     * @param acInputMaxVol AC允许输入最高电压
     * @param ivtOutputMaxPow 逆变器最大输出功率
     * @param outputVol 输出电压
     * @param outputFreq 输出频率
     * @param outputPowSavingMode 输出省电模式
     * @param electChargeCur 市电充电电流
     * @param electSwitchPointVol 市电切换点电压
     * @param electSwitchRecoverVol 市电切换恢复电压
     */
}
