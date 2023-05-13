package com.FIT5046.assignment;

import static android.content.Intent.getIntent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FIT5046.assignment.databinding.QuizStartBinding;

import java.util.Timer;
import java.util.TimerTask;

public class QuizReview extends Fragment {

    private QuizStartBinding binding;
    public QuizReview(){};

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


        /*QuizCollection quizCollection = new QuizCollection();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, quizCollection);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/

        return view;
    }


}