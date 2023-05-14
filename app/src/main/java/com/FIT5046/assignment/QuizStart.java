package com.FIT5046.assignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FIT5046.assignment.databinding.QuizStartBinding;

import java.util.ArrayList;

public class QuizStart extends Fragment {

    private QuizStartBinding binding;
    public QuizStart(){};
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuizStartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SharedPreferences sharedName = requireActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences sharedCategory = requireActivity().getSharedPreferences("category", Context.MODE_PRIVATE);
        SharedPreferences sharedQuantity = requireActivity().getSharedPreferences("quantity", Context.MODE_PRIVATE);

        String name = sharedName.getString("name", null);
        String category = sharedCategory.getString("category", null);
        String quantity = sharedQuantity.getString("quantity", null);
        binding.textViewName.setText(name);
        binding.textViewCategory.setText(category);
        binding.textViewQuantity.setText(quantity + " Questions");

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager != null) {fragmentManager.popBackStack();
                }
            }
        });



        /*QuizMCQ quizMCQ = new QuizMCQ();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, quizMCQ);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/


        return view;
    }

    private void createQuiz(String mode, String category, String quantity){


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Question> qnsList = new ArrayList<>();

                for (DataSnapshot qnsSnapshot: dataSnapshot.getChildren()){
                    Question qns = qnsSnapshot.getValue()
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}