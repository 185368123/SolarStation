package com.shuorigf.solarstaition.data.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by clx on 18/2/2.
 */

public class HttpResult<T> {


    @SerializedName("code")
    public String code;
    @SerializedName("msg")
    public String message;

    @SerializedName("data")
    public T data;

}
