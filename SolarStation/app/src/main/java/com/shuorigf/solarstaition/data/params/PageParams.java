package com.shuorigf.solarstaition.data.params;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/23.
 */

public class PageParams {
    private static final int DEFAULT_NUMBER = 1;
    private static final int DEFAULT_SIZE = 10;


    public static final String PAGE = "page";
    public static final String COUNT = "count";

    @SerializedName("page")
    public int page;
    @SerializedName("count")
    public int count;

    /**
     *
     * @param page 当前页码
     * @param count 每页数量
     */
    public PageParams(int page, int count) {
        this.page = page;
        this.count = count;
    }


    public PageParams() {
        this.page = DEFAULT_NUMBER;
        this.count = DEFAULT_SIZE;
    }


    public void addPage(){
        page++;
    }

}
