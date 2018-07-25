package com.shuorigf.solarstaition.ui.fragment.devicemanage;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.RealTimeMonitoringAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.device.RealTimeDataInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
import com.shuorigf.solarstaition.util.ConvertUtils;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.functions.Action1;

/**
 * Created by clx on 2017/10/15.
 */

public class RealTimeMonitoringFragment extends BaseFragment {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_real_time_monitoring_content)
    RecyclerView mContentRv;

    @BindArray(R.array.real_time_monitoring_title)
    TypedArray mTitle;

    private DeviceService mDeviceService;

    private String mDeviceId;

    public static RealTimeMonitoringFragment newInstance() {

        Bundle args = new Bundle();
        RealTimeMonitoringFragment fragment = new RealTimeMonitoringFragment();
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
        return R.layout.fragment_real_time_monitoring;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mContentRv.addItemDecoration(new UniversalDividerItemDecoration(getContext(),
                UniversalDividerItemDecoration.HORIZONTAL_LIST,
                ConvertUtils.dp2px(getContext(), 10), ContextCompat.getColor(getContext(), R.color.divider)));
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mDeviceId = getActivity().getIntent().getStringExtra(Constants.DEVICE_ID);
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getRealTimeData();
    }

    private void getRealTimeData() {
        if (mDeviceId == null) {
            return;
        }
        Disposable disposable = mDeviceService.getRealTimeData(mDeviceId)
                .compose(new HttpResultTransformer<RealTimeDataInfo>())
                .subscribeWith(new DisposableSubscriber<RealTimeDataInfo>() {
                    @Override
                    public void onNext(RealTimeDataInfo realTimeDataInfo) {
                        initRealTimeDataInfo(realTimeDataInfo);
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

    private void initRealTimeDataInfo(RealTimeDataInfo realTimeDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<mTitle.length(); i++) {
            list.add(mTitle.getResourceId(i, 0));
        }
        RealTimeMonitoringAdapter realTimeMonitoringAdapter = new RealTimeMonitoringAdapter(list, realTimeDataInfo);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(realTimeMonitoringAdapter);
    }


    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getRealTimeData();
            }
        });

        mRxManager.on(Constants.REFSH_ALL_DEVICE_DATA, new Action1<String>() {
            @Override
            public void call(String o) {
                mDeviceId=o;
                initData();
            }
        });
    }
}
