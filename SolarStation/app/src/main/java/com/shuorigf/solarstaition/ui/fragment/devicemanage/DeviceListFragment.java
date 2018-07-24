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
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
import com.shuorigf.solarstaition.ui.activity.EditingDeviceActivity;
import com.shuorigf.solarstaition.ui.fragment.MessageDialogFragment;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.LogUtils;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.functions.Action1;

/**
 * Created by clx on 2017/10/15.
 */

public class DeviceListFragment extends BaseFragment {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_device_list_content)
    RecyclerView mContentRv;

    private DeviceService mDeviceService;

    private String mStationId;

    public static DeviceListFragment newInstance() {

        Bundle args = new Bundle();
        DeviceListFragment fragment = new DeviceListFragment();
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
        return R.layout.fragment_device_list;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mStationId = getActivity().getIntent().getStringExtra(Constants.STATION_ID);
        mContentRv.addItemDecoration(new UniversalDividerItemDecoration(getContext(),
                UniversalDividerItemDecoration.HORIZONTAL_LIST,
                ConvertUtils.dp2px(getContext(), 10), ContextCompat.getColor(getContext(), R.color.divider)));
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getDeviceList();
    }

    private void getDeviceList() {
        if (mStationId == null) {
            return;
        }
        Disposable disposable = mDeviceService.getDeviceList(mStationId)
                .compose(new HttpResultTransformer<List<DeviceListInfo>>())
                .subscribeWith(new DisposableSubscriber<List<DeviceListInfo>>() {
                    @Override
                    public void onNext(List<DeviceListInfo> list) {
                        initDeviceList(list);
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(getContext(), response.getErrorMessage());
                        }
                        if (mRefreshLayout.isRefreshing()) {
                            mRefreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mRefreshLayout.isRefreshing()) {
                            mRefreshLayout.finishRefresh();
                        }
                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }

    private void initDeviceList(List<DeviceListInfo> list) {
        DeviceInformationAdapter deviceInformationAdapter = new DeviceInformationAdapter(list);
        deviceInformationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final DeviceListInfo deviceListInfo = (DeviceListInfo)adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.iv_swipe_edit:
                        Intent intent = new Intent(getContext(), EditingDeviceActivity.class);
                        intent.putExtra(Constants.DEVICE_INFO, deviceListInfo);
                        startActivity(intent);
                        break;

                    case R.id.iv_swipe_delete:
                        MessageDialogFragment mMessageDialogFragment = MessageDialogFragment.newInstance();
                        mMessageDialogFragment.setShowTitle(false).setMessage(getString(R.string.hint_delete))
                                .setOnNegativeClickListener(new MessageDialogFragment.OnNegativeClickListener() {
                                    @Override
                                    public void onNegativeClick(MessageDialogFragment fragment, View cancel) {
                                        LogUtils.logd("取消删除");
                                        fragment.dismiss();
                                    }
                                })
                                .setOnPositiveClickListener(new MessageDialogFragment.OnPositiveClickListener() {
                                    @Override
                                    public void onPositiveClick(MessageDialogFragment fragment, View ok) {
                                        LogUtils.logd("确定删除");
                                        delDevice(deviceListInfo.id);
                                        fragment.dismiss();
                                    }
                        });
                        mMessageDialogFragment.show(getChildFragmentManager(), "");
                        break;
                }
            }
        });
        mContentRv.setAdapter(deviceInformationAdapter);

    }


    private void delDevice(String deviceId) {
        if (deviceId == null) {
            return;
        }
        Disposable disposable = mDeviceService.delDevice(deviceId)
                .compose(new HttpResultLoadingTransformer<Void>())
                .subscribeWith(new DisposableSubscriber<Void>() {
                    @Override
                    public void onNext(Void s) {
                        ToastUtil.showShortToast(getContext(), R.string.delete_success);
                        mRxManager.post(Constants.REFSH_DEVICE_DATA,null);
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(getContext(), response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getDeviceList();
            }
        });

        mRxManager.on(Constants.REFSH_DEVICE_DATA, new Action1<Object>() {
            @Override
            public void call(Object o) {
                getDeviceList();
            }
        });
    }

}
