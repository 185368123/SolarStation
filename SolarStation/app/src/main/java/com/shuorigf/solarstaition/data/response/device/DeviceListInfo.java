package com.shuorigf.solarstaition.data.response.device;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class DeviceListInfo implements Parcelable {
    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_ABNORMAL = 0;

    /**
     * model : AHU3453249685
     * type : 逆变器
     * address : 0-255
     * sw_version : NO10.02
     * hw_version : No21.52
     * serial_no : HDJEI6546421
     * status : 1 设备状态(1正常，0异常)
     */
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
    @SerializedName("status")
    public int status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.model);
        dest.writeString(this.type);
        dest.writeString(this.address);
        dest.writeString(this.swVersion);
        dest.writeString(this.hwVersion);
        dest.writeString(this.serialNo);
        dest.writeInt(this.status);
    }

    public DeviceListInfo() {
    }

    protected DeviceListInfo(Parcel in) {
        this.id = in.readString();
        this.model = in.readString();
        this.type = in.readString();
        this.address = in.readString();
        this.swVersion = in.readString();
        this.hwVersion = in.readString();
        this.serialNo = in.readString();
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<DeviceListInfo> CREATOR = new Parcelable.Creator<DeviceListInfo>() {
        @Override
        public DeviceListInfo createFromParcel(Parcel source) {
            return new DeviceListInfo(source);
        }

        @Override
        public DeviceListInfo[] newArray(int size) {
            return new DeviceListInfo[size];
        }
    };
}
