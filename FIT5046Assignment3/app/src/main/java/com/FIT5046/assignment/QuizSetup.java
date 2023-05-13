package com.FIT5046.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.FIT5046.assignment.databinding.QuizSetupBinding;

import java.util.Random;

public class QuizSetup extends AppCompatActivity {
    private QuizSetupBinding binding;
    private String mode = "";
    private String category = "";
    private String quantity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = QuizSetupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Mode
        binding.soloButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.vsButton.setChecked(false);
                    mode = "solo";
                }
            }
        });
        binding.vsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.soloButton.setChecked(false);
                    mode = "vs";
                }
            }
        });

        //Category
        binding.anyButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="any";
                }
            }
        });
        binding.techButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.anyButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="tech";
                }
            }
        });
        binding.healthButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.anyButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="health";
                }
            }
        });
        binding.mathButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.anyButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="math";
                }
            }
        });
        binding.engButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.anyButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="eng";
                }
            }
        });
        binding.sciButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.anyButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="sci";
                }
            }
        });
        binding.artButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.anyButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="art";
                }
            }
        });
        binding.triviaButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.techButton.setChecked(false);
                    binding.healthButton.setChecked(false);
                    binding.mathButton.setChecked(false);
                    binding.engButton.setChecked(false);
                    binding.sciButton.setChecked(false);
                    binding.artButton.setChecked(false);
                    binding.triviaButton.setChecked(false);
                    category="trivia";
                }
            }
        });

        //Questions Quantity
        binding.anyQuantityButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.tenButton.setChecked(false);
                    binding.fifteenButton.setChecked(false);
                    binding.twentyButton.setChecked(false);
                    quantity="any";
                }
            }
        });
        binding.tenButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.anyQuantityButton.setChecked(false);
                    binding.fifteenButton.setChecked(false);
                    binding.twentyButton.setChecked(false);
                    quantity="10";
                }
            }
        });
        binding.fifteenButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.anyQuantityButton.setChecked(false);
                    binding.tenButton.setChecked(false);
                    binding.twentyButton.setChecked(false);
                    quantity="15";
                }
            }
        });
        binding.twentyButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.anyQuantityButton.setChecked(false);
                    binding.tenButton.setChecked(false);
                    binding.fifteenButton.setChecked(false);
                    quantity="20";
                }
            }
        });


        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizSetup.this, QuizStart.class);

                switch(mode){
                    case "Solo":
                        //insert user name
                        intent.putExtra("name","John");
                        break;
                    case "VS":
                        //NO VS USER?
                        intent.putExtra("name","John VS Jason");
                        break;
                }

                switch(category){
                    case "any":
                        String [] qnsArray = {"Technology", "Health", "Math", "English", "Science", "Art", "Trivia"};
                        Random randomQns = new Random();
                        int qns = randomQns.nextInt(qnsArray.length);
                        intent.putExtra("category", qnsArray[qns]);
                        break;
                    case "tech":
                        intent.putExtra("category","Technology");
                        break;
                    case "health":
                        intent.putExtra("category","Health");
                        break;
                    case "math":
                        intent.putExtra("category","Math");
                        break;
                    case "eng":
                        intent.putExtra("category","Englisgh");
                        break;
                    case "sci":
                        intent.putExtra("category","Science");
                        break;
                    case "art":
                        intent.putExtra("category","Art");
                        break;
                    case "trivia":
                        intent.putExtra("category","Trivia");
                        break;
                }

                switch(quantity){
                    case "any":
                        String [] qnsArray = {"10", "15", "20"};
                        Random randomQns = new Random();
                        int qns = randomQns.nextInt(qnsArray.length);
                        intent.putExtra("quantity", qnsArray[qns]);
                        break;
                    case "10":
                        intent.putExtra("quantity","10");
                        break;
                    case "15":
                        intent.putExtra("quantity","15");
                        break;
                    case "20":
                        intent.putExtra("quantity","20");
                        break;
                }
            } });
    }
}
