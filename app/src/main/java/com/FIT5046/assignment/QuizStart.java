package com.FIT5046.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.FIT5046.assignment.databinding.QuizSetupBinding;
import com.FIT5046.assignment.databinding.QuizStartBinding;

import java.util.Timer;
import java.util.TimerTask;

public class QuizStart extends AppCompatActivity {

    private QuizStartBinding binding;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = QuizStartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        String quantity = intent.getStringExtra("quantity");
        binding.textViewName.setText(name);
        binding.textViewName.setText(category);
        binding.textViewName.setText(quantity);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(QuizStart.this, QuizMCQ.class);
                startActivity(intent);
                finish();
            }
        }, 5000);

    }
}