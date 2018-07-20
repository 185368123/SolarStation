package com.shuorigf.solarstaition.ui.fragment.powerstationdetails;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.shuorigf.solarstaition.GlideApp;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.adapter.PowerStationInformationAdapter;
import com.shuorigf.solarstaition.adapter.itemdecoration.UniversalDividerItemDecoration;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.params.station.StationSaveParams;
import com.shuorigf.solarstaition.data.response.station.StationDetailInfo;
import com.shuorigf.solarstaition.data.service.StationService;
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
    private String mStationId;


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

    private void initStationDetail(StationDetailInfo stationDetailInfo){
        SingleBeans.getInstance().setSaveParams(new StationSaveParams(mStationId, stationDetailInfo.name, stationDetailInfo.projectId, stationDetailInfo. installTime, stationDetailInfo. designer, stationDetailInfo. photo,
                stationDetailInfo. panelPower, stationDetailInfo. panelType+"", stationDetailInfo. panelSeriesCount, stationDetailInfo. panelParallelCount,
                stationDetailInfo. panelCount, stationDetailInfo. panelVoltage, stationDetailInfo. panelCurrent, stationDetailInfo. panelSinglePower,
                stationDetailInfo. panelSingleVmp, stationDetailInfo. panelSingleVoc, stationDetailInfo. panelSingleImp, stationDetailInfo. panelSingleIsc,
                stationDetailInfo. panelRemark, stationDetailInfo. batteryType+"", stationDetailInfo. batterySingleVoltage, stationDetailInfo. batterySingleCapacity,
                stationDetailInfo. batterySeriesCount, stationDetailInfo. batteryParallelCount, stationDetailInfo. batteryCount, stationDetailInfo. batteryVoltage,
                stationDetailInfo. batteryCapacity, stationDetailInfo. batteryRemark, stationDetailInfo. loadType+"", stationDetailInfo. loadPower, stationDetailInfo. countryId,
                stationDetailInfo. provinceId, stationDetailInfo. address, stationDetailInfo. longitude, stationDetailInfo. latitude, stationDetailInfo. altitude, stationDetailInfo. maxAnnualTemper,
                stationDetailInfo. minAnnualTemper, stationDetailInfo. geoRemark));
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

    }
}
