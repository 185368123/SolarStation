package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.params.Id;
import com.shuorigf.solarstaition.data.params.station.StationListParams;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.response.station.StationDataInfo;
import com.shuorigf.solarstaition.data.response.station.StationDetailInfo;
import com.shuorigf.solarstaition.data.response.station.StationListInfo;
import com.shuorigf.solarstaition.data.response.station.StationPowerLogInfo;

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
public interface StationService {

    /**
     * 电站数据
     */
    @POST(ApiConstants.STATION_DATA)
    @FormUrlEncoded
    Flowable<HttpResult<StationDataInfo>> getStationData(@Field(Id.ID) String id);


    /**
     * 电站列表
     */
    @POST(ApiConstants.STATION_GET)
    @FormUrlEncoded
    Flowable<HttpResult<List<StationListInfo>>> getStationList(@Field(StationListParams.PROJECT_ID) String id, @Field(StationListParams.KEYWORD) String keyword);


    /**
     * 电站详情
     */
    @POST(ApiConstants.STATION_DETAIL)
    @FormUrlEncoded
    Flowable<HttpResult<StationDetailInfo>> getStationDetail(@Field(Id.ID) String id);

    /**
     * 添加或修改电站
     */
    @POST(ApiConstants.STATION_SAVE)
    @FormUrlEncoded
    Flowable<HttpResult<SaveInfo>> saveStation(@FieldMap Map<String, String> map);


    /**
     * 查询充电/放电功率日志
     */
    @POST(ApiConstants.STATION_GET_POWER_LOG)
    @FormUrlEncoded
    Flowable<HttpResult<StationPowerLogInfo>> getPowerLog(@FieldMap Map<String, String> map);

}
