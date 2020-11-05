package com.example.humanresursmanag;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import com.example.humanresursmanag.calculation.Calculation;
import com.example.humanresursmanag.model.Employ;
import com.example.humanresursmanag.viewmodel.EmployViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.List;


public class GraphicsActivity extends AppCompatActivity {
    private LiveData<List<Employ>> allEmploy;
    private Double middleSalary;
    EmployViewModel employViewModel = EmployActivity.employViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        allEmploy = employViewModel.getAllEmploy();
        middleSalary = Calculation.middleSalary(allEmploy.getValue());
        iniGraph(graph);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void iniGraph(GraphView graph) {
        BarGraphSeries<DataPoint> barGraphSeries = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(Calendar.getInstance().get(Calendar.YEAR), middleSalary)
        });
        barGraphSeries.setSpacing(98);
        barGraphSeries.setAnimated(true);
        barGraphSeries.setDrawValuesOnTop(true);
        barGraphSeries.setValuesOnTopColor(Color.RED);
        graph.addSeries(barGraphSeries);
        graph.getViewport().setMinX(2010);
        graph.getViewport().setMaxX((Calendar.getInstance()
                .get(Calendar.YEAR))+10);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(middleSalary+middleSalary/2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);


    }

}