package com.shuorigf.solarstaition.data.response.device;

import java.util.List;

/**
 * auther: chenlixin on 18/1/23.
 */

public class DeviceDataLogTableListInfo {

        /**
         * total : 2454
         * table : [{"id":"15461","device_id":"44","collector_id":"12","station_id":"27","battery_soc":"80","battery_vol":"12.6","battery_chg_cur":"0.00","battery_temp":"25","battery_max_vol":"13.2","battery_min_vol":"12.6","battery_sts":"0","battery_chg_sts":"0","battery_total_dischg_num":"0","battery_total_full_chg_num":"0","battery_total_chg_am":"0","battery_total_dischg_am":"11","panel_vol":"0.0","panel_cur":"0.00","panel_day_max_chg_cur":"0.00","panel_day_max_dischg_cur":"3.16","panel_day_max_chg_pow":"0","panel_day_max_dischg_pow":"40","panel_day_max_chg_am":"0","panel_day_max_dischg_am":"11","panel_day_generating":"0.000","panel_day_consumption":"133.000","panel_total_generating":"0.000","panel_total_consumption":"133.000","panel_chg_pow":"0","panel_sts":"0","load_dc_vol":"12.6","load_dc_cur":"2.96","load_dc_pow":"37","load_dc_sts":"1","load_ac_vol":"0.0","load_ac_cur":"0.00","load_ac_pow":"0","load_ac_opt_fqc":"0","load_ac_sts":"1","load_dischg_pow":"37.000","load_brightness":"0","run_total_days":"6","controller_temp":"26","status":"1","log_time":"2018-07-19 13:49:19"},{"id":"15460","device_id":"44","collector_id":"12","station_id":"27","battery_soc":"81","battery_vol":"12.6","battery_chg_cur":"0.00","battery_temp":"25","battery_max_vol":"13.2","battery_min_vol":"12.6","battery_sts":"0","battery_chg_sts":"0","battery_total_dischg_num":"0","battery_total_full_chg_num":"0","battery_total_chg_am":"0","battery_total_dischg_am":"11","panel_vol":"0.0","panel_cur":"0.00","panel_day_max_chg_cur":"0.00","panel_day_max_dischg_cur":"3.16","panel_day_max_chg_pow":"0","panel_day_max_dischg_pow":"40","panel_day_max_chg_am":"0","panel_day_max_dischg_am":"11","panel_day_generating":"0.000","panel_day_consumption":"133.000","panel_total_generating":"0.000","panel_total_consumption":"133.000","panel_chg_pow":"0","panel_sts":"0","load_dc_vol":"12.6","load_dc_cur":"2.96","load_dc_pow":"37","load_dc_sts":"1","load_ac_vol":"0.0","load_ac_cur":"0.00","load_ac_pow":"0","load_ac_opt_fqc":"0","load_ac_sts":"1","load_dischg_pow":"37.000","load_brightness":"0","run_total_days":"6","controller_temp":"26","status":"1","log_time":"2018-07-19 13:44:19"},{"id":"15459","device_id":"44","collector_id":"12","station_id":"27","battery_soc":"81","battery_vol":"12.6","battery_chg_cur":"0.00","battery_temp":"25","battery_max_vol":"13.2","battery_min_vol":"12.6","battery_sts":"0","battery_chg_sts":"0","battery_total_dischg_num":"0","battery_total_full_chg_num":"0","battery_total_chg_am":"0","battery_total_dischg_am":"10","panel_vol":"0.0","panel_cur":"0.00","panel_day_max_chg_cur":"0.00","panel_day_max_dischg_cur":"3.16","panel_day_max_chg_pow":"0","panel_day_max_dischg_pow":"40","panel_day_max_chg_am":"0","panel_day_max_dischg_am":"10","panel_day_generating":"0.000","panel_day_consumption":"121.000","panel_total_generating":"0.000","panel_total_consumption":"121.000","panel_chg_pow":"0","panel_sts":"0","load_dc_vol":"12.6","load_dc_cur":"2.96","load_dc_pow":"37","load_dc_sts":"1","load_ac_vol":"0.0","load_ac_cur":"0.00","load_ac_pow":"0","load_ac_opt_fqc":"0","load_ac_sts":"1","load_dischg_pow":"37.000","load_brightness":"0","run_total_days":"6","controller_temp":"26","status":"1","log_time":"2018-07-19 13:39:17"}]
         */

        private int total;
        private List<TableBean> table;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<TableBean> getTable() {
            return table;
        }

        public void setTable(List<TableBean> table) {
            this.table = table;
        }

        public static class TableBean {
            /**
             * id : 15461
             * device_id : 44
             * collector_id : 12
             * station_id : 27
             * battery_soc : 80
             * battery_vol : 12.6
             * battery_chg_cur : 0.00
             * battery_temp : 25
             * battery_max_vol : 13.2
             * battery_min_vol : 12.6
             * battery_sts : 0
             * battery_chg_sts : 0
             * battery_total_dischg_num : 0
             * battery_total_full_chg_num : 0
             * battery_total_chg_am : 0
             * battery_total_dischg_am : 11
             * panel_vol : 0.0
             * panel_cur : 0.00
             * panel_day_max_chg_cur : 0.00
             * panel_day_max_dischg_cur : 3.16
             * panel_day_max_chg_pow : 0
             * panel_day_max_dischg_pow : 40
             * panel_day_max_chg_am : 0
             * panel_day_max_dischg_am : 11
             * panel_day_generating : 0.000
             * panel_day_consumption : 133.000
             * panel_total_generating : 0.000
             * panel_total_consumption : 133.000
             * panel_chg_pow : 0
             * panel_sts : 0
             * load_dc_vol : 12.6
             * load_dc_cur : 2.96
             * load_dc_pow : 37
             * load_dc_sts : 1
             * load_ac_vol : 0.0
             * load_ac_cur : 0.00
             * load_ac_pow : 0
             * load_ac_opt_fqc : 0
             * load_ac_sts : 1
             * load_dischg_pow : 37.000
             * load_brightness : 0
             * run_total_days : 6
             * controller_temp : 26
             * status : 1
             * log_time : 2018-07-19 13:49:19
             */

            private String id;
            private String device_id;
            private String collector_id;
            private String station_id;
            private String battery_soc;
            private String battery_vol;
            private String battery_chg_cur;
            private String battery_temp;
            private String battery_max_vol;
            private String battery_min_vol;
            private String battery_sts;
            private String battery_chg_sts;
            private String battery_total_dischg_num;
            private String battery_total_full_chg_num;
            private String battery_total_chg_am;
            private String battery_total_dischg_am;
            private String panel_vol;
            private String panel_cur;
            private String panel_day_max_chg_cur;
            private String panel_day_max_dischg_cur;
            private String panel_day_max_chg_pow;
            private String panel_day_max_dischg_pow;
            private String panel_day_max_chg_am;
            private String panel_day_max_dischg_am;
            private String panel_day_generating;
            private String panel_day_consumption;
            private String panel_total_generating;
            private String panel_total_consumption;
            private String panel_chg_pow;
            private String panel_sts;
            private String load_dc_vol;
            private String load_dc_cur;
            private String load_dc_pow;
            private String load_dc_sts;
            private String load_ac_vol;
            private String load_ac_cur;
            private String load_ac_pow;
            private String load_ac_opt_fqc;
            private String load_ac_sts;
            private String load_dischg_pow;
            private String load_brightness;
            private String run_total_days;
            private String controller_temp;
            private String status;
            private String log_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getCollector_id() {
                return collector_id;
            }

            public void setCollector_id(String collector_id) {
                this.collector_id = collector_id;
            }

            public String getStation_id() {
                return station_id;
            }

            public void setStation_id(String station_id) {
                this.station_id = station_id;
            }

            public String getBattery_soc() {
                return battery_soc;
            }

            public void setBattery_soc(String battery_soc) {
                this.battery_soc = battery_soc;
            }

            public String getBattery_vol() {
                return battery_vol;
            }

            public void setBattery_vol(String battery_vol) {
                this.battery_vol = battery_vol;
            }

            public String getBattery_chg_cur() {
                return battery_chg_cur;
            }

            public void setBattery_chg_cur(String battery_chg_cur) {
                this.battery_chg_cur = battery_chg_cur;
            }

            public String getBattery_temp() {
                return battery_temp;
            }

            public void setBattery_temp(String battery_temp) {
                this.battery_temp = battery_temp;
            }

            public String getBattery_max_vol() {
                return battery_max_vol;
            }

            public void setBattery_max_vol(String battery_max_vol) {
                this.battery_max_vol = battery_max_vol;
            }

            public String getBattery_min_vol() {
                return battery_min_vol;
            }

            public void setBattery_min_vol(String battery_min_vol) {
                this.battery_min_vol = battery_min_vol;
            }

            public String getBattery_sts() {
                return battery_sts;
            }

            public void setBattery_sts(String battery_sts) {
                this.battery_sts = battery_sts;
            }

            public String getBattery_chg_sts() {
                return battery_chg_sts;
            }

            public void setBattery_chg_sts(String battery_chg_sts) {
                this.battery_chg_sts = battery_chg_sts;
            }

            public String getBattery_total_dischg_num() {
                return battery_total_dischg_num;
            }

            public void setBattery_total_dischg_num(String battery_total_dischg_num) {
                this.battery_total_dischg_num = battery_total_dischg_num;
            }

            public String getBattery_total_full_chg_num() {
                return battery_total_full_chg_num;
            }

            public void setBattery_total_full_chg_num(String battery_total_full_chg_num) {
                this.battery_total_full_chg_num = battery_total_full_chg_num;
            }

            public String getBattery_total_chg_am() {
                return battery_total_chg_am;
            }

            public void setBattery_total_chg_am(String battery_total_chg_am) {
                this.battery_total_chg_am = battery_total_chg_am;
            }

            public String getBattery_total_dischg_am() {
                return battery_total_dischg_am;
            }

            public void setBattery_total_dischg_am(String battery_total_dischg_am) {
                this.battery_total_dischg_am = battery_total_dischg_am;
            }

            public String getPanel_vol() {
                return panel_vol;
            }

            public void setPanel_vol(String panel_vol) {
                this.panel_vol = panel_vol;
            }

            public String getPanel_cur() {
                return panel_cur;
            }

            public void setPanel_cur(String panel_cur) {
                this.panel_cur = panel_cur;
            }

            public String getPanel_day_max_chg_cur() {
                return panel_day_max_chg_cur;
            }

            public void setPanel_day_max_chg_cur(String panel_day_max_chg_cur) {
                this.panel_day_max_chg_cur = panel_day_max_chg_cur;
            }

            public String getPanel_day_max_dischg_cur() {
                return panel_day_max_dischg_cur;
            }

            public void setPanel_day_max_dischg_cur(String panel_day_max_dischg_cur) {
                this.panel_day_max_dischg_cur = panel_day_max_dischg_cur;
            }

            public String getPanel_day_max_chg_pow() {
                return panel_day_max_chg_pow;
            }

            public void setPanel_day_max_chg_pow(String panel_day_max_chg_pow) {
                this.panel_day_max_chg_pow = panel_day_max_chg_pow;
            }

            public String getPanel_day_max_dischg_pow() {
                return panel_day_max_dischg_pow;
            }

            public void setPanel_day_max_dischg_pow(String panel_day_max_dischg_pow) {
                this.panel_day_max_dischg_pow = panel_day_max_dischg_pow;
            }

            public String getPanel_day_max_chg_am() {
                return panel_day_max_chg_am;
            }

            public void setPanel_day_max_chg_am(String panel_day_max_chg_am) {
                this.panel_day_max_chg_am = panel_day_max_chg_am;
            }

            public String getPanel_day_max_dischg_am() {
                return panel_day_max_dischg_am;
            }

            public void setPanel_day_max_dischg_am(String panel_day_max_dischg_am) {
                this.panel_day_max_dischg_am = panel_day_max_dischg_am;
            }

            public String getPanel_day_generating() {
                return panel_day_generating;
            }

            public void setPanel_day_generating(String panel_day_generating) {
                this.panel_day_generating = panel_day_generating;
            }

            public String getPanel_day_consumption() {
                return panel_day_consumption;
            }

            public void setPanel_day_consumption(String panel_day_consumption) {
                this.panel_day_consumption = panel_day_consumption;
            }

            public String getPanel_total_generating() {
                return panel_total_generating;
            }

            public void setPanel_total_generating(String panel_total_generating) {
                this.panel_total_generating = panel_total_generating;
            }

            public String getPanel_total_consumption() {
                return panel_total_consumption;
            }

            public void setPanel_total_consumption(String panel_total_consumption) {
                this.panel_total_consumption = panel_total_consumption;
            }

            public String getPanel_chg_pow() {
                return panel_chg_pow;
            }

            public void setPanel_chg_pow(String panel_chg_pow) {
                this.panel_chg_pow = panel_chg_pow;
            }

            public String getPanel_sts() {
                return panel_sts;
            }

            public void setPanel_sts(String panel_sts) {
                this.panel_sts = panel_sts;
            }

            public String getLoad_dc_vol() {
                return load_dc_vol;
            }

            public void setLoad_dc_vol(String load_dc_vol) {
                this.load_dc_vol = load_dc_vol;
            }

            public String getLoad_dc_cur() {
                return load_dc_cur;
            }

            public void setLoad_dc_cur(String load_dc_cur) {
                this.load_dc_cur = load_dc_cur;
            }

            public String getLoad_dc_pow() {
                return load_dc_pow;
            }

            public void setLoad_dc_pow(String load_dc_pow) {
                this.load_dc_pow = load_dc_pow;
            }

            public String getLoad_dc_sts() {
                return load_dc_sts;
            }

            public void setLoad_dc_sts(String load_dc_sts) {
                this.load_dc_sts = load_dc_sts;
            }

            public String getLoad_ac_vol() {
                return load_ac_vol;
            }

            public void setLoad_ac_vol(String load_ac_vol) {
                this.load_ac_vol = load_ac_vol;
            }

            public String getLoad_ac_cur() {
                return load_ac_cur;
            }

            public void setLoad_ac_cur(String load_ac_cur) {
                this.load_ac_cur = load_ac_cur;
            }

            public String getLoad_ac_pow() {
                return load_ac_pow;
            }

            public void setLoad_ac_pow(String load_ac_pow) {
                this.load_ac_pow = load_ac_pow;
            }

            public String getLoad_ac_opt_fqc() {
                return load_ac_opt_fqc;
            }

            public void setLoad_ac_opt_fqc(String load_ac_opt_fqc) {
                this.load_ac_opt_fqc = load_ac_opt_fqc;
            }

            public String getLoad_ac_sts() {
                return load_ac_sts;
            }

            public void setLoad_ac_sts(String load_ac_sts) {
                this.load_ac_sts = load_ac_sts;
            }

            public String getLoad_dischg_pow() {
                return load_dischg_pow;
            }

            public void setLoad_dischg_pow(String load_dischg_pow) {
                this.load_dischg_pow = load_dischg_pow;
            }

            public String getLoad_brightness() {
                return load_brightness;
            }

            public void setLoad_brightness(String load_brightness) {
                this.load_brightness = load_brightness;
            }

            public String getRun_total_days() {
                return run_total_days;
            }

            public void setRun_total_days(String run_total_days) {
                this.run_total_days = run_total_days;
            }

            public String getController_temp() {
                return controller_temp;
            }

            public void setController_temp(String controller_temp) {
                this.controller_temp = controller_temp;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLog_time() {
                return log_time;
            }

            public void setLog_time(String log_time) {
                this.log_time = log_time;
            }
        }
}
