package com.shuorigf.solarstaition.ui.fragment.devicemanage;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.base.BaseFragment;
import com.shuorigf.solarstaition.constants.Constants;
import com.shuorigf.solarstaition.data.SingleBeans;
import com.shuorigf.solarstaition.data.exception.ResponseMessageException;
import com.shuorigf.solarstaition.data.flowable.HttpResultTransformer;
import com.shuorigf.solarstaition.data.linechart.LineChartData;
import com.shuorigf.solarstaition.data.params.device.DeviceDataLogChartParams;
import com.shuorigf.solarstaition.data.response.device.DeviceDataLogChartInfo;
import com.shuorigf.solarstaition.data.service.DeviceService;
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

public class RealTimeCurveChartFragment extends BaseFragment {


    private static final String[] Y_VALUE = {"00", "02", "04", "06", "08", "10", "12", "14", "16", "18"};
    @BindView(R.id.tv_real_time_curve_top_title)
    TextView mTopTitleTv;
    @BindView(R.id.line_chart_top)
    LineChart mTopLineChart;
    @BindView(R.id.tv_real_time_curve_top_max)
    TextView mTopMaxTv;
    @BindView(R.id.tv_real_time_curve_top_min)
    TextView mTopMinTv;
    @BindView(R.id.tv_real_time_curve_top_avg)
    TextView mTopAvgTv;
    @BindView(R.id.tv_real_time_curve_bottom_title)
    TextView mBottomTitleTv;
    @BindView(R.id.line_chart_bottom)
    LineChart mBottomLineChart;
    @BindView(R.id.tv_real_time_curve_bottom_max)
    TextView mBottomMaxTv;
    @BindView(R.id.tv_real_time_curve_bottom_min)
    TextView mBottomMinTv;
    @BindView(R.id.tv_real_time_curve_bottom_avg)
    TextView mBottomAvgTv;

    private DeviceService mDeviceService;

    private DeviceDataLogChartParams mDeviceDataLogChartParams1 = new DeviceDataLogChartParams();
    private DeviceDataLogChartParams mDeviceDataLogChartParams2 = new DeviceDataLogChartParams();

    public static RealTimeCurveChartFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(Constants.REAL_TIME_CURVE_CHART_TYPE, type);
        RealTimeCurveChartFragment fragment = new RealTimeCurveChartFragment();
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
        return R.layout.fragment_real_time_curve_chart;
    }

    /**
     * init view
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void init(Bundle savedInstanceState) {
        mDeviceService = RetrofitUtil.create(DeviceService.class);
        mDeviceDataLogChartParams1.id = getActivity().getIntent().getStringExtra(Constants.DEVICE_ID);
        mDeviceDataLogChartParams1.dateType = DeviceDataLogChartParams.DATE_TYPE_ALL;
        mDeviceDataLogChartParams2.id = getActivity().getIntent().getStringExtra(Constants.DEVICE_ID);
        mDeviceDataLogChartParams2.dateType = DeviceDataLogChartParams.DATE_TYPE_ALL;
        int type = getArguments().getInt(Constants.REAL_TIME_CURVE_CHART_TYPE);

        switch (type) {
            case 0:
                mTopTitleTv.setText(R.string.storage_battery_voltage);
                mBottomTitleTv.setText(R.string.solar_panel_voltage);
                mDeviceDataLogChartParams1.type = DeviceDataLogChartParams.TYPE_STORAGE_BATTERY_VOLTAGE;
                mDeviceDataLogChartParams2.type = DeviceDataLogChartParams.TYPE_SOLAR_PANEL_VOLTAGE;
                break;
            case 1:
                mTopTitleTv.setText(R.string.charging_current);
                mBottomTitleTv.setText(R.string.charging_power);
                mDeviceDataLogChartParams1.type = DeviceDataLogChartParams.TYPE_CHARGING_CURRENT;
                mDeviceDataLogChartParams2.type = DeviceDataLogChartParams.TYPE_CHARGING_POWER;
                break;
            case 2:
                mTopTitleTv.setText(R.string.battery_temperature);
                mBottomTitleTv.setText(R.string.generating_capacity);
                mDeviceDataLogChartParams1.type = DeviceDataLogChartParams.TYPE_BATTERY_TEMPERATURE;
                mDeviceDataLogChartParams2.type = DeviceDataLogChartParams.TYPE_GENERATING_CAPACITY;
                break;
            case 3:
                mTopTitleTv.setText(R.string.load_power);
                mDeviceDataLogChartParams1.type = DeviceDataLogChartParams.TYPE_LOAD_POWER;
                break;
        }

        initLineChart(mTopLineChart);
        initLineChart(mBottomLineChart);
    }


    private void initLineChart(LineChart lineChart) {
        // enable touch gestures
        lineChart.setTouchEnabled(false);

        // enable scaling and dragging
//        mLineChart.setDragEnabled(false);
//        mLineChart.setScaleEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setNoDataText("");
        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);
        lineChart.setPinchZoom(false);
        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
//        mv.setChartView(mLineChart); // For bounds control
//        mLineChart.setMarker(mv); // Set the marker to the chart

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.textGray));
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(Y_VALUE.length);
        xAxis.setValueFormatter(new MyXFormatter(Y_VALUE));


        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.textGray));
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawAxisLine(true);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChart.getAxisRight().setEnabled(false);
        Legend l = lineChart.getLegend();
        l.setEnabled(false);

    }

    private void initLineChartXY(LineChart lineChart,String max,String min,String avg){
        YAxis leftAxis=lineChart.getAxisLeft();
        String []y=getY_VALUE(Double.parseDouble(max),Double.parseDouble(min),Double.parseDouble(avg));
        leftAxis.setAxisMinimum(Float.parseFloat(min)-1);
        leftAxis.setAxisMaximum(Float.parseFloat(max)+1);
        //leftAxis.setLabelCount(y.length);
        //leftAxis.setValueFormatter(new MyXFormatter(y));
    }

    /**
     * init data
     */
    @Override
    public void initData() {
        super.initData();
        getDataLogChart1();
        getDataLogChart2();
    }

    private void getDataLogChart1() {
        if (mDeviceDataLogChartParams1.id == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(DeviceDataLogChartParams.ID, mDeviceDataLogChartParams1.id);
        map.put(DeviceDataLogChartParams.TYPE, mDeviceDataLogChartParams1.type);
        map.put(DeviceDataLogChartParams.DATE_TYPE, mDeviceDataLogChartParams1.dateType);
        map.put(DeviceDataLogChartParams.DATE, mDeviceDataLogChartParams1.dateType);
        if (mDeviceDataLogChartParams1.date != null) {
            map.put(DeviceDataLogChartParams.DATE, mDeviceDataLogChartParams1.date);
        }

        Disposable disposable = mDeviceService.getDataLogChart(map)
                .compose(new HttpResultTransformer<DeviceDataLogChartInfo>())
                .subscribeWith(new DisposableSubscriber<DeviceDataLogChartInfo>() {
                    @Override
                    public void onNext(DeviceDataLogChartInfo deviceDataLogChartInfo) {
                        mTopMaxTv.setText(String.format(getString(R.string.format_maximum_value), deviceDataLogChartInfo.max));
                        mTopMinTv.setText(String.format(getString(R.string.format_minimum_value), deviceDataLogChartInfo.min));
                        mTopAvgTv.setText(String.format(getString(R.string.format_average_value), deviceDataLogChartInfo.avg));
                        initLineChartXY(mTopLineChart,deviceDataLogChartInfo.max,deviceDataLogChartInfo.min,deviceDataLogChartInfo.avg);
                        initLineChartData1(deviceDataLogChartInfo.logInfo);
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

    private void initLineChartData1(DeviceDataLogChartInfo.LogInfo logInfo) {
        if (logInfo == null) {
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
        lineChartData.drawLine(mTopLineChart);
    }

    private void getDataLogChart2() {
        if (mDeviceDataLogChartParams2.id == null) {
            return;
        }
        if (mDeviceDataLogChartParams2.type == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(DeviceDataLogChartParams.ID, mDeviceDataLogChartParams2.id);
        map.put(DeviceDataLogChartParams.TYPE, mDeviceDataLogChartParams2.type);
        map.put(DeviceDataLogChartParams.DATE_TYPE, mDeviceDataLogChartParams2.dateType);
        if (mDeviceDataLogChartParams2.date != null) {
            map.put(DeviceDataLogChartParams.DATE, mDeviceDataLogChartParams2.date);
        }
        Disposable disposable = mDeviceService.getDataLogChart(map)
                .compose(new HttpResultTransformer<DeviceDataLogChartInfo>())
                .subscribeWith(new DisposableSubscriber<DeviceDataLogChartInfo>() {
                    @Override
                    public void onNext(DeviceDataLogChartInfo deviceDataLogChartInfo) {
                        mBottomMaxTv.setText(String.format(getString(R.string.format_maximum_value), deviceDataLogChartInfo.max));
                        mBottomMinTv.setText(String.format(getString(R.string.format_minimum_value), deviceDataLogChartInfo.min));
                        mBottomAvgTv.setText(String.format(getString(R.string.format_average_value), deviceDataLogChartInfo.avg));
                        initLineChartXY(mBottomLineChart,deviceDataLogChartInfo.max,deviceDataLogChartInfo.min,deviceDataLogChartInfo.avg);
                        initLineChartData2(deviceDataLogChartInfo.logInfo);
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

    private void initLineChartData2(DeviceDataLogChartInfo.LogInfo logInfo) {
        if (logInfo == null) {
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
        lineChartData.drawLine(mBottomLineChart);
    }

    /**
     * init event
     */
    @Override
    protected void initEvent() {

    }


    public String[] getY_VALUE(double max,double min,double avg){//根据最大、最小、平均值计算出Y轴的九个坐标点
        List<String> list=new ArrayList<>();
        double distance_max=Math.ceil(Math.max(max-avg,avg-min));
        while (distance_max%4!=0){
            distance_max++;
        }
        if (distance_max<4)
            distance_max=4;
        for (int i = 0; i < 9; i++) {
            list.add(Math.ceil(avg)+(distance_max/4)*(4-i)+"");
        }
        return  (String[])list.toArray(new String[list.size()]);
    }
}
