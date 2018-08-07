package com.shuorigf.solarstaition.ui.fragment.powerstationdetails;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.shuorigf.solarstaition.GlideApp;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.PowerStationInformationAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.ApiConstants;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultLoadingTransformer;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.params.station.StationSaveParams;
import com.shuorigf.solarstaition.data.response.SaveInfo;
import com.shuorigf.solarstaition.data.response.common.UrlInfo;
import com.shuorigf.solarstaition.data.response.station.StationDetailInfo;
import com.shuorigf.solarstaition.data.service.CommonService;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.activity.StationInformationChangeActivity;
import com.shuorigf.solarstaition.util.ConvertUtils;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 * Created by clx on 2017/10/13.
 */

public class PowerStationInformationFragment extends BaseFragment {

    @BindView(R.id.iv_power_station_information_icon)
    ImageView mIconIv;
    @BindView(R.id.rv_power_station_information_content)
    RecyclerView mContentRv;

    @BindArray(R.array.power_station_information_title)
    TypedArray mTitle;

    private StationService mStationService;
    private CommonService mCommonService;
    private String mStationId;
    private StationSaveParams mStationSaveParams = new StationSaveParams();

    public static PowerStationInformationFragment newInstance() {

        Bundle args = new Bundle();
        PowerStationInformationFragment fragment = new PowerStationInformationFragment();
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
        return R.layout.fragment_power_station_information;
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
        mStationId = getActivity().getIntent().getStringExtra(Constants.STATION_ID);
        mContentRv.addItemDecoration(new UniversalDividerItemDecoration(getContext(),
                UniversalDividerItemDecoration.HORIZONTAL_LIST,
                ConvertUtils.dp2px(getContext(), 10), ContextCompat.getColor(getContext(), R.color.divider)));
        mContentRv.setNestedScrollingEnabled(false);
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getStationDetail();
    }

    private void getStationDetail() {
        if (mStationId == null) {
            return;
        }
        Disposable disposable = mStationService.getStationDetail(mStationId)
                .compose(new HttpResultTransformer<StationDetailInfo>())
                .subscribeWith(new DisposableSubscriber<StationDetailInfo>() {
                    @Override
                    public void onNext(StationDetailInfo stationDetailInfo) {
                        initStationDetail(stationDetailInfo);
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

    private void initStationDetail(StationDetailInfo stationDetailInfo) {
        SingleBeans.getInstance().setSaveParams(new StationSaveParams(mStationId, stationDetailInfo.name, stationDetailInfo.projectId, stationDetailInfo.installTime, stationDetailInfo.designer, stationDetailInfo.photo,
                stationDetailInfo.panelPower, stationDetailInfo.panelType + "", stationDetailInfo.panelSeriesCount, stationDetailInfo.panelParallelCount,
                stationDetailInfo.panelCount, stationDetailInfo.panelVoltage, stationDetailInfo.panelCurrent, stationDetailInfo.panelSinglePower,
                stationDetailInfo.panelSingleVmp, stationDetailInfo.panelSingleVoc, stationDetailInfo.panelSingleImp, stationDetailInfo.panelSingleIsc,
                stationDetailInfo.panelRemark, stationDetailInfo.batteryType + "", stationDetailInfo.batterySingleVoltage, stationDetailInfo.batterySingleCapacity,
                stationDetailInfo.batterySeriesCount, stationDetailInfo.batteryParallelCount, stationDetailInfo.batteryCount, stationDetailInfo.batteryVoltage,
                stationDetailInfo.batteryCapacity, stationDetailInfo.batteryRemark, stationDetailInfo.loadType + "", stationDetailInfo.loadPower, stationDetailInfo.countryId,
                stationDetailInfo.provinceId, stationDetailInfo.address, stationDetailInfo.longitude, stationDetailInfo.latitude, stationDetailInfo.altitude, stationDetailInfo.maxAnnualTemper,
                stationDetailInfo.minAnnualTemper, stationDetailInfo.geoRemark));
        mStationSaveParams=SingleBeans.getInstance().getSaveParams();
        GlideApp.with(getContext())
                .load(stationDetailInfo.photo)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mIconIv);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < mTitle.length(); i++) {
            list.add(mTitle.getResourceId(i, 0));
        }
        PowerStationInformationAdapter powerStationInformationAdapter = new PowerStationInformationAdapter(list, stationDetailInfo);
        mContentRv.setItemViewCacheSize(list.size());
        mContentRv.setAdapter(powerStationInformationAdapter);
    }


    /**
     * init event
     */
    @Override
    protected void initEvent() {
        mRxManager.on(Constants.INIT_STATION_DETAIL, new Action1<Object>() {
            @Override
            public void call(Object o) {
                getStationDetail();
            }
        });
    }

    @OnClick(R.id.iv_power_station_information_icon)
    public void onViewClicked() {
        int cropWH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        PictureSelectorUtils.intoPictureSelector(this, cropWH, cropWH, 2, 2);
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
                    public void onNext(final UrlInfo urlInfo) {
                        Log.e("111", "onNext: " + ApiConstants.IMAGE_URL + urlInfo.url);
                        Map<String, String> map = new HashMap<>();
                        map.put(StationSaveParams.ID, mStationId);
                        map.put(StationSaveParams.PHOTO, urlInfo.url);
                        map.put(StationSaveParams.NAME, mStationSaveParams.name);
                        map.put(StationSaveParams.PROJECT_ID, mStationSaveParams.projectId);
                        map.put(StationSaveParams.INSTALL_TIME, mStationSaveParams.installTime);
                        map.put(StationSaveParams.DESIGNER, mStationSaveParams.designer);
                        Disposable disposable_ = mStationService.saveStation(map)
                                .compose(new HttpResultLoadingTransformer<SaveInfo>())
                                .subscribeWith(new DisposableSubscriber<SaveInfo>() {
                                    @Override
                                    public void onNext(SaveInfo s) {
                                        GlideApp.with(getContext())
                                                .load(ApiConstants.IMAGE_URL + urlInfo.url)
                                                .placeholder(R.mipmap.ic_select_photo)
                                                .error(R.mipmap.ic_select_photo)
                                                .into(mIconIv);
                                        ToastUtil.showShortToast(getContext(), R.string.change_success);
                                        mRxManager.post(Constants.INIT_STATION_DETAIL,null);
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
}
