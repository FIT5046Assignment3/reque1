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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseUser;

public class BarChartFragment extends Fragment {
    private BarChartFragmentBinding binding;
    public BarChartFragment(){}
    public DatabaseReference fDatabase;
    public FirebaseUser fireBaseUser;
    private String UserID;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = BarChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        UserID = fireBaseUser.getUid();
        firebaseAuth=FirebaseAuth.getInstance();
        fireBaseUser=firebaseAuth.getCurrentUser();
        fDatabase = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Result of Quiz").child(UserID);
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //ArrayList<BarEntry> userResults = new ArrayList<>();
//                ArrayList<String> xAxisValues = new ArrayList<>();
//
//                int index = 0;
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String xAxisValue = snapshot.child("category").getValue(String.class); //ADD .child(database name) for each category in database
//                    int value = snapshot.child("value").getValue(Integer.class); //ADD .child(database name) for each category in database
//                    userResults.add(new BarEntry(index, value));
//                    xAxisValues.add(xAxisValue);
//                    index++;
//                }

        HashMap<String, Object> hashmap = new HashMap<>();
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                     String key = dataSnapshot.getKey();
                     Object object = dataSnapshot.getValue();

                     hashmap.put(key, object);

                     System.out.printf(String.valueOf(hashmap));
                     BarDataSet barDataSet = new BarDataSet((List<BarEntry>) object, "Quiz Questions");
                     barDataSet.setColors(Color.GREEN, Color.RED, Color.YELLOW);
                     binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(Collections.singleton(key)));
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
             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {

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

/*List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 6766));
        barEntries.add(new BarEntry(1, 4444));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Quiz Questions");
        barDataSet.setColors(new int[] {Color.GREEN, Color.RED});
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Correct", "Incorrect"));
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        barData.setBarWidth(1.0f);
        binding.barChart.setVisibility(View.VISIBLE);
        binding.barChart.animateY(1000);
        Description description = new Description();
        description.setText("Quiz Questions");
        binding.barChart.setDescription(description);
        binding.barChart.invalidate();*/