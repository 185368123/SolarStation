package com.shuorigf.solarstaition.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.alarm.AlarmListInfo;

import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class AlarmContentAdapter extends BaseQuickAdapter<AlarmListInfo, BaseViewHolder> {

    public AlarmContentAdapter(List<AlarmListInfo> data) {
        super(R.layout.rv_item_alarm_content, data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, AlarmListInfo alarmListInfo) {
        baseViewHolder.setText(R.id.tv_name, alarmListInfo.deviceType);
        baseViewHolder.setText(R.id.tv_number, alarmListInfo.serialNo);
        baseViewHolder.setText(R.id.tv_status, alarmListInfo.alarmType);
        baseViewHolder.setText(R.id.tv_alarm_describe, String.format(mContext.getString(R.string.format_alarm_description_colon),alarmListInfo.alarmDesc));
        baseViewHolder.setText(R.id.tv_disappearance_time, String.format(mContext.getString(R.string.format_disappearance_time_colon),alarmListInfo.recoveryTime));
        baseViewHolder.setText(R.id.tv_time, alarmListInfo.alarmTime);
        baseViewHolder.setText(R.id.tv_situation, alarmListInfo.marker);
    }
}
