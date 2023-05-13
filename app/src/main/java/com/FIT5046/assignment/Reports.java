package com.FIT5046.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.FIT5046.assignment.databinding.ReportsBinding;

import java.util.ArrayList;
import java.util.List;

public class Reports extends AppCompatActivity {
    private ReportsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ReportsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<String> list = new ArrayList<>();
        list.add("Bar Chart");
        list.add("Pie Chart");
        Spinner chartSpinner = binding.chartSpinner;
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this ,android.R.layout.simple_spinner_item,
                list);
        chartSpinner.setAdapter(spinnerAdapter);

        binding.generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chartType = binding.chartSpinner.getSelectedItem().toString();
                if (chartType.equals("Bar Chart")) {
                    replaceFragment(new BarChartFragment());
                }
                else if (chartType.equals("Pie Chart")) {
                    replaceFragment(new PieChartFragment());
                }
            }
        });
    }

    private void replaceFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.chartFragment, nextFragment);
        fragmentTransaction.commit();

    }
}
