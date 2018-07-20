package com.shuorigf.solarstaition.data.service;


import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.data.params.project.ProjectId;
import com.shuorigf.solarstaition.data.params.project.ProjectListParams;
import com.shuorigf.solarstaition.data.response.HttpResult;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.response.project.ProjectDataInfo;
import com.shuorigf.solarstaition.data.response.project.ProjectListInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by clx on 18/2/27.
 */
public interface ProjectService {

    /**
     * 项目数据
     */
    @POST(ApiConstants.PROJECT_DATA)
    @FormUrlEncoded
    Flowable<HttpResult<ProjectDataInfo>> getProjectData(@Field(ProjectId.PROJECT_ID) String id);


    /**
     * 项目列表
     */
    @POST(ApiConstants.PROJECT_GET)
    @FormUrlEncoded
    Flowable<HttpResult<List<ProjectListInfo>>> getProjectList(@Field(ProjectListParams.SHOW_STATION) String show_station);


    /**
     * 添加或修改项目
     */
    @POST(ApiConstants.PROJECT_SAVE)
    @FormUrlEncoded
    Flowable<HttpResult<SaveInfo>> saveProject(@FieldMap Map<String, String> map);
}
