package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.login.UserInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by clx on 18/2/2.
 */
public interface UserService {

    /**
     * 用户登录
     */
    @POST(ApiConstants.COMMON_LOGIN)
    @FormUrlEncoded
    Flowable<HttpResult<UserInfo>> login(@FieldMap Map<String, String> map);

}
