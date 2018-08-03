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
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.params.station.StationSaveParams;
import com.shuorigf.solarstaition.util.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by clx on 2017/10/7.
 */

public class NewBuildPowerStationContentAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    @BindArray(R.array.battery_board_type_title)
    TypedArray mBatteryBoardTypeTitle;
    @BindArray(R.array.storage_battery_type_title)
    TypedArray mStorageBatteryTypeTitle;
    @BindArray(R.array.load_type_title)
    TypedArray mLoadTypeTitle;

    private StationSaveParams mStationSaveParams;

    public NewBuildPowerStationContentAdapter(List<Integer> data, StationSaveParams stationSaveParams) {
        super(R.layout.rv_item_new_build_power_station, data);
        this.mStationSaveParams = stationSaveParams;
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
        EditText content = baseViewHolder.getView(R.id.edt_content);
        switch (integer) {
            case R.string.install_date:
            case R.string.belong_to_project:
            case R.string.type_of_battery_board:
            case R.string.type_of_storage_battery:
            case R.string.load_type:
                select.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                break;
        }
        switch (integer) {
            case R.string.power_station_name:
                content.setText(mStationSaveParams.name);
                break;
            case R.string.install_date:
                select.setText(mStationSaveParams.installTime);
                break;
            case R.string.design_vendor:
                content.setText(mStationSaveParams.designer);
                break;
            case R.string.total_power_of_battery_board:
                content.setText(mStationSaveParams.panelPower);
                break;
            case R.string.the_quantity_of_battery_board_connected_in_series:
                content.setText(mStationSaveParams.panelSeriesCount);
                break;
            case R.string.the_quantity_of_battery_board_connected_in_parallel:
                content.setText(mStationSaveParams.panelParallelCount);
                break;
            case R.string.power_of_single_battery_board:
                content.setText(mStationSaveParams.panelSinglePower);
                break;
            case R.string.total_quantity_of_battery_board:
                content.setText(mStationSaveParams.panelCount);
                break;
            case R.string.total_open_circuit_voltage_of_battery_board:
                content.setText(mStationSaveParams.panelVoltage);
                break;
            case R.string.total_working_current_of_battery_board:
                content.setText(mStationSaveParams.panelCurrent);
                break;
            case R.string.single_battery_board_vmp:
                content.setText(mStationSaveParams.panelSingleVmp);
                break;
            case R.string.single_battery_board_voc:
                content.setText(mStationSaveParams.panelSingleVoc);
                break;
            case R.string.single_battery_board_imp:
                content.setText(mStationSaveParams.panelSingleImp);
                break;
            case R.string.single_battery_board_isc:
                content.setText(mStationSaveParams.panelSingleIsc);
                break;
            case R.string.battery_board_remark:
                content.setText(mStationSaveParams.panelRemark);
                break;
            case R.string.single_battery_voltage:
                content.setText(mStationSaveParams.batterySingleVoltage);
                break;
            case R.string.single_battery_capacity:
                content.setText(mStationSaveParams.batterySingleCapacity);
                break;
            case R.string.the_quantity_of_battery_connected_in_series:
                content.setText(mStationSaveParams.batterySeriesCount);
                break;
            case R.string.the_quantity_of_battery_connected_in_parallel:
                content.setText(mStationSaveParams.batteryParallelCount);
                break;
            case R.string.total_quantity_of_battery:
                content.setText(mStationSaveParams.batteryCount);
                break;
            case R.string.total_battery_voltage:
                content.setText(mStationSaveParams.batteryVoltage);
                break;
            case R.string.total_battery_capacity:
                content.setText(mStationSaveParams.batteryCapacity);
                break;
            case R.string.storage_battery_remark:
                content.setText(mStationSaveParams.batteryRemark);
                break;
            case R.string.load_power:
                content.setText(mStationSaveParams.loadPower);
                break;
            case R.string.address:
                content.setText(mStationSaveParams.address);
                break;
            case R.string.maximum_annual_temperature:
                content.setText(mStationSaveParams.maxAnnualTemper);
                break;
            case R.string.minimum_annual_temperature:
                content.setText(mStationSaveParams.minAnnualTemper);
                break;
            case R.string.geography_remark:
                content.setText(mStationSaveParams.geoRemark);
                break;
            case R.string.belong_to_project:
                for (int i = 0; i < SingleBeans.getInstance().getProjectListInfos().size(); i++) {
                    if (SingleBeans.getInstance().getProjectListInfos().get(i).projectId.equals(mStationSaveParams.projectId)){
                        select.setText(SingleBeans.getInstance().getProjectListInfos().get(i).projectName);
                    }
                }

                break;
            case R.string.type_of_battery_board:
                select.setText(mStationSaveParams.panelType);
                break;
            case R.string.type_of_storage_battery:
                select.setText(mStationSaveParams.batteryType);
                break;
            case R.string.load_type:
                select.setText(mStationSaveParams.loadType);
                break;
        }


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (integer) {
                    case R.string.install_date:
                        showTimePv(select);
                        break;
                    case R.string.belong_to_project:
                        showProjectTypePv(select);
                        break;
                    case R.string.type_of_battery_board:
                        showBatteryBoardTypePv(select);
                        break;
                    case R.string.type_of_storage_battery:
                        showStorageBatteryTypePv(select);
                        break;
                    case R.string.load_type:
                        showLoadTypeTypePv(select);
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
                    case R.string.power_station_name:
                        mStationSaveParams.name = editable.toString().trim();
                        break;
                    case R.string.install_date:
                        mStationSaveParams.installTime = editable.toString().trim();
                        break;
                    case R.string.design_vendor:
                        mStationSaveParams.designer = editable.toString().trim();
                        break;
                    case R.string.total_power_of_battery_board:
                        mStationSaveParams.panelPower = editable.toString().trim();
                        break;
                    case R.string.the_quantity_of_battery_board_connected_in_series:
                        mStationSaveParams.panelSeriesCount = editable.toString().trim();
                        break;
                    case R.string.the_quantity_of_battery_board_connected_in_parallel:
                        mStationSaveParams.panelParallelCount = editable.toString().trim();
                        break;
                    case R.string.power_of_single_battery_board:
                        mStationSaveParams.panelSinglePower = editable.toString().trim();
                        break;
                    case R.string.total_quantity_of_battery_board:
                        mStationSaveParams.panelCount = editable.toString().trim();
                        break;
                    case R.string.total_open_circuit_voltage_of_battery_board:
                        mStationSaveParams.panelVoltage = editable.toString().trim();
                        break;
                    case R.string.total_working_current_of_battery_board:
                        mStationSaveParams.panelCurrent = editable.toString().trim();
                        break;
                    case R.string.single_battery_board_vmp:
                        mStationSaveParams.panelSingleVmp = editable.toString().trim();
                        break;
                    case R.string.single_battery_board_voc:
                        mStationSaveParams.panelSingleVoc = editable.toString().trim();
                        break;
                    case R.string.single_battery_board_imp:
                        mStationSaveParams.panelSingleImp = editable.toString().trim();
                        break;
                    case R.string.single_battery_board_isc:
                        mStationSaveParams.panelSingleIsc = editable.toString().trim();
                        break;
                    case R.string.battery_board_remark:
                        mStationSaveParams.panelRemark = editable.toString().trim();
                        break;
                    case R.string.single_battery_voltage:
                        mStationSaveParams.batterySingleVoltage = editable.toString().trim();
                        break;
                    case R.string.single_battery_capacity:
                        mStationSaveParams.batterySingleCapacity = editable.toString().trim();
                        break;
                    case R.string.the_quantity_of_battery_connected_in_series:
                        mStationSaveParams.batterySeriesCount = editable.toString().trim();
                        break;
                    case R.string.the_quantity_of_battery_connected_in_parallel:
                        mStationSaveParams.batteryParallelCount = editable.toString().trim();
                        break;
                    case R.string.total_quantity_of_battery:
                        mStationSaveParams.batteryCount = editable.toString().trim();
                        break;
                    case R.string.total_battery_voltage:
                        mStationSaveParams.batteryVoltage = editable.toString().trim();
                        break;
                    case R.string.total_battery_capacity:
                        mStationSaveParams.batteryCapacity = editable.toString().trim();
                        break;
                    case R.string.storage_battery_remark:
                        mStationSaveParams.batteryRemark = editable.toString().trim();
                        break;
                    case R.string.load_power:
                        mStationSaveParams.loadPower = editable.toString().trim();
                        break;
                    case R.string.address:
                        mStationSaveParams.address = editable.toString().trim();
                        break;
                    case R.string.maximum_annual_temperature:
                        mStationSaveParams.maxAnnualTemper = editable.toString().trim();
                        break;
                    case R.string.minimum_annual_temperature:
                        mStationSaveParams.minAnnualTemper = editable.toString().trim();
                        break;
                    case R.string.geography_remark:
                        mStationSaveParams.geoRemark = editable.toString().trim();
                        break;
                }
            }
        });
    }
    private void showProjectTypePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                view.setText(SingleBeans.getInstance().getProjectListInfos().get(options1).projectName);
                mStationSaveParams.projectId=SingleBeans.getInstance().getProjectListInfos().get(options1).projectId;
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
        for (int i = 0; i < SingleBeans.getInstance().getProjectListInfos().size(); i++) {
            list.add(SingleBeans.getInstance().getProjectListInfos().get(i).projectName);
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }
    private void showBatteryBoardTypePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mStationSaveParams.panelType = String.valueOf(options1);
                view.setText(mBatteryBoardTypeTitle.getString(options1));
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
        for (int i = 0; i < mBatteryBoardTypeTitle.length(); i++) {
            list.add(mBatteryBoardTypeTitle.getString(i));
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }

    private void showStorageBatteryTypePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mStationSaveParams.batteryType = String.valueOf(options1);
                view.setText(mStorageBatteryTypeTitle.getString(options1));
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
        for (int i = 0; i < mStorageBatteryTypeTitle.length(); i++) {
            list.add(mStorageBatteryTypeTitle.getString(i));
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }

    private void showLoadTypeTypePv(final TextView view) {
        OptionsPickerView mOptionsPickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mStationSaveParams.loadType = String.valueOf(options1);
                view.setText(mLoadTypeTitle.getString(options1));
            }
        })
                .setSubmitColor(ContextCompat.getColor(mContext, R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(mContext, R.color.textBlue))//取消按钮文字颜色
                .setCyclic(false, false, false)//循环与否
                .build();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < mLoadTypeTitle.length(); i++) {
            list.add(mLoadTypeTitle.getString(i));
        }

        mOptionsPickerView.setPicker(list);//添加数据源
        mOptionsPickerView.show();

    }

    private void showTimePv(final TextView view) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 11, 31);
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(TimeUtils.date2String(date));
                mStationSaveParams.installTime=TimeUtils.date2String(date);
            }
        })
                .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setSubmitColor(ContextCompat.getColor(mContext, R.color.textBlue))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(mContext, R.color.textBlue))//取消按钮文字颜色
                .isCyclic(false)//循环与否
                .isCenterLabel(true)
                .setDate(Calendar.getInstance())// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
        pvTime.show();
    }
}
