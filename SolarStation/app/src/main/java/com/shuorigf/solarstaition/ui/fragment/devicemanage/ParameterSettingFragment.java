package com.shuorigf.solarstaition.ui.fragment.devicemanage;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.ParameterSettingAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.device.ReadSettingInfo;
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

public class ParameterSettingFragment extends BaseFragment {

    @BindView(R.id.rv_parameter_setting_content)
    RecyclerView mContentRv;

    @BindArray(R.array.parameter_setting_title)
    TypedArray mTitle;

    private DeviceService mDeviceService;

    private String mDeviceId;

    public static ParameterSettingFragment newInstance() {

        Bundle args = new Bundle();
        ParameterSettingFragment fragment = new ParameterSettingFragment();
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
        return R.layout.fragment_parameter_setting;
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
        readSetting();
    }

    private void readSetting() {
        if (mDeviceId == null) {
            return;
        }
        Disposable disposable = mDeviceService.readSetting(mDeviceId)
                .compose(new HttpResultTransformer<ReadSettingInfo>())
                .subscribeWith(new DisposableSubscriber<ReadSettingInfo>() {
                    @Override
                    public void onNext(ReadSettingInfo readSettingInfo) {
                        initReadSettingInfo(readSettingInfo);
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

    private void initReadSettingInfo(ReadSettingInfo readSettingInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mTitle.length(); i++) {
            list.add(mTitle.getResourceId(i, 0));
        }
        ParameterSettingAdapter parameterSettingAdapter = new ParameterSettingAdapter(list, readSettingInfo);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(parameterSettingAdapter);
    }


    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mRxManager.on(Constants.REFSH_ALL_DEVICE_DATA, new Action1<String>() {
            @Override
            public void call(String o) {
                mDeviceId =o;
                initData();
            }
        });
    }
}
