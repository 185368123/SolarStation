package com.shuorigf.solarstaition.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * auther: chenlixin on 18/1/23.
 */
public class PageInfo<T> {

    @SerializedName("total")
    public int total;
    @SerializedName("table")
    public List<T> list;

}
