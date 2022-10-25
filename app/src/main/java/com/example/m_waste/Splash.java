package com.example.m_waste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.m_waste.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;
    Handler handler;
    FirebaseAuth firebaseAuth;
    Animation move_in,together;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        move_in = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_in);
        binding.imageCar.startAnimation(move_in);
        together= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.together);
        binding.text2.startAnimation(together);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser!=null){
                    startActivity(new Intent(getApplicationContext(),Welcome.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(),Welcome.class));
                    finish();
                }
            }
        },3000);
    }
}