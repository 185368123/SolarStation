package com.shuorigf.solarstaition.adapter;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.params.device.DeviceSaveParams;
import com.shuorigf.solarstaition.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by clx on 2017/10/16.
 */

public class EditingDeviceAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    @BindArray(R.array.device_type_title)
    TypedArray device_type_title;

    private DeviceSaveParams deviceSaveParams;

    public EditingDeviceAdapter(List<Integer> data, DeviceSaveParams deviceSaveParams) {
        super(R.layout.rv_item_simple_edit_content, data);
        this.deviceSaveParams = deviceSaveParams;
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
        final TextView select = baseViewHolder.getView(R.id.tv_select);
        final EditText content = baseViewHolder.getView(R.id.edt_content);
        switch (integer) {
            case R.string.product_model:
                content.setText(deviceSaveParams.model);
                break;
            case R.string.product_type:
                select.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                if (deviceSaveParams.type!=null){
                    if (deviceSaveParams.type.equals("0")){
                        select.setText(R.string.device_type_0);
                    }else {
                        select.setText(R.string.device_type_1);
                    }
                }
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

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (integer) {
                    case R.string.product_type:
                        showTypedPv(select);
                        break;
                }
            }
        });
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
                    case R.string.device_pn_number:
                        deviceSaveParams.pn_code = editable.toString().trim();
                        break;

                }
            }
        });
    }

    private void showTypedPv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                view.setText(device_type_title.getString(options1));
                deviceSaveParams.type=options1+"";
            }
        })
                .setSubCalSize(15)//确定和取消文字大小
                .setSubmitColor(ContextCompat.getColor(mContext, R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(mContext, R.color.textBlue))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(mContext, R.color.white))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .build();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < device_type_title.length(); i++) {
            list.add(device_type_title.getString(i));
        }
        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();
    }
}
