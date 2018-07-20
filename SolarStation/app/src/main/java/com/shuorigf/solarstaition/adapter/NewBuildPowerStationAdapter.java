package com.shuorigf.solarstaition.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.params.station.StationSaveParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by clx on 2017/10/15.
 */

public class NewBuildPowerStationAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
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

    private StationSaveParams mStationSaveParams;

    public NewBuildPowerStationAdapter(List<Integer> data, StationSaveParams stationSaveParams) {
        super(R.layout.rv_item_power_station_information, data);
        this.mStationSaveParams = stationSaveParams;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = super.onCreateViewHolder(parent, viewType);
        ButterKnife.bind(this, baseViewHolder.itemView);
        return baseViewHolder;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        baseViewHolder.setGone(R.id.iv_edit, false);
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
        NewBuildPowerStationContentAdapter newBuildPowerStationContentAdapter = new NewBuildPowerStationContentAdapter(list, mStationSaveParams);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(list.size());
        recyclerView.setAdapter(newBuildPowerStationContentAdapter);
    }
}
