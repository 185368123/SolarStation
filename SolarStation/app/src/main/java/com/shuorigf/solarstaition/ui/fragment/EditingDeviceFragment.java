package com.shuorigf.solarstaition.ui.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.EditingDeviceAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.params.device.DeviceSaveParams;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/10/17.
 */

public class EditingDeviceFragment extends BaseFragment {


    @BindView(R.id.rv_editing_device_content)
    RecyclerView mContentRv;

    @BindArray(R.array.edit_device_title)
    TypedArray mTitle;

    private DeviceService mDeviceService;

    private DeviceSaveParams mDeviceSaveParams = new DeviceSaveParams();

    public static EditingDeviceFragment newInstance() {
        Bundle args = new Bundle();
        EditingDeviceFragment fragment = new EditingDeviceFragment();
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
        return R.layout.fragment_editing_device;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mContentRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        DeviceListInfo deviceListInfo = getActivity().getIntent().getParcelableExtra(Constants.DEVICE_INFO);
        if (deviceListInfo != null) {
            mDeviceSaveParams.id = deviceListInfo.id;
            mDeviceSaveParams.model = deviceListInfo.model;
            mDeviceSaveParams.type = deviceListInfo.type;
            mDeviceSaveParams.address = deviceListInfo.address;
            mDeviceSaveParams.swVersion = deviceListInfo.swVersion;
            mDeviceSaveParams.hwVersion = deviceListInfo.hwVersion;
            mDeviceSaveParams.serialNo = deviceListInfo.serialNo;
        }
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mTitle.length(); i++) {
            list.add(mTitle.getResourceId(i, 0));
        }
        EditingDeviceAdapter editingDeviceAdapter = new EditingDeviceAdapter(list, mDeviceSaveParams);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(editingDeviceAdapter);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.btn_editing_device_save)
    public void onViewClicked() {
        save();
    }


    private void save() {

        if (TextUtils.isEmpty(mDeviceSaveParams.model)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_product_model);
            return;
        }
        if (TextUtils.isEmpty(mDeviceSaveParams.type)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_product_type);
            return;
        }
        if (TextUtils.isEmpty(mDeviceSaveParams.swVersion)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_software_version);
            return;
        }

        if (TextUtils.isEmpty(mDeviceSaveParams.hwVersion)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_hardware_version);
            return;
        }

        if (TextUtils.isEmpty(mDeviceSaveParams.serialNo)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_device_serial_number);
            return;
        }
        Map<String, String> map = new HashMap<>();
        if (mDeviceSaveParams.id != null) {
            map.put(DeviceSaveParams.ID, mDeviceSaveParams.id);
        }
        map.put(DeviceSaveParams.MODEL, mDeviceSaveParams.model);
        map.put(DeviceSaveParams.TYPE, mDeviceSaveParams.type);
        map.put(DeviceSaveParams.SW_VERSION, mDeviceSaveParams.swVersion);
        map.put(DeviceSaveParams.HW_VERSION, mDeviceSaveParams.hwVersion);
        map.put(DeviceSaveParams.ADDRESS, mDeviceSaveParams.address);
        map.put(DeviceSaveParams.SERIAL_NO, mDeviceSaveParams.serialNo);

        Disposable disposable = mDeviceService.saveDevice(map)
                .compose(new HttpResultLoadingTransformer<SaveInfo>())
                .subscribeWith(new DisposableSubscriber<SaveInfo>() {
                    @Override
                    public void onNext(SaveInfo s) {
                        ToastUtil.showShortToast(getContext(), R.string.save_success);
                        mRxManager.post(Constants.REFSH_DEVICE_DATA,null);
                        getActivity().finish();
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
}
