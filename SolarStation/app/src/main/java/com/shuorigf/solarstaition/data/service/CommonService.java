package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.common.UrlInfo;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by clx on 18/3/8.
 */
public interface CommonService {


    /**
     * 上传图片
     */
    @POST(ApiConstants.COMMON_UPLOAD)
    @Multipart
    Flowable<HttpResult<UrlInfo>> uploadFile(@Part("type") RequestBody type, @Part("file\"; filename=\"image.jpg") RequestBody file);
}
