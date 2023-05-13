package com.FIT5046.assignment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FIT5046.assignment.databinding.QuizStartBinding;

public class QuizStart extends Fragment {

    private QuizStartBinding binding;
    public QuizStart(){};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuizStartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {fragmentManager.popBackStack();
                }
            }
        });

        SharedPreferences sharedName = requireActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences sharedCategory = requireActivity().getSharedPreferences("category", Context.MODE_PRIVATE);
        SharedPreferences sharedQuantity = requireActivity().getSharedPreferences("quantity", Context.MODE_PRIVATE);

        String name = sharedName.getString("name", null);
        String category = sharedCategory.getString("category", null);
        String quantity = sharedQuantity.getString("quantity", null);
        binding.textViewName.setText(name);
        binding.textViewCategory.setText(category);
        binding.textViewQuantity.setText(quantity + " Questions");

        /*QuizMCQ quizMCQ = new QuizMCQ();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, quizMCQ);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/


        return view;
    }
}