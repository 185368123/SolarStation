package com.shuorigf.solarstaition.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.EditingDeviceAdapter;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.params.device.DeviceSaveParams;
import com.shuorigf.solarstaition.data.response.SaveInfo;
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

public class AddDeviceActivity extends BaseActivity {
    @BindArray(R.array.edit_device_title)
    TypedArray mTitle;

    @BindView(R.id.tv_add_device_title)
    TextView tvAddDeviceTitle;
    @BindView(R.id.rv_add_device_content)
    RecyclerView rvAddDeviceContent;

    private DeviceService mDeviceService;

    private DeviceSaveParams mDeviceSaveParams = new DeviceSaveParams();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_add_device;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        tvAddDeviceTitle.setText(R.string.add_device);
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        rvAddDeviceContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDeviceSaveParams.project_id=getIntent().getStringExtra(Constants.PROJECT_ID);
        mDeviceSaveParams.station_id=getIntent().getStringExtra(Constants.STATION_ID);
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
        list.add(R.string.device_pn_number);
        EditingDeviceAdapter editingDeviceAdapter = new EditingDeviceAdapter(list, mDeviceSaveParams);
        rvAddDeviceContent.setItemViewCacheSize(list.size());
        rvAddDeviceContent.setAdapter(editingDeviceAdapter);
    }

    @OnClick(R.id.btn_add_device_content)
    public void onViewClicked() {
        add();
    }

    private void add() {
        if (TextUtils.isEmpty(mDeviceSaveParams.model)) {
            ToastUtil.showShortToast(this, R.string.hint_product_model);
            return;
        }
        if (TextUtils.isEmpty(mDeviceSaveParams.type)) {
            ToastUtil.showShortToast(this, R.string.hint_product_type);
            return;
        }
        if (TextUtils.isEmpty(mDeviceSaveParams.swVersion)) {
            ToastUtil.showShortToast(this, R.string.hint_software_version);
            return;
        }

        if (TextUtils.isEmpty(mDeviceSaveParams.hwVersion)) {
            ToastUtil.showShortToast(this, R.string.hint_hardware_version);
            return;
        }
        if (TextUtils.isEmpty(mDeviceSaveParams.pn_code)) {
            ToastUtil.showShortToast(this, R.string.hint_device_pn_number);
            return;
        }
        if (TextUtils.isEmpty(mDeviceSaveParams.serialNo)) {
            ToastUtil.showShortToast(this, R.string.hint_device_serial_number);
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
        map.put(DeviceSaveParams.STATION_ID, mDeviceSaveParams.station_id);
        map.put(DeviceSaveParams.PROJECT_ID, mDeviceSaveParams.project_id);
        map.put(DeviceSaveParams.PN_CODE, mDeviceSaveParams.pn_code);
        Disposable disposable = mDeviceService.saveDevice(map)
                .compose(new HttpResultLoadingTransformer<SaveInfo>())
                .subscribeWith(new DisposableSubscriber<SaveInfo>() {
                    @Override
                    public void onNext(SaveInfo s) {
                        mRxManager.post(Constants.REFSH_DEVICE_DATA,null);
                        ToastUtil.showShortToast(AddDeviceActivity.this, R.string.add_success);
                        finish();
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(AddDeviceActivity.this, response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }
}
