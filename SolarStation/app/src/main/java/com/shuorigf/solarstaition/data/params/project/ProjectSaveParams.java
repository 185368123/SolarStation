package com.shuorigf.solarstaition.data.params.project;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */
public class ProjectSaveParams {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GUEST_NAME = "guest_name";
    public static final String COMPANY_NAME = "company_name";
    public static final String ADDRESS = "address";

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("guest_name")
    public String guestName;
    @SerializedName("company_name")
    public String companyName;
    @SerializedName("address")
    public String address;

    public ProjectSaveParams() {
    }

    /**
     *
     * @param id 项目ID(传0或不传则为添加)
     * @param name 项目名称
     * @param guestName 客户名称
     * @param companyName 公司名称
     * @param address 项目地址
     */
    public ProjectSaveParams(String id, String name, String guestName, String companyName, String address) {
        this.id = id;
        this.name = name;
        this.guestName = guestName;
        this.companyName = companyName;
        this.address = address;
    }
}
