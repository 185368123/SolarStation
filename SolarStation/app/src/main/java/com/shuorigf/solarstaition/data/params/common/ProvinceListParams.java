package com.shuorigf.solarstaition.data.params.common;

import com.google.gson.annotations.SerializedName;

/**
 * auther: chenlixin on 18/1/22.
 */

public class ProvinceListParams {
    @SerializedName("country_id")
    public String countryId;

    /**
     *
     * @param countryId 国家id
     */
    public ProvinceListParams(String countryId) {
        this.countryId = countryId;
    }
}
