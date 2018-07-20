package com.shuorigf.solarstaition;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static String[] getY_VALUE(double max,double min,double avg){
        List<String> list=new ArrayList<>();
        double distance_max=Math.ceil(Math.max(max-avg,avg-min));
        while (distance_max%4!=0){
            distance_max++;
        }
        if (distance_max==0)
            distance_max=4;
        for (int i = 0; i < 9; i++) {
            list.add(Math.ceil(avg)+(distance_max/4)*(4-i)+"");
        }
        return  (String[])list.toArray(new String[list.size()]);
    }
}
