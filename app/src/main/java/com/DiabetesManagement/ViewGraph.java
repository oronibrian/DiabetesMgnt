package com.DiabetesManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

public class ViewGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_graph);
        getSupportActionBar().setTitle("Weekly Diet and Exercise");

        barChart();
        lineChart();
    }

    private void lineChart() {
        ValueLineChart mCubicValueLineChart = findViewById(R.id.cubiclinechart);

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        series.addPoint(new ValueLinePoint("Mon", 0.4f));
        series.addPoint(new ValueLinePoint("Tue", 3.4f));
        series.addPoint(new ValueLinePoint("Wed", .4f));
        series.addPoint(new ValueLinePoint("Thur", 1.2f));
        series.addPoint(new ValueLinePoint("Fri", 2.6f));
        series.addPoint(new ValueLinePoint("Sat", 1.0f));
        series.addPoint(new ValueLinePoint("Sun", 3.5f));
//        series.addPoint(new ValueLinePoint("Aug", 2.4f));
//        series.addPoint(new ValueLinePoint("Sep", 2.4f));
//        series.addPoint(new ValueLinePoint("Oct", 3.4f));
//        series.addPoint(new ValueLinePoint("Nov", .4f));
//        series.addPoint(new ValueLinePoint("Dec", 1.3f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }

    private void barChart() {
        BarChart mBarChart = findViewById(R.id.barchart);

        mBarChart.addBar(new BarModel("Mon" ,2.3f, 0xFF123456));
        mBarChart.addBar(new BarModel("Tue",2.f, 0xFF343456));
        mBarChart.addBar(new BarModel("Wed",3.3f, 0xFF563456));
        mBarChart.addBar(new BarModel("Thur",1.1f, 0xFF873F56));
        mBarChart.addBar(new BarModel("Fri",2.7f, 0xFF56B7F1));
        mBarChart.addBar(new BarModel("Sat",2.f, 0xFF343456));
        mBarChart.addBar(new BarModel("Sun",0.4f, 0xFF1FF4AC));

        mBarChart.startAnimation();
    }




}