package com.shuorigf.solarstaition.util;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class JsonUntils {

    public static String[] getKey(String jsonString){
        String[] s;
        List<String> list = new ArrayList<String>();
        try {
            JSONObject json = new JSONObject(jsonString);
            Iterator key=json.keys();
            while (key.hasNext()){
                String sKey = key.next().toString();
                list.add(sKey);
            }
        } catch (JSONException e) {
            return new String[]{};
        }
        return list.toArray(new String[list.size()]);
    }

    public static List<Float> getValue(String jsonString){
        List<Float> list = new ArrayList<Float>();
        LogUtils.logd(jsonString.toString());
        try {
            JSONObject json = new JSONObject(jsonString);
            Iterator key=json.keys();
            while (key.hasNext()){
                String sKey = key.next().toString();
                list.add(Float.parseFloat(json.optString(sKey)));
            }
        } catch (JSONException e) {
            return list;
        }
        return list;
    }

}
