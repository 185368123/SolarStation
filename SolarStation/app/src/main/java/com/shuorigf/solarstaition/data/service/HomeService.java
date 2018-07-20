package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.home.HomeDataInfo;

import io.reactivex.Flowable;
import retrofit2.http.POST;

/**
 * Created by clx on 18/2/27.
 */
public interface HomeService {

    /**
     * 首页数据
     */
    @POST(ApiConstants.HOME_DATA)
    Flowable<HttpResult<HomeDataInfo>> getHomeData();

}
