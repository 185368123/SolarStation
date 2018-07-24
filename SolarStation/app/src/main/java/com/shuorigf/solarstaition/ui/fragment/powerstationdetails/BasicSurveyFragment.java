package com.shuorigf.solarstaition.ui.fragment.powerstationdetails;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.CommonFragmentPagerAdapter;
import com.shuorigf.solarstaition.adapter.PowerStationChartAdapter;
import com.shuorigf.solarstaition.adapter.PowerStationDetailsDeviceAdapter;
import com.shuorigf.solarstaition.adapter.PowerStationElectricityAdapter;
import com.shuorigf.solarstaition.adapter.PowerStationSaveAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.IconText;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.response.device.DeviceListInfo;
import com.shuorigf.solarstaition.data.response.station.StationDataInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.activity.AddDeviceActivity;
import com.shuorigf.solarstaition.ui.activity.DeviceListActivity;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.functions.Action1;

/**
 * Created by clx on 2017/10/12.
 */

public class BasicSurveyFragment extends BaseFragment {

    @BindView(R.id.basic_survey_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.basic_survey_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.rv_basic_survey_electricity)
    RecyclerView mElectricityRv;
    @BindView(R.id.rv_basic_survey_chart)
    RecyclerView mChartRv;
    @BindView(R.id.rv_basic_survey_save)
    RecyclerView mSaveRv;
    @BindView(R.id.rv_basic_survey_device)
    RecyclerView mDeviceRv;

    @BindArray(R.array.power_station_electricity_title)
    TypedArray mElectricityTitle;

    @BindArray(R.array.power_station_chart_title)
    TypedArray mChartTitle;

    @BindArray(R.array.power_station_save_title)
    TypedArray mSaveTitle;

    @BindArray(R.array.power_station_save_icon)
    TypedArray mSaveIcon;

    @BindArray(R.array.basic_survey_chart_title)
    TypedArray mPowerTitle;
    private DeviceService mDeviceService;
    private StationService mStationService;

    private String mStationId;


    public static BasicSurveyFragment newInstance() {

        Bundle args = new Bundle();
        BasicSurveyFragment fragment = new BasicSurveyFragment();
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
        return R.layout.fragment_basic_survey;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mElectricityRv.setNestedScrollingEnabled(false);
        mChartRv.setNestedScrollingEnabled(false);
        mSaveRv.setNestedScrollingEnabled(false);
        mDeviceRv.setNestedScrollingEnabled(false);
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mStationService = RetrofitUtil.create(StationService.class);
        mStationId = getActivity().getIntent().getStringExtra(Constants.STATION_ID);
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        initVP();
        getStationData();
        getDeviceList();
    }

    private void getStationData() {
        if (mStationId == null) {
            return;
        }
        Disposable disposable = mStationService.getStationData(mStationId)
                .compose(new HttpResultTransformer<StationDataInfo>())
                .subscribeWith(new DisposableSubscriber<StationDataInfo>() {
                    @Override
                    public void onNext(StationDataInfo stationDataInfo) {
                        initElectricity(stationDataInfo);
                        initChart(stationDataInfo);
                        initSave(stationDataInfo);
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
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        DisposableManager.getInstance().add(this, disposable);

    }

    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mPowerTitle.length(); i++) {
            fragments.add(PowerChartFragment.newInstance(mPowerTitle.getResourceId(i, 0)));
        }

        mViewpager.setAdapter(new CommonFragmentPagerAdapter(getChildFragmentManager(), fragments));
        mViewpager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_change_tab, null, false);
            if (mTabLayout.getTabAt(i) != null)
                mTabLayout.getTabAt(i).setCustomView(view);
        }

    }

    private void initElectricity(StationDataInfo stationDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mElectricityTitle.length(); i++) {
            list.add(mElectricityTitle.getResourceId(i, 0));
        }
        PowerStationElectricityAdapter powerStationElectricityAdapter = new PowerStationElectricityAdapter(list, stationDataInfo);
        mElectricityRv.setItemViewCacheSize(list.size());
        mElectricityRv.setAdapter(powerStationElectricityAdapter);

    }

    private void initChart(StationDataInfo stationDataInfo) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mChartTitle.length(); i++) {
            list.add(mChartTitle.getResourceId(i, 0));
        }
        PowerStationChartAdapter powerStationChartAdapter = new PowerStationChartAdapter(list, stationDataInfo);
        mChartRv.setItemViewCacheSize(list.size());
        mChartRv.setAdapter(powerStationChartAdapter);

    }

    private void initSave(StationDataInfo stationDataInfo) {
        List<IconText> list = new ArrayList<>();
        for (int i = 0; i < mSaveTitle.length(); i++) {
            list.add(new IconText(mSaveTitle.getResourceId(i, 0), mSaveIcon.getResourceId(i, 0)));
        }
        PowerStationSaveAdapter powerStationSaveAdapter = new PowerStationSaveAdapter(list, stationDataInfo);
        mSaveRv.setItemViewCacheSize(list.size());
        mSaveRv.setAdapter(powerStationSaveAdapter);

    }

    private void initDeviceList(List<DeviceListInfo> list) {
        PowerStationDetailsDeviceAdapter powerStationDetailsDeviceAdapter = new PowerStationDetailsDeviceAdapter(list,mStationId);
        View headerView = View.inflate(getContext(), R.layout.rv_item_power_station_details_device_header, null);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddDeviceActivity.class);
                intent.putExtra(Constants.STATION_ID, mStationId);
                intent.putExtra(Constants.PROJECT_ID, getActivity().getIntent().getStringExtra(Constants.PROJECT_ID));
                startActivity(intent);
            }
        });
        powerStationDetailsDeviceAdapter.setHeaderViewAsFlow(true);
        powerStationDetailsDeviceAdapter.addHeaderView(headerView);
        mDeviceRv.setAdapter(powerStationDetailsDeviceAdapter);

    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mRxManager.on(Constants.REFSH_DEVICE_DATA, new Action1<Object>() {
            @Override
            public void call(Object o) {
               getDeviceList();
            }
        });
    }


    @OnClick(R.id.tv_basic_survey_view_details)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), DeviceListActivity.class);
        intent.putExtra(Constants.STATION_ID, mStationId);
        startActivity(intent);
    }
}
