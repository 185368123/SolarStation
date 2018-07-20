package com.shuorigf.solarstaition.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.response.device.ReadSettingInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by clx on 2017/10/15.
 */

public class ParameterSettingAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    @BindArray(R.array.storage_battery_parameter_title)
    TypedArray mStorageBatteryParameterTitle;
    @BindArray(R.array.load_parameter_title)
    TypedArray mLoadParameterTitle;

    private ReadSettingInfo readSettingInfo;

    public ParameterSettingAdapter(List<Integer> data, ReadSettingInfo readSettingInfo) {
        super(R.layout.rv_item_parameter_setting, data);
        this.readSettingInfo = readSettingInfo == null ? new ReadSettingInfo() : readSettingInfo;
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
        RecyclerView recyclerView = baseViewHolder.getView(R.id.rv_content);
        List<Integer> list = new ArrayList<>();

        switch (integer) {
            case R.string.storage_battery_parameter:
                for (int i = 0; i < mStorageBatteryParameterTitle.length(); i++) {
                    list.add(mStorageBatteryParameterTitle.getResourceId(i, 0));
                }
                break;
            case R.string.load_parameter:
                for (int i = 0; i < mLoadParameterTitle.length(); i++) {
                    list.add(mLoadParameterTitle.getResourceId(i, 0));
                }
                break;
            case R.string.inverter_parameter:

                break;
        }
        ParameterSettingContentAdapter parameterSettingContentAdapter = new ParameterSettingContentAdapter(list, readSettingInfo);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(list.size());
        recyclerView.setAdapter(parameterSettingContentAdapter);
    }
}
