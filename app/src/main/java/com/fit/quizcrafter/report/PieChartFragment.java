package com.fit.quizcrafter.report;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fit.quizcrafter.databinding.PieChartFragmentBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
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
import java.util.List;

public class PieChartFragment extends Fragment {
    private PieChartFragmentBinding binding;
    public PieChartFragment(){}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = PieChartFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser fireBaseUser = firebaseAuth.getCurrentUser();
        String userID = fireBaseUser.getUid();
        DatabaseReference fDatabase = FirebaseDatabase.getInstance().getReference("Result of quiz").child(userID);

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

                    List<PieEntry> pieEntries = new ArrayList<>();
                    for (int i = 0; i < values.size(); i++) {
                        pieEntries.add(new PieEntry(values.get(i), keys.get(i)));
                    }
                    PieDataSet pieDataSet = new PieDataSet(pieEntries, "Quiz Results");
                    pieDataSet.setColors(Color.GREEN, Color.RED, Color.BLUE);
                    pieDataSet.setValueTextColor(Color.WHITE);
                    pieDataSet.setValueTextSize(14);
                    PieData pieData = new PieData(pieDataSet);
                    binding.pieChart.setData(pieData);
                    binding.pieChart.setVisibility(View.VISIBLE);
                    binding.pieChart.animateY(1000);
                    Legend legend = binding.pieChart.getLegend();
                    legend.setEnabled(false);
                    binding.pieChart.getDescription().setEnabled(false);
                    binding.pieChart.invalidate();
                } else {

                    Toast.makeText(getContext(), "No results found, please play a quiz first", Toast.LENGTH_SHORT).show();
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


/*List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(4, "Wrong"));
        pieEntries.add(new PieEntry(0, "Correct"));
        pieEntries.add(new PieEntry(2, "Other"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Quiz Questions");
        pieDataSet.setColors(Color.GREEN, Color.RED, Color.YELLOW);
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
        return view;*/
