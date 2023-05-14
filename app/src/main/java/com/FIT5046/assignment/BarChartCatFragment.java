package com.FIT5046.assignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.FIT5046.assignment.databinding.BarChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BarChartCatFragment extends Fragment {
    private BarChartFragmentBinding binding;
    public BarChartCatFragment(){}
    public DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = BarChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mDatabase = (DatabaseReference) FirebaseDatabase.getInstance().getReference("User Results").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<BarEntry> userResults = new ArrayList<>();
                ArrayList<String> xAxisValues = new ArrayList<>();

                int index = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String xAxisValue = snapshot.child("category").getValue(String.class);
                    float value = snapshot.child("value").getValue(Float.class);
                    userResults.add(new BarEntry(index, value));
                    xAxisValues.add(xAxisValue);
                    index++;
                }
                BarDataSet barDataSet = new BarDataSet(userResults, "Quiz Questions");
                barDataSet.setColors(Color.GREEN, Color.RED);
                binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
                BarData barData = new BarData(barDataSet);
                binding.barChart.setData(barData);
                barData.setBarWidth(1.0f);
                binding.barChart.setVisibility(View.VISIBLE);
                binding.barChart.animateY(1000);
                YAxis yAxis = binding.barChart.getAxisLeft();
                yAxis.setAxisMaximum(15);
                Description description = new Description();
                description.setText("Quiz Questions");
                binding.barChart.setDescription(description);
                binding.barChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Results could not be retrieved", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}