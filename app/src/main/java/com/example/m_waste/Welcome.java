package com.example.m_waste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.m_waste.databinding.ActivityWelcomeBinding;

public class Welcome extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    Animation move_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        move_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_in);
        binding.rl1.startAnimation(move_in);
        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginUser.class));
            }
        });
        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterUser.class));
            }
        });

    }
}