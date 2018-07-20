package com.shuorigf.solarstaition.data;


import com.shuorigf.solarstaition.data.params.station.StationSaveParams;
import com.shuorigf.solarstaition.data.response.project.ProjectListInfo;

import java.util.List;

//保存临时数据的单例
public class SingleBeans {
    private static SingleBeans singleBeans;


    private SingleBeans() {

    }

    public static SingleBeans getInstance() {
        if (singleBeans == null) {
            synchronized (SingleBeans.class) {
                if (singleBeans == null) {
                    singleBeans = new SingleBeans();
                }
            }
        }
        return singleBeans;
    }

    //项目信息保存，用来修改或新建电站选择
    private List<ProjectListInfo> projectListInfos;

    public void setProjectListInfos(List<ProjectListInfo> projectListInfos) {
        this.projectListInfos = projectListInfos;
    }

    public List<ProjectListInfo> getProjectListInfos() {
        return projectListInfos;
    }

    //当前所修改电站数据
    private StationSaveParams saveParams;

    public void setSaveParams(StationSaveParams saveParams) {
        this.saveParams = saveParams;
    }

    public StationSaveParams getSaveParams() {
        return saveParams;
    }

    //设备运行日志类型
    private String date_type = "0";//日期类型(0全部, 1年, 2月, 3日)
    private String date;//日期(年月日格式参考：2017/2017-12/2017-12-12，若不传date为当年/当月/当日)

    public void setDate_type(String date_type) {
        this.date_type = date_type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_type() {
        return date_type;
    }

    public String getDate() {
        return date;
    }
}
