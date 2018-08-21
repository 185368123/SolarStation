package com.shuorigf.solarstaition.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.shuorigf.solarstaition.GlideApp;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.NewBuildPowerStationAdapter;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.params.station.StationSaveParams;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.response.common.UrlInfo;
import com.shuorigf.solarstaition.data.service.CommonService;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.PictureSelectorUtils;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by clx on 2017/10/15.
 */

public class NewBuildPowerStationFragment extends BaseFragment {

    @BindArray(R.array.power_station_information_title)
    TypedArray mTitle;
    @BindView(R.id.rv_new_build_power_station_content)
    RecyclerView mContentRv;
    @BindView(R.id.iv_new_build_power_station_select_photo)
    ImageView mIconIv;

    private StationService mStationService;
    private CommonService mCommonService;

    private StationSaveParams mStationSaveParams = new StationSaveParams();


    public static NewBuildPowerStationFragment newInstance() {

        Bundle args = new Bundle();
        NewBuildPowerStationFragment fragment = new NewBuildPowerStationFragment();
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
        return R.layout.fragment_new_build_power_station;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mStationService = RetrofitUtil.create(StationService.class);
        mCommonService = RetrofitUtil.create(CommonService.class);
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
        NewBuildPowerStationAdapter newBuildPowerStationAdapter = new NewBuildPowerStationAdapter(list, mStationSaveParams);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(newBuildPowerStationAdapter);
        mContentRv.setNestedScrollingEnabled(false);
    }


    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.iv_new_build_power_station_select_photo, R.id.btn_new_build_power_station_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_new_build_power_station_select_photo:
                int cropWH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
                PictureSelectorUtils.intoPictureSelector(this, cropWH, cropWH, 2, 2);
                break;
            case R.id.btn_new_build_power_station_save:
                save();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0) {
                        Log.e("111", "onActivityResult: " + selectList.get(0).getCompressPath());
                        imagesUpload(selectList.get(0).getCompressPath());
                    }
                    break;
            }
        }
    }


    private void imagesUpload(String imgPath) {
        File imgFile = new File(imgPath);
        if (!imgFile.exists()) {
            return;
        }
        Disposable disposable = mCommonService.uploadFile(RequestBody.create(MediaType.parse("multipart/form-data"),
                "0"), RequestBody.create(MediaType.parse("multipart/form-data"), imgFile))
                .compose(new HttpResultTransformer<UrlInfo>())
                .subscribeWith(new DisposableSubscriber<UrlInfo>() {
                    @Override
                    public void onNext(UrlInfo urlInfo) {
                        Log.e("111", "onNext: " + ApiConstants.IMAGE_URL + urlInfo.url);
                        mStationSaveParams.photo = urlInfo.url;
                        GlideApp.with(getContext())
                                .load(ApiConstants.IMAGE_URL + urlInfo.url)
                                .placeholder(R.mipmap.ic_select_photo)
                                .error(R.mipmap.ic_select_photo)
                                .into(mIconIv);
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
                        PictureFileUtils.deleteCacheDirFile(getContext());
                    }
                });
        DisposableManager.getInstance().add(this, disposable);
    }


    private void save() {
        if (TextUtils.isEmpty(mStationSaveParams.name)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_power_station_name);
            return;
        }
        if (TextUtils.isEmpty(mStationSaveParams.projectId)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_belong_to_project);
            return;
        }
        if (TextUtils.isEmpty(mStationSaveParams.installTime)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_install_date);
            return;
        }
        if (TextUtils.isEmpty(mStationSaveParams.designer)) {
            ToastUtil.showShortToast(getContext(), R.string.hint_design_vendor);
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put(StationSaveParams.NAME, mStationSaveParams.name);
        map.put(StationSaveParams.PROJECT_ID, mStationSaveParams.projectId);
        map.put(StationSaveParams.INSTALL_TIME, mStationSaveParams.installTime);
        map.put(StationSaveParams.DESIGNER, mStationSaveParams.designer);

        if (!TextUtils.isEmpty(mStationSaveParams.photo)) {
            map.put(StationSaveParams.PHOTO, mStationSaveParams.photo);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelPower)) {
            map.put(StationSaveParams.PANEL_POWER, mStationSaveParams.panelPower);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelType)) {
            map.put(StationSaveParams.PANEL_TYPE, mStationSaveParams.panelType + "");
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelSeriesCount)) {
            map.put(StationSaveParams.PANEL_SERIES_COUNT, mStationSaveParams.panelSeriesCount);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelParallelCount)) {
            map.put(StationSaveParams.PANEL_PARALLEL_COUNT, mStationSaveParams.panelParallelCount);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelCount)) {
            map.put(StationSaveParams.PANEL_COUNT, mStationSaveParams.panelCount);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelVoltage)) {
            map.put(StationSaveParams.PANEL_VOLTAGE, mStationSaveParams.panelVoltage);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelCurrent)) {
            map.put(StationSaveParams.PANEL_CURRENT, mStationSaveParams.panelCurrent);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelSinglePower)) {
            map.put(StationSaveParams.PANEL_SINGLE_POWER, mStationSaveParams.panelSinglePower);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelSingleVmp)) {
            map.put(StationSaveParams.PANEL_SINGLE_VMP, mStationSaveParams.panelSingleVmp);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelSingleVoc)) {
            map.put(StationSaveParams.PANEL_SINGLE_VOC, mStationSaveParams.panelSingleVoc);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelSingleImp)) {
            map.put(StationSaveParams.PANEL_SINGLE_IMP, mStationSaveParams.panelSingleImp);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelSingleIsc)) {
            map.put(StationSaveParams.PANEL_SINGLE_ISC, mStationSaveParams.panelSingleIsc);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.panelRemark)) {
            map.put(StationSaveParams.PANEL_REMARK, mStationSaveParams.panelRemark);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batteryType)) {
            map.put(StationSaveParams.BATTERY_TYPE, mStationSaveParams.batteryType + "");
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batterySingleVoltage)) {
            map.put(StationSaveParams.BATTERY_SINGLE_VOLTAGE, mStationSaveParams.batterySingleVoltage);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batterySingleCapacity)) {
            map.put(StationSaveParams.BATTERY_SINGLE_CAPACITY, mStationSaveParams.batterySingleCapacity);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batterySeriesCount)) {
            map.put(StationSaveParams.BATTERY_SERIES_COUNT, mStationSaveParams.batterySeriesCount);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batteryParallelCount)) {
            map.put(StationSaveParams.BATTERY_PARALLEL_COUNT, mStationSaveParams.batteryParallelCount);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batteryCount)) {
            map.put(StationSaveParams.BATTERY_COUNT, mStationSaveParams.batteryCount);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batteryVoltage)) {
            map.put(StationSaveParams.BATTERY_VOLTAGE, mStationSaveParams.batteryVoltage);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batteryCapacity)) {
            map.put(StationSaveParams.BATTERY_CAPACITY, mStationSaveParams.batteryCapacity);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.batteryRemark)) {
            map.put(StationSaveParams.BATTERY_REMARK, mStationSaveParams.batteryRemark);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.loadType)) {
            map.put(StationSaveParams.LOAD_TYPE, mStationSaveParams.loadType + "");
        }
        if (!TextUtils.isEmpty(mStationSaveParams.loadPower)) {
            map.put(StationSaveParams.LOAD_POWER, mStationSaveParams.loadPower);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.countryId)) {
            map.put(StationSaveParams.COUNTRY_ID, mStationSaveParams.countryId);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.provinceId)) {
            map.put(StationSaveParams.PROVINCE_ID, mStationSaveParams.provinceId);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.address)) {
            map.put(StationSaveParams.ADDRESS, mStationSaveParams.address);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.longitude)) {
            map.put(StationSaveParams.LONGITUDE, mStationSaveParams.longitude);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.latitude)) {
            map.put(StationSaveParams.LATITUDE, mStationSaveParams.latitude);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.altitude)) {
            map.put(StationSaveParams.ALTITUDE, mStationSaveParams.altitude);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.maxAnnualTemper)) {
            map.put(StationSaveParams.MAX_ANNUAL_TEMPER, mStationSaveParams.maxAnnualTemper);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.minAnnualTemper)) {
            map.put(StationSaveParams.MIN_ANNUAL_TEMPER, mStationSaveParams.minAnnualTemper);
        }
        if (!TextUtils.isEmpty(mStationSaveParams.geoRemark)) {
            map.put(StationSaveParams.GEO_REMARK, mStationSaveParams.geoRemark);
        }

        Disposable disposable = mStationService.saveStation(map)
                .compose(new HttpResultLoadingTransformer<SaveInfo>())
                .subscribeWith(new DisposableSubscriber<SaveInfo>() {
                    @Override
                    public void onNext(SaveInfo s) {
                        mRxManager.post(Constants.INIT_STATION_DETAIL, null);
                        mRxManager.post(Constants.REFSH_HOME_FRAGMENT_DATA, null);
                        ToastUtil.showShortToast(getContext(), R.string.new_build_success);
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
