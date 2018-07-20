package com.shuorigf.solarstaition.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.device.RealTimeDataInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by clx on 2017/10/19.
 */

public class RealTimeMonitoringAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    @BindArray(R.array.storage_battery_title)
    TypedArray mStorageBatteryTitle;
    @BindArray(R.array.battery_board_title)
    TypedArray mBatteryBoardTitle;
    @BindArray(R.array.load_title)
    TypedArray mLoadTitle;
    @BindArray(R.array.load2_title)
    TypedArray mLoad2Title;

    private RealTimeDataInfo realTimeDataInfo;

    public RealTimeMonitoringAdapter(List<Integer> data, RealTimeDataInfo realTimeDataInfo) {
        super(R.layout.rv_item_real_time_monitoring, data);
        this.realTimeDataInfo = realTimeDataInfo == null ? new RealTimeDataInfo() : realTimeDataInfo;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = super.onCreateViewHolder(parent, viewType);
        ButterKnife.bind(this, baseViewHolder.itemView);
        return baseViewHolder;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        RecyclerView recyclerView = baseViewHolder.getView(R.id.rv_content);
        View headerView = mLayoutInflater.inflate(R.layout.rv_item_real_time_monitoring_content_header, null, false);
        TextView titleTv = headerView.findViewById(R.id.tv_title);
        List<Integer> list = new ArrayList<>();
        View footerView  = mLayoutInflater.inflate(R.layout.rv_item_real_time_monitoring_content_footer, null, false);
        ((TextView)footerView.findViewById(R.id.tv_status)).setText(integer);
        TextView statusTv = footerView.findViewById(R.id.tv_status_value);
        switch (integer) {
            case R.string.storage_battery_state:
                for (int i = 0; i < mStorageBatteryTitle.length(); i++) {
                    list.add(mStorageBatteryTitle.getResourceId(i, 0));
                }
                titleTv.setText(R.string.storage_battery);
                titleTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_storage_battery, 0, 0);
                switch (realTimeDataInfo.batterySts) {
                    case RealTimeDataInfo.STATUS_BATTERY_NORMAL:
                        statusTv.setText(R.string.normal);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_LOW_VOLTAGE_DISCONNECT:
                        statusTv.setText(R.string.low_voltage_disconnect);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_UNDER_VOLTAGE_ALARM:
                        statusTv.setText(R.string.under_voltage_alarm);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_OVER_VOLTAGE_DISCONNECT:
                        statusTv.setText(R.string.over_voltage_disconnect);
                        break;
                    case RealTimeDataInfo.STATUS_BATTERY_BATTERY_THERMAL:
                        statusTv.setText(R.string.battery_thermal);
                        break;
                }
                break;

            case R.string.battery_board_state:
                for (int i = 0; i < mBatteryBoardTitle.length(); i++) {
                    list.add(mBatteryBoardTitle.getResourceId(i, 0));
                }
                titleTv.setText(R.string.battery_board);
                titleTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_battery_board, 0, 0);
                switch (realTimeDataInfo.panelSts) {
                    case RealTimeDataInfo.STATUS_PANEL_PV_OVER_VOLTAGE:
                        statusTv.setText(R.string.pv_over_voltage);
                        break;
                    case RealTimeDataInfo.STATUS_PANEL_PV_REVERSE:
                        statusTv.setText(R.string.pv_reverse);
                        break;
                    case RealTimeDataInfo.STATUS_PANEL_PV_OVER_POWER:
                        statusTv.setText(R.string.pv_over_power);
                        break;
                }
                break;
            case R.string.DC_load_state:
                for (int i = 0; i < mLoadTitle.length(); i++) {
                    list.add(mLoadTitle.getResourceId(i, 0));
                }
                titleTv.setText(R.string.load);
                titleTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_load, 0, 0);
                switch (realTimeDataInfo.loadDcSts) {
                    case RealTimeDataInfo.STATUS_LOAD_CLOSE:
                        statusTv.setText(R.string.close);
                        break;
                    case RealTimeDataInfo.STATUS_LOAD_OPEN:
                        statusTv.setText(R.string.open);
                        break;
                    case RealTimeDataInfo.STATUS_LOAD_OVER_LOAD:
                        statusTv.setText(R.string.over_load);
                        break;
                    case RealTimeDataInfo.STATUS_LOAD_SHORT_CIRCUIT:
                        statusTv.setText(R.string.short_circuit);
                        break;
                }
                break;
            case R.string.AC_load_state:
                for (int i = 0; i < mLoad2Title.length(); i++) {
                    list.add(mLoad2Title.getResourceId(i, 0));
                }
                switch (realTimeDataInfo.loadAcSts) {
                    case RealTimeDataInfo.STATUS_LOAD_CLOSE:
                        statusTv.setText(R.string.close);
                        break;
                    case RealTimeDataInfo.STATUS_LOAD_OPEN:
                        statusTv.setText(R.string.open);
                        break;
                    case RealTimeDataInfo.STATUS_LOAD_OVER_LOAD:
                        statusTv.setText(R.string.over_load);
                        break;
                    case RealTimeDataInfo.STATUS_LOAD_SHORT_CIRCUIT:
                        statusTv.setText(R.string.short_circuit);
                        break;
                }
                break;
        }

        RealTimeMonitoringContentAdapter realTimeMonitoringContentAdapter = new RealTimeMonitoringContentAdapter(list, realTimeDataInfo);
        realTimeMonitoringContentAdapter.setHeaderViewAsFlow(true);
        if (integer != R.string.AC_load_state) {
            realTimeMonitoringContentAdapter.addHeaderView(headerView);
        }
        realTimeMonitoringContentAdapter.addFooterView(footerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(list.size());
        recyclerView.setAdapter(realTimeMonitoringContentAdapter);
    }
}
