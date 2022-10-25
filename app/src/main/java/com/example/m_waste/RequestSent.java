package com.example.m_waste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.m_waste.databinding.ActivityRequestSentBinding;

public class RequestSent extends AppCompatActivity {
    ActivityRequestSentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRequestSentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}