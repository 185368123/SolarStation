package com.shuorigf.solarstaition.ui.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.NewBuildPowerStationAdapter;
import com.shuorigf.solarstaition.base.BaseActivity;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.params.station.StationSaveParams;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class StationInformationChangeActivity extends BaseActivity {
    @BindArray(R.array.power_station_information_title)
    TypedArray mTitle;

    @BindView(R.id.rv_change_station_information)
    RecyclerView rvChangeStationInformation;
    @BindView(R.id.btn_save_station_information)
    Button btnSaveStationInformation;
    @BindView(R.id.tv_change_station_information_title)
    TextView tvChangeStationInformationTitle;

    private int title;
    private StationService mStationService;
    private StationSaveParams stationSaveParams;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_station_information_change;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        title = getIntent().getIntExtra(Constants.INFORMATION_CHANGE, 0);
        mStationService = RetrofitUtil.create(StationService.class);
        stationSaveParams = SingleBeans.getInstance().getSaveParams();

        tvChangeStationInformationTitle.setText(title);
        List<Integer> list = new ArrayList<>();
        list.add(title);

        NewBuildPowerStationAdapter newBuildPowerStationAdapter = new NewBuildPowerStationAdapter(list, stationSaveParams);
        rvChangeStationInformation.setItemViewCacheSize(list.size());
        rvChangeStationInformation.setAdapter(newBuildPowerStationAdapter);
        rvChangeStationInformation.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.btn_save_station_information)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>();
        stationSaveParams.photo=stationSaveParams.photo.replace("http://station-iot.com","");
        map.put(StationSaveParams.ID, stationSaveParams.id);
        map.put(StationSaveParams.NAME, stationSaveParams.name);
        map.put(StationSaveParams.PROJECT_ID, stationSaveParams.projectId);
        map.put(StationSaveParams.INSTALL_TIME, stationSaveParams.installTime);
        map.put(StationSaveParams.DESIGNER, stationSaveParams.designer);
        map.put(StationSaveParams.PHOTO, stationSaveParams.photo);
        map.put(StationSaveParams.PANEL_POWER, stationSaveParams.panelPower);
        map.put(StationSaveParams.PANEL_TYPE, stationSaveParams.panelType + "");
        map.put(StationSaveParams.PANEL_SERIES_COUNT, stationSaveParams.panelSeriesCount);
        map.put(StationSaveParams.PANEL_PARALLEL_COUNT, stationSaveParams.panelParallelCount);
        map.put(StationSaveParams.PANEL_COUNT, stationSaveParams.panelCount);
        map.put(StationSaveParams.PANEL_VOLTAGE, stationSaveParams.panelVoltage);
        map.put(StationSaveParams.PANEL_CURRENT, stationSaveParams.panelCurrent);
        map.put(StationSaveParams.PANEL_SINGLE_POWER, stationSaveParams.panelSinglePower);
        map.put(StationSaveParams.PANEL_SINGLE_VMP, stationSaveParams.panelSingleVmp);
        map.put(StationSaveParams.PANEL_SINGLE_VOC, stationSaveParams.panelSingleVoc);
        map.put(StationSaveParams.PANEL_SINGLE_IMP, stationSaveParams.panelSingleImp);
        map.put(StationSaveParams.PANEL_SINGLE_ISC, stationSaveParams.panelSingleIsc);
        map.put(StationSaveParams.PANEL_REMARK, stationSaveParams.panelRemark);
        map.put(StationSaveParams.BATTERY_TYPE, stationSaveParams.batteryType + "");
        map.put(StationSaveParams.BATTERY_SINGLE_VOLTAGE, stationSaveParams.batterySingleVoltage);
        map.put(StationSaveParams.BATTERY_SINGLE_CAPACITY, stationSaveParams.batterySingleCapacity);
        map.put(StationSaveParams.BATTERY_SERIES_COUNT, stationSaveParams.batterySeriesCount);
        map.put(StationSaveParams.BATTERY_PARALLEL_COUNT, stationSaveParams.batteryParallelCount);
        map.put(StationSaveParams.BATTERY_COUNT, stationSaveParams.batteryCount);
        map.put(StationSaveParams.BATTERY_VOLTAGE, stationSaveParams.batteryVoltage);
        map.put(StationSaveParams.BATTERY_CAPACITY, stationSaveParams.batteryCapacity);
        map.put(StationSaveParams.BATTERY_REMARK, stationSaveParams.batteryRemark);
        map.put(StationSaveParams.LOAD_TYPE, stationSaveParams.loadType + "");
        map.put(StationSaveParams.LOAD_POWER, stationSaveParams.loadPower);
        map.put(StationSaveParams.COUNTRY_ID, stationSaveParams.countryId);
        map.put(StationSaveParams.PROVINCE_ID, stationSaveParams.provinceId);
        map.put(StationSaveParams.ADDRESS, stationSaveParams.address);
        map.put(StationSaveParams.LONGITUDE, stationSaveParams.longitude);
        map.put(StationSaveParams.LATITUDE, stationSaveParams.latitude);
        map.put(StationSaveParams.ALTITUDE, stationSaveParams.altitude);
        map.put(StationSaveParams.MAX_ANNUAL_TEMPER, stationSaveParams.maxAnnualTemper);
        map.put(StationSaveParams.MIN_ANNUAL_TEMPER, stationSaveParams.minAnnualTemper);
        map.put(StationSaveParams.GEO_REMARK, stationSaveParams.geoRemark);


        Disposable disposable = mStationService.saveStation(map)
                .compose(new HttpResultLoadingTransformer<SaveInfo>())
                .subscribeWith(new DisposableSubscriber<SaveInfo>() {
                    @Override
                    public void onNext(SaveInfo s) {
                        ToastUtil.showShortToast(StationInformationChangeActivity.this, R.string.save_success);
                        mRxManager.post(Constants.INIT_STATION_DETAIL,null);
                        finish();
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ResponseMessageException) {
                            ResponseMessageException response = (ResponseMessageException) t;
                            ToastUtil.showShortToast(StationInformationChangeActivity.this, response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }

}
