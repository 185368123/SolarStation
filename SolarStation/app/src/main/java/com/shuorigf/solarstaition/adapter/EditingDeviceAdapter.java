package com.shuorigf.solarstaition.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.params.device.DeviceSaveParams;

import java.util.List;

/**
 * Created by clx on 2017/10/16.
 */

public class EditingDeviceAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private DeviceSaveParams deviceSaveParams;

    public EditingDeviceAdapter(List<Integer> data, DeviceSaveParams deviceSaveParams) {
        super(R.layout.rv_item_simple_edit_content, data);
        this.deviceSaveParams = deviceSaveParams;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Integer integer) {
        baseViewHolder.setText(R.id.tv_title, integer);
        EditText content = baseViewHolder.getView(R.id.edt_content);
        switch (integer) {
            case R.string.product_model:
                content.setText(deviceSaveParams.model);
                break;
            case R.string.product_type:
                content.setText(deviceSaveParams.type);
                break;
            case R.string.software_version:
                content.setText(deviceSaveParams.swVersion);
                break;
            case R.string.hardware_version:
                content.setText(deviceSaveParams.hwVersion);
                break;
            case R.string.device_address:
                content.setText(deviceSaveParams.address);
                break;
            case R.string.device_serial_number:
                content.setText(deviceSaveParams.serialNo);
                break;
        }

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                switch (integer) {
                    case R.string.product_model:
                        deviceSaveParams.model = editable.toString().trim();
                        break;
                    case R.string.product_type:
                        deviceSaveParams.type = editable.toString().trim();
                        break;
                    case R.string.software_version:
                        deviceSaveParams.swVersion = editable.toString().trim();
                        break;
                    case R.string.hardware_version:
                        deviceSaveParams.hwVersion = editable.toString().trim();
                        break;
                    case R.string.device_address:
                        deviceSaveParams.address = editable.toString().trim();
                        break;
                    case R.string.device_serial_number:
                        deviceSaveParams.serialNo = editable.toString().trim();
                        break;

                }
            }
        });
    }
}
