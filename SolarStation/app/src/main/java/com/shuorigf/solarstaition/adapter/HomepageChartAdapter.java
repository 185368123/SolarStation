package com.shuorigf.solarstaition.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.linechart.LineChartData;
import com.shuorigf.solarstaition.data.response.home.HomeDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class HomepageChartAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private HomeDataInfo homeDataInfo;

    public HomepageChartAdapter(List<Integer> data, HomeDataInfo homeDataInfo) {
        super(R.layout.rv_item_homepage_chart, data);
        this.homeDataInfo = homeDataInfo == null ? new HomeDataInfo() : homeDataInfo;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        TextView chartValue = baseViewHolder.getView(R.id.tv_chart_value);
        baseViewHolder.setText(R.id.tv_chart, integer);
        LineChart lineChart =  baseViewHolder.getView(R.id.line_chart);
        initLineChart(lineChart);
        String[] values = null;
        int color = ContextCompat.getColor(lineChart.getContext(), R.color.textBlue);
        switch (integer) {
            case R.string.day_generating_capacity:
                if (homeDataInfo.dayGenerating != null) {
                    values = homeDataInfo.dayGenerating.split(",");
                }
                color = ContextCompat.getColor(lineChart.getContext(), R.color.textBlue);
                break;
            case R.string.day_electricity_consumption:
                if (homeDataInfo.dayConsumption != null) {
                    values = homeDataInfo.dayConsumption.split(",");
                }
                color = ContextCompat.getColor(lineChart.getContext(), R.color.textRed);
                break;
        }
        chartValue.setTextColor(color);
        if (values != null && values.length > 0) {
            chartValue.setText(values[values.length - 1]);
            LineChartData lineChartData = new LineChartData();
            List<Float> list = new ArrayList<>();
            for (String value : values) {
                try {
                    list.add(Float.parseFloat(value));
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                    list.add(0f);
                }
            }
            lineChartData.addLine(list, color, true, false);
            lineChartData.drawLine(lineChart);
        }
    }

    private void initLineChart(LineChart lineChart) {
        lineChart.setDrawGridBackground(false);//设置没有网格
        lineChart.setTouchEnabled(false);//设置不能点击
        lineChart.getDescription().setEnabled(false);//设置没有文字描述
//        lineChart.setDragEnabled(false);
//        lineChart.setScaleEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(false); //设置没有曲线描述
        lineChart.getXAxis().setEnabled(false);
    }

}
