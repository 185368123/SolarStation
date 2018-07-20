package com.shuorigf.solarstaition.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.shuorigf.solarstaition.R;
import com.shuorigf.solarstaition.data.linechart.LineChartData;
import com.shuorigf.solarstaition.data.response.project.ProjectDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clx on 2017/10/6.
 */

public class ProjectChartAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private ProjectDataInfo projectDataInfo;

    public ProjectChartAdapter(List<Integer> data, ProjectDataInfo projectDataInfo) {
        super(R.layout.rv_item_project_chart, data);
        this.projectDataInfo = projectDataInfo == null ? new ProjectDataInfo() : projectDataInfo;
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
                if (projectDataInfo.dayGenerating != null) {
                    values = projectDataInfo.dayGenerating.split(",");
                }
                color = ContextCompat.getColor(lineChart.getContext(), R.color.textBlue);
                break;
            case R.string.day_electricity_consumption:
                if (projectDataInfo.dayConsumption != null) {
                    values = projectDataInfo.dayConsumption.split(",");
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

    private void setData(LineChart lineChart, int lineColor) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 100) + 3;
            values.add(new Entry(i, val));
        }

        LineDataSet set1 = new LineDataSet(values, "DataSet 1");

        set1.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"设置线为虚线
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(lineColor);
            set1.setLineWidth(1f);
            set1.setCircleColor(lineColor);
            set1.setCircleRadius(3f);
//            set1.setCircleHoleRadius(2f);
            set1.setDrawCircleHole(false);//设置是否空心圆
            set1.setDrawValues(false);//设置不显示值


            //设置线下面的阴影颜色
            set1.setDrawFilled(true);
            set1.setFillColor(lineColor);


            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
             lineChart.setData(data);

    }
}
