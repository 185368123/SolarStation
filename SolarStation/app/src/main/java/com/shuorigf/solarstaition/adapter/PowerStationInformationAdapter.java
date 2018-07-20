package com.shuorigf.solarstaition.adapter;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.response.station.StationDetailInfo;
import com.shuorigf.solarstaition.ui.activity.StationInformationChangeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by clx on 2017/10/6.
 */

public class PowerStationInformationAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    @BindArray(R.array.power_station_information_title)
    TypedArray mTitle;
    @BindArray(R.array.basic_information_title)
    TypedArray mBasicInformationTitle;
    @BindArray(R.array.battery_board_information_title)
    TypedArray mBatteryBoardInformationTitle;
    @BindArray(R.array.storage_battery_information_title)
    TypedArray mStorageBatteryTitle;
    @BindArray(R.array.load_information_title)
    TypedArray mLoadInformationTitle;
    @BindArray(R.array.geography_information_title)
    TypedArray mGeographyInformationTitle;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;


    private StationDetailInfo stationDetailInfo;

    public PowerStationInformationAdapter(List<Integer> data, StationDetailInfo stationDetailInfo) {
        super(R.layout.rv_item_power_station_information, data);
        this.stationDetailInfo = stationDetailInfo == null ? new StationDetailInfo() : stationDetailInfo;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = super.onCreateViewHolder(parent, viewType);
        ButterKnife.bind(this, baseViewHolder.itemView);
        return baseViewHolder;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        baseViewHolder.addOnClickListener(R.id.iv_edit);
        setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext, StationInformationChangeActivity.class);
                intent.putExtra(Constants.INFORMATION_CHANGE,mTitle.getResourceId(position, 0));
                mContext.startActivity(intent);
            }
        });
        RecyclerView recyclerView = baseViewHolder.getView(R.id.rv_content);
        List<Integer> list = new ArrayList<>();
        switch (integer) {
            case R.string.basic_information:
                for (int i = 0; i < mBasicInformationTitle.length(); i++) {
                    list.add(mBasicInformationTitle.getResourceId(i, 0));
                }
                break;
            case R.string.battery_board_information:
                for (int i = 0; i < mBatteryBoardInformationTitle.length(); i++) {
                    list.add(mBatteryBoardInformationTitle.getResourceId(i, 0));
                }
                break;
            case R.string.storage_battery_information:
                for (int i = 0; i < mStorageBatteryTitle.length(); i++) {
                    list.add(mStorageBatteryTitle.getResourceId(i, 0));
                }
                break;
            case R.string.load_information:
                for (int i = 0; i < mLoadInformationTitle.length(); i++) {
                    list.add(mLoadInformationTitle.getResourceId(i, 0));
                }
                break;
            case R.string.geography_information:
                for (int i = 0; i < mGeographyInformationTitle.length(); i++) {
                    list.add(mGeographyInformationTitle.getResourceId(i, 0));
                }
                break;
        }
        PowerStationInformationContentAdapter powerStationInformationContentAdapter = new PowerStationInformationContentAdapter(list, stationDetailInfo);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(list.size());
        recyclerView.setAdapter(powerStationInformationContentAdapter);
    }

    static class ViewHolder {
        @BindView(R.id.iv_edit)
        ImageView ivEdit;
    }
}
