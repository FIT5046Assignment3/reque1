package com.FIT5046.assignment;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FIT5046.assignment.databinding.PieChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieChartFragment extends Fragment {
    private PieChartFragmentBinding binding;
    public PieChartFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = PieChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //chatGPT
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(6766, "Correct"));
        pieEntries.add(new PieEntry(4444, "Incorrect"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Quiz Questions");
        pieDataSet.setColors(new int[] {Color.GREEN, Color.RED});
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueTextColor(Color.BLACK);
        //chatGPT

        PieData pieData = new PieData(pieDataSet);
        binding.pieChart.setData(pieData);
        binding.pieChart.setVisibility(View.VISIBLE);
        binding.pieChart.animateY(4000);
        Description description = new Description();
        description.setText("Quiz Questions");
        binding.pieChart.setDescription(description);
        binding.pieChart.invalidate();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
