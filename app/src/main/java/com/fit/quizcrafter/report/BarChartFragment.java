package com.fit.quizcrafter.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.fit.quizcrafter.databinding.BarChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BarChartFragment extends Fragment {
    private BarChartFragmentBinding binding;
    public BarChartFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the View for this fragment using the binding
        binding = BarChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SharedPreferences sharedPref= requireActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
        String startDate = sharedPref.getString("StartDate",null);
        String endDate = sharedPref.getString("EndDate",null);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser fireBaseUser = firebaseAuth.getCurrentUser();
        String userID = fireBaseUser.getUid();
        DatabaseReference fDatabase = FirebaseDatabase.getInstance().getReference("Result of quiz").child(userID);

        //DatabaseReference fDatabase = FirebaseDatabase.getInstance().getReference("Result of quiz").child(userID).child(StartDate);
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<String> keys = new ArrayList<>();
                    ArrayList<Integer> values = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String key = dataSnapshot.getKey();
                        int value = dataSnapshot.getValue(Integer.class);

                        keys.add(key);
                        values.add(value);
                    }

                    List<BarEntry> barEntries = new ArrayList<>();
                    for (int i = 0; i < values.size(); i++){
                        barEntries.add(new BarEntry(i, values.get(i)));
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntries, "Quiz Results");
                    barDataSet.setColors(Color.GREEN, Color.RED, Color.YELLOW);
                    binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(keys));
                    BarData barData = new BarData(barDataSet);
                    binding.barChart.setData(barData);
                    barData.setBarWidth(1.0f);
                    binding.barChart.setVisibility(View.VISIBLE);
                    binding.barChart.animateY(1000);
                    Legend legend = binding.barChart.getLegend();
                    legend.setEnabled(false);
                    binding.barChart.getDescription().setEnabled(false);
                    binding.barChart.invalidate();
                }
                else{

                    Toast.makeText(getContext(),"No results found, please play a quiz first", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        });
        return view;
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}