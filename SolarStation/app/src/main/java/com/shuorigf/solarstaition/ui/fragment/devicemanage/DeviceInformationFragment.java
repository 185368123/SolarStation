package com.shuorigf.solarstaition.ui.fragment.devicemanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.DeviceInformationAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.ui.activity.EditingDeviceActivity;
import com.shuorigf.solarstaition.ui.fragment.MessageDialogFragment;
import com.shuorigf.solarstaition.util.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by clx on 2017/10/15.
 */

public class DeviceInformationFragment extends BaseFragment {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_device_information_content)
    RecyclerView mContentRv;

    private MessageDialogFragment mMessageDialogFragment;

    public static DeviceInformationFragment newInstance() {

        Bundle args = new Bundle();
        DeviceInformationFragment fragment = new DeviceInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * get layout resources id
     *
     * @return layoutRes
     */
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_device_information;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {

    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        List<DeviceListInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new DeviceListInfo());
        }
        DeviceInformationAdapter deviceInformationAdapter = new DeviceInformationAdapter(list);
        deviceInformationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.iv_swipe_edit:
                        Intent intent = new Intent(getContext(), EditingDeviceActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.iv_swipe_delete:
                        mMessageDialogFragment = MessageDialogFragment.newInstance();
                        mMessageDialogFragment.setShowTitle(false).setMessage("确定要删除吗")
                                    .setOnNegativeClickListener(new MessageDialogFragment.OnNegativeClickListener() {
                                        @Override
                                        public void onNegativeClick(MessageDialogFragment fragment, View cancel) {
                                            mMessageDialogFragment.dismiss();
                                        }
                                    });
                        mMessageDialogFragment.show(getActivity().getSupportFragmentManager(), "");
                        break;
                }
            }
        });
        mContentRv.addItemDecoration(new UniversalDividerItemDecoration(getContext(),
                UniversalDividerItemDecoration.HORIZONTAL_LIST,
                ConvertUtils.dp2px(getContext(), 10), ContextCompat.getColor(getContext(), R.color.divider)));
        mContentRv.setAdapter(deviceInformationAdapter);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mRefreshLayout.finishRefresh();
            }
        });
    }

}
