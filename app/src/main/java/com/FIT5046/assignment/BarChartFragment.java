package com.FIT5046.assignment;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FIT5046.assignment.databinding.BarChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarChartFragment extends Fragment {
    private BarChartFragmentBinding binding;
    public BarChartFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = BarChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 6766));
        barEntries.add(new BarEntry(1, 4444));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Quiz Questions");
        barDataSet.setColors(new int[] {Color.GREEN, Color.RED});
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Correct", "Incorrect"));
        binding.barChart.getXAxis().setValueFormatter(new
                IndexAxisValueFormatter(xAxisValues));
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        barData.setBarWidth(1.0f);
        binding.barChart.setVisibility(View.VISIBLE);
        binding.barChart.animateY(1000);
        Description description = new Description();
        description.setText("Quiz Questions");
        binding.barChart.setDescription(description);
        binding.barChart.invalidate();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
