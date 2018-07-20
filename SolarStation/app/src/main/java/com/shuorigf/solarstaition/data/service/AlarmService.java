package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.alarm.AlarmListInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by clx on 18/2/28.
 */
public interface AlarmService {


    /**
     * 获取报警信息列表/搜索报警信息
     */
    @POST(ApiConstants.ALARM_GET)
    @FormUrlEncoded
    Flowable<HttpResult<List<AlarmListInfo>>> getAlarmList(@FieldMap Map<String, String> map);
}
