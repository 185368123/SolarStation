package com.shuorigf.solarstaition.ui.fragment.powerstationdetails;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.linechart.LineChartData;
import com.shuorigf.solarstaition.data.params.station.StationPowerLogParams;
import com.shuorigf.solarstaition.data.response.station.StationPowerLogInfo;
import com.shuorigf.solarstaition.data.service.StationService;
import com.shuorigf.solarstaition.ui.view.MyXFormatter;
import com.shuorigf.solarstaition.util.DisposableManager;
import com.shuorigf.solarstaition.util.RetrofitUtil;
import com.shuorigf.solarstaition.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by clx on 2017/10/12.
 */

public class PowerChartFragment extends BaseFragment {

    @BindView(R.id.line_chart)
    LineChart mLineChart;
    @BindView(R.id.tv_power_chart_power)
    TextView mPowerTv;

    private static final String[] Y_VALUE = {"00", "02", "04", "06", "08", "10", "12", "14", "16", "18"};

    private StationService mStationService;
    private String mStationId;
    private String mType;

    public static PowerChartFragment newInstance(@StringRes int type) {
        Bundle args = new Bundle();
        args.putInt(Constants.POWER_CHART_TYPE, type);
        PowerChartFragment fragment = new PowerChartFragment();
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
        return R.layout.fragment_power_chart;
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
        int type = getArguments().getInt(Constants.POWER_CHART_TYPE);
        mPowerTv.setText(type);
        if (type == R.string.charging_power) {
            mType = StationPowerLogParams.TYPE_CHARGE;
        }else {
            mType = StationPowerLogParams.TYPE_DISCHARGE;
        }
        initLineChart();
    }



    private void initLineChart() {
        // enable touch gestures
        mLineChart.setTouchEnabled(false);

        // enable scaling and dragging
//        mLineChart.setDragEnabled(false);
//        mLineChart.setScaleEnabled(false);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setNoDataText("");
        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);
        mLineChart.setPinchZoom(false);
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
//        mv.setChartView(mLineChart); // For bounds control
//        mLineChart.setMarker(mv); // Set the marker to the chart

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.textGray));
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(Y_VALUE.length);
        xAxis.setValueFormatter(new MyXFormatter(Y_VALUE));


        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.textGray));
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawAxisLine(true);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mLineChart.getAxisRight().setEnabled(false);
        Legend l = mLineChart.getLegend();
        l.setEnabled(false);

    }

    /**
     * init data
     */
    @Override
    public void initData() {
       super.initData();
        getPowerLog();
    }

    private void getPowerLog() {
        if (mStationId == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(StationPowerLogParams.STATION_ID,mStationId);
        map.put(StationPowerLogParams.TYPE,mType);
        Disposable disposable = mStationService.getPowerLog(map)
                .compose(new HttpResultTransformer<StationPowerLogInfo>())
                .subscribeWith(new DisposableSubscriber<StationPowerLogInfo>() {
                    @Override
                    public void onNext(StationPowerLogInfo stationPowerLogInfo) {
                        initLineChartData(stationPowerLogInfo.logInfo);
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

    private void initLineChartData(StationPowerLogInfo.LogInfo logInfo) {
        if(logInfo == null) {
            return;
        }

        LineChartData lineChartData = new LineChartData();

        List<Float> list = new ArrayList<>();
        list.add(logInfo.zero);
        list.add(logInfo.two);
        list.add(logInfo.four);
        list.add(logInfo.six);
        list.add(logInfo.eight);
        list.add(logInfo.ten);
        list.add(logInfo.twelve);
        list.add(logInfo.fourteen);
        list.add(logInfo.sixteen);
        list.add(logInfo.eighteen);
        lineChartData.addLine(list, ContextCompat.getColor(getContext(), R.color.textBlue), true, false);
        lineChartData.drawLine(mLineChart);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }

}
