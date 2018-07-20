package com.shuorigf.solarstaition.data.response.login;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class UserInfo {
    public static final int TYPE_SUPER_ADMIN = 0;
    public static final int TYPE_MAIN_ACCOUNT = 1;
    public static final int TYPE_SUB_ACCOUNT = 2;

    /**
     * company : xxx公司
     * address : xxx地址
     * email : admin@station-iot.com
     * mobile : 12345678999
     * logo : http://station-iot.com/upload/logo/xxx_company.jpg
     * role : 1 (用户类型（0超级管理员，1主账户，2子账户)
     * item_list : 1,2,3,4,5,6 (该用户负责的电站id列表，多个id用逗号分隔)
     * token : asdfljasldfjasdlfj234pi23p4i
     */
    @SerializedName("user_name")
    public String userName;
    @SerializedName("company")
    public String company;
    @SerializedName("address")
    public String address;
    @SerializedName("email")
    public String email;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("logo")
    public String logo;
    @SerializedName("role")
    public int role;
    @SerializedName("item_list")
    public String itemList;
    @SerializedName("project_count")
    public String projectCount;
    @SerializedName("station_count")
    public String stationCount;
    @SerializedName("device_count")
    public String deviceCount;
    @SerializedName("token")
    public String token;
}
