package com.shuorigf.solarstaition.data.params.common;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class UploadParams {
    public static final String TYPE_PICTURE = "0";
    public static final String TYPE_DOCUMENT = "1";

    @SerializedName("type")
    public String type;

    /**
     *
     * @param type 文件类型(0图片, 1文档)
     */
    public UploadParams(String type) {
        this.type = type;
    }
}
