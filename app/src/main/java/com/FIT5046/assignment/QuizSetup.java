package com.FIT5046.assignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
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
import android.widget.CompoundButton;
import android.widget.Toast;

import com.FIT5046.assignment.databinding.QuizSetupBinding;

import java.util.ArrayList;
import java.util.Random;

public class QuizSetup extends Fragment {

    private FirebaseUser fireBaseUser;
    private DatabaseReference databaseReference;
    private QuizSetupBinding binding;
    public QuizSetup(){};
    private String UserID;
    private String mode ;
    private String category;
    private String quantity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuizSetupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

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
                    binding.anyButton.setChecked(false);
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

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager != null) {fragmentManager.popBackStack();
                }
            }
        });
        private void retrieveAccName(FirebaseUser fireBaseUser) {
            UserID = fireBaseUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("Data entry for create Account");
            databaseReference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    CreateAccountClass createAccountClass = snapshot.getValue(CreateAccountClass.class);
                    if (createAccountClass != null) {
                        useForAccountName = createAccountClass.accountName;
                        UserID = useForAccountName;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(HomeScreenActivity.this, "An error occurred", Toast.LENGTH_LONG).show();
                }
            });

        }

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedName= requireActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
                SharedPreferences.Editor spName = sharedName.edit();
                SharedPreferences sharedCategory= requireActivity().getSharedPreferences("category", Context.MODE_PRIVATE);
                SharedPreferences.Editor spCat = sharedCategory.edit();
                SharedPreferences sharedQuantity= requireActivity().getSharedPreferences("quantity", Context.MODE_PRIVATE);
                SharedPreferences.Editor spQnt = sharedQuantity.edit();

                switch(mode){
                    case "Solo":
                        //insert user name
                        spName.putString("name",UserID);
                        spName.apply();
                        break;
                    case "VS":
                        spName.putString("name","John VS Jason");
                        spName.apply();
                        break;
                }

                switch(category){
                    case "any":
                        String [] qnsArray = {"Technology", "Health", "Math", "English", "Science", "Art", "Trivia"};
                        Random randomQns = new Random();
                        int qns = randomQns.nextInt(qnsArray.length);
                        spCat.putString("category", qnsArray[qns]);
                        spCat.apply();
                        break;
                    case "tech":
                        spCat.putString("category","Technology");
                        spCat.apply();
                        break;
                    case "health":
                        spCat.putString("category","Health");
                        spCat.apply();
                        break;
                    case "math":
                        spCat.putString("category","Math");
                        spCat.apply();
                        break;
                    case "eng":
                        spCat.putString("category","English");
                        spCat.apply();
                        break;
                    case "sci":
                        spCat.putString("category","Science");
                        spCat.apply();
                        break;
                    case "art":
                        spCat.putString("category","Art");
                        spCat.apply();
                        break;
                    case "trivia":
                        spCat.putString("category","Trivia");
                        spCat.apply();
                        break;
                }

                switch(quantity){
                    case "any":
                        String [] qnsArray = {"10", "15", "20"};
                        Random randomQns = new Random();
                        int qns = randomQns.nextInt(qnsArray.length);
                        spQnt.putString("quantity", qnsArray[qns]);
                        spQnt.apply();
                        break;
                    case "10":
                        spQnt.putString("quantity","10");
                        spQnt.apply();
                        break;
                    case "15":
                        spQnt.putString("quantity","15");
                        spQnt.apply();
                        break;
                    case "20":
                        spQnt.putString("quantity","20");
                        spQnt.apply();
                        break;
                }
                QuizStart quizStart = new QuizStart();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, quizStart);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            } });
        return view;
    }
}
