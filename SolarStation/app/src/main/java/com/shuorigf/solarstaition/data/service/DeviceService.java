package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.params.Id;
import com.shuorigf.solarstaition.data.params.station.StationId;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.response.device.DeviceDataLogChartInfo;
import com.shuorigf.solarstaition.data.response.device.DeviceDataLogTableListInfo;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.data.response.device.ReadSettingInfo;
import com.shuorigf.solarstaition.data.response.device.RealTimeDataInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by clx on 18/2/28.
 */
public interface DeviceService {


    /**
     * 查询设备列表
     */
    @POST(ApiConstants.DEVICE_GET)
    @FormUrlEncoded
    Flowable<HttpResult<List<DeviceListInfo>>> getDeviceList(@Field(StationId.STATION_ID) String id);

    /**
     * 获取设备实时监控数据
     */
    @POST(ApiConstants.DEVICE_GET_REAL_TIME_DATA)
    @FormUrlEncoded
    Flowable<HttpResult<RealTimeDataInfo>> getRealTimeData(@Field(Id.ID) String id);


    /**
     * 获取设备数据明细
     */
    @POST(ApiConstants.DEVICE_GET_DATA_LOG_TABLE)
    @FormUrlEncoded
    Flowable<HttpResult<DeviceDataLogTableListInfo>> getDataLogTable(@FieldMap Map<String, String> map);

    /**
     * 读取设备参数
     */
    @POST(ApiConstants.DEVICE_READ_SETTING)
    @FormUrlEncoded
    Flowable<HttpResult<ReadSettingInfo>> readSetting(@Field(Id.ID) String id);


    /**
     * 设备运行日志(实时曲线)
     */
    @POST(ApiConstants.DEVICE_GET_DATA_LOG_CHART)
    @FormUrlEncoded
    Flowable<HttpResult<DeviceDataLogChartInfo>> getDataLogChart(@FieldMap Map<String, String> map);

    /**
     * 删除设备
     */
    @POST(ApiConstants.DEVICE_DEL)
    @FormUrlEncoded
    Flowable<HttpResult<Void>> delDevice(@Field(Id.ID) String id);

    /**
     * 添加/修改设备
     */
    @POST(ApiConstants.DEVICE_SAVE)
    @FormUrlEncoded
    Flowable<HttpResult<SaveInfo>> saveDevice(@FieldMap Map<String, String> map);
}
