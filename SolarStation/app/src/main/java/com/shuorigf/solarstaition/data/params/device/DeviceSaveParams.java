package com.shuorigf.solarstaition.data.params.device;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class DeviceSaveParams {
    public static final String ID = "id";
    public static final String MODEL = "model";
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";
    public static final String SW_VERSION = "sw_version";
    public static final String HW_VERSION = "hw_version";
    public static final String SERIAL_NO = "serial_no";

    @SerializedName("id")
    public String id;
    @SerializedName("model")
    public String model;
    @SerializedName("type")
    public String type;
    @SerializedName("address")
    public String address;
    @SerializedName("sw_version")
    public String swVersion;
    @SerializedName("hw_version")
    public String hwVersion;
    @SerializedName("serial_no")
    public String serialNo;

    public DeviceSaveParams() {
    }

    /**
     *
     * @param id 设备id(传0或不传则为添加)
     * @param model 产品型号
     * @param type 产品类型
     * @param address 设备地址
     * @param swVersion 软件版本
     * @param hwVersion 硬件版本
     * @param serialNo 产品序列号
     */
    public DeviceSaveParams(String id, String model, String type, String address, String swVersion, String hwVersion, String serialNo) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.address = address;
        this.swVersion = swVersion;
        this.hwVersion = hwVersion;
        this.serialNo = serialNo;
    }
}
