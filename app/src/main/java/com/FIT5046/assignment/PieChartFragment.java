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
import com.FIT5046.assignment.databinding.PieChartFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PieChartFragment extends Fragment {
    private PieChartFragmentBinding binding;
    public PieChartFragment(){}
    public DatabaseReference mDatabase;
    public DatabaseReference fDatabase;
    public FirebaseUser fireBaseUser;
    private String UserID;
    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = PieChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        UserID = fireBaseUser.getUid();
        firebaseAuth= FirebaseAuth.getInstance();
        fireBaseUser=firebaseAuth.getCurrentUser();
        fDatabase = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Result of Quiz").child(UserID);

        /*mDatabase = (DatabaseReference) FirebaseDatabase.getInstance().getReference("User Results").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /
                List<PieEntry> userResults = new ArrayList<>();
                List<String> categories = new ArrayList<>();

                int index = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String category = snapshot.child("category").getValue(String.class);
                    int value = snapshot.child("value").getValue(Integer.class);
                    userResults.add(new PieEntry(index, value));
                    categories.add(category);
                    index++;
                }*/
        HashMap<String, Object> hashmap = new HashMap<>();
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();
                    Object object = dataSnapshot.getValue();

                    hashmap.put(key, object);

                    System.out.printf(String.valueOf(hashmap));
                    PieDataSet pieDataSet = new PieDataSet((List<PieEntry>) object, "Quiz Questions");
                    pieDataSet.setColors(Color.GREEN, Color.RED);
                    pieDataSet.setValueTextSize(12f);
                    pieDataSet.setValueTextColor(Color.BLACK);
                    PieData pieData = new PieData(pieDataSet);
                    binding.pieChart.setData(pieData);
                    binding.pieChart.setVisibility(View.VISIBLE);
                    binding.pieChart.animateY(1000);
                    Description description = new Description();
                    description.setText("Quiz Questions");
                    binding.pieChart.setDescription(description);
                    binding.pieChart.invalidate();
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
/*
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
        binding.pieChart.animateY(1000);
        Description description = new Description();
        description.setText("Quiz Questions");
        binding.pieChart.setDescription(description);
        binding.pieChart.invalidate();
        return view;*/