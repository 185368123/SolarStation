package com.shuorigf.solarstaition.data.params.station;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */
public class StationSaveParams {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PROJECT_ID = "project_id";
    public static final String INSTALL_TIME = "install_time";
    public static final String DESIGNER = "designer";
    public static final String PHOTO = "photo";

    public static final String PANEL_POWER = "panel_power";
    public static final String PANEL_TYPE = "panel_type";
    public static final String PANEL_SERIES_COUNT = "panel_series_count";
    public static final String PANEL_PARALLEL_COUNT = "panel_parallel_count";

    public static final String PANEL_COUNT = "panel_count";
    public static final String PANEL_VOLTAGE = "panel_voltage";
    public static final String PANEL_CURRENT = "panel_current";
    public static final String PANEL_SINGLE_POWER = "panel_single_power";
    public static final String PANEL_SINGLE_VMP = "panel_single_vmp";
    public static final String PANEL_SINGLE_VOC = "panel_single_voc";
    public static final String PANEL_SINGLE_IMP = "panel_single_imp";
    public static final String PANEL_SINGLE_ISC = "panel_single_isc";
    public static final String PANEL_REMARK = "panel_remark";

    public static final String BATTERY_TYPE = "battery_type";
    public static final String BATTERY_SINGLE_VOLTAGE = "battery_single_voltage";
    public static final String BATTERY_SINGLE_CAPACITY = "battery_single_capacity";
    public static final String BATTERY_SERIES_COUNT = "battery_series_count";
    public static final String BATTERY_PARALLEL_COUNT = "battery_parallel_count";
    public static final String BATTERY_COUNT = "battery_count";
    public static final String BATTERY_VOLTAGE = "battery_voltage";
    public static final String BATTERY_CAPACITY = "battery_capacity";
    public static final String BATTERY_REMARK = "battery_remark";

    public static final String LOAD_TYPE = "load_type";
    public static final String LOAD_POWER = "load_power";
    public static final String COUNTRY_ID = "country_id";
    public static final String PROVINCE_ID = "province_id";
    public static final String ADDRESS = "address";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String ALTITUDE = "altitude";
    public static final String MAX_ANNUAL_TEMPER = "max_annual_temper";
    public static final String MIN_ANNUAL_TEMPER = "min_annual_temper";
    public static final String GEO_REMARK = "geo_remark";

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("project_id")
    public String projectId;
    @SerializedName("install_time")
    public String installTime;
    @SerializedName("designer")
    public String designer;
    @SerializedName("photo")
    public String photo;
    @SerializedName("panel_power")
    public String panelPower;
    @SerializedName("panel_type")
    public String panelType;
    @SerializedName("panel_series_count")
    public String panelSeriesCount;
    @SerializedName("panel_parallel_count")
    public String panelParallelCount;
    @SerializedName("panel_count")
    public String panelCount;
    @SerializedName("panel_voltage")
    public String panelVoltage;
    @SerializedName("panel_current")
    public String panelCurrent;
    @SerializedName("panel_single_power")
    public String panelSinglePower;
    @SerializedName("panel_single_vmp")
    public String panelSingleVmp;
    @SerializedName("panel_single_voc")
    public String panelSingleVoc;
    @SerializedName("panel_single_imp")
    public String panelSingleImp;
    @SerializedName("panel_single_isc")
    public String panelSingleIsc;
    @SerializedName("panel_remark")
    public String panelRemark;
    @SerializedName("battery_type")
    public String batteryType;
    @SerializedName("battery_single_voltage")
    public String batterySingleVoltage;
    @SerializedName("battery_single_capacity")
    public String batterySingleCapacity;
    @SerializedName("battery_series_count")
    public String batterySeriesCount;
    @SerializedName("battery_parallel_count")
    public String batteryParallelCount;
    @SerializedName("battery_count")
    public String batteryCount;
    @SerializedName("battery_voltage")
    public String batteryVoltage;
    @SerializedName("battery_capacity")
    public String batteryCapacity;
    @SerializedName("battery_remark")
    public String batteryRemark;
    @SerializedName("load_type")
    public String loadType;
    @SerializedName("load_power")
    public String loadPower;
    @SerializedName("country_id")
    public String countryId;
    @SerializedName("province_id")
    public String provinceId;
    @SerializedName("address")
    public String address;
    @SerializedName("longitude")
    public String longitude;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("altitude")
    public String altitude;
    @SerializedName("max_annual_temper")
    public String maxAnnualTemper;
    @SerializedName("min_annual_temper")
    public String minAnnualTemper;
    @SerializedName("geo_remark")
    public String geoRemark;

    public StationSaveParams() {
    }

    /**
     *
     * @param id 电站ID(传0或不传则为添加)
     * @param name 电站名称
     * @param projectId 所属项目ID
     * @param installTime 安装日期
     * @param designer 设计厂商
     * @param photo 电站照片
     * @param panelPower 电池板总功率
     * @param panelType 电池板类型(0单晶硅，1多晶硅，2非晶硅，3其他)
     * @param panelSeriesCount 电池板串联数量
     * @param panelParallelCount 电池板并联数量
     * @param panelCount 电池板总数量
     * @param panelVoltage 电池板总开路电压
     * @param panelCurrent 电池板总工作电流
     * @param panelSinglePower 单块电池板功率
     * @param panelSingleVmp 单块电池板vmp
     * @param panelSingleVoc 单块电池板voc
     * @param panelSingleImp 单块电池板imp
     * @param panelSingleIsc 单块电池板isc
     * @param panelRemark 电池板信息备注
     * @param batteryType 蓄电池类型(0自定义，1开口，2密封，3胶体，4锂电池)
     * @param batterySingleVoltage 单节电池电压
     * @param batterySingleCapacity 单节电池电量
     * @param batterySeriesCount 电池串联数量
     * @param batteryParallelCount 电池并联数量
     * @param batteryCount 电池总数量
     * @param batteryVoltage 电池总电压
     * @param batteryCapacity 电池总电量
     * @param batteryRemark 电池信息备注
     * @param loadType 负载类型(0直流，1交流，2直流+交流)
     * @param loadPower 负载功率
     * @param countryId 国家id
     * @param provinceId 省份id
     * @param address 详细地址
     * @param longitude 经度
     * @param latitude 纬度
     * @param altitude 海拔
     * @param maxAnnualTemper 最高年气温
     * @param minAnnualTemper 最低年气温
     * @param geoRemark 地理信息备注
     */
    public StationSaveParams(String id, String name, String projectId, String installTime, String designer, String photo,
                             String panelPower, String panelType, String panelSeriesCount, String panelParallelCount,
                             String panelCount, String panelVoltage, String panelCurrent, String panelSinglePower,
                             String panelSingleVmp, String panelSingleVoc, String panelSingleImp, String panelSingleIsc,
                             String panelRemark, String batteryType, String batterySingleVoltage, String batterySingleCapacity,
                             String batterySeriesCount, String batteryParallelCount, String batteryCount, String batteryVoltage,
                             String batteryCapacity, String batteryRemark, String loadType, String loadPower, String countryId,
                             String provinceId, String address, String longitude, String latitude, String altitude, String maxAnnualTemper,
                             String minAnnualTemper, String geoRemark) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.installTime = installTime;
        this.designer = designer;
        this.photo = photo;
        this.panelPower = panelPower;
        this.panelType = panelType;
        this.panelSeriesCount = panelSeriesCount;
        this.panelParallelCount = panelParallelCount;
        this.panelCount = panelCount;
        this.panelVoltage = panelVoltage;
        this.panelCurrent = panelCurrent;
        this.panelSinglePower = panelSinglePower;
        this.panelSingleVmp = panelSingleVmp;
        this.panelSingleVoc = panelSingleVoc;
        this.panelSingleImp = panelSingleImp;
        this.panelSingleIsc = panelSingleIsc;
        this.panelRemark = panelRemark;
        this.batteryType = batteryType;
        this.batterySingleVoltage = batterySingleVoltage;
        this.batterySingleCapacity = batterySingleCapacity;
        this.batterySeriesCount = batterySeriesCount;
        this.batteryParallelCount = batteryParallelCount;
        this.batteryCount = batteryCount;
        this.batteryVoltage = batteryVoltage;
        this.batteryCapacity = batteryCapacity;
        this.batteryRemark = batteryRemark;
        this.loadType = loadType;
        this.loadPower = loadPower;
        this.countryId = countryId;
        this.provinceId = provinceId;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.maxAnnualTemper = maxAnnualTemper;
        this.minAnnualTemper = minAnnualTemper;
        this.geoRemark = geoRemark;
    }


}
