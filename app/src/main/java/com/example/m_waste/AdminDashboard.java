package com.example.m_waste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.m_waste.databinding.ActivityAdminDashboardBinding;

public class AdminDashboard extends AppCompatActivity {
    ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddTrucks.class));
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MySystemUsers.class));
            }
        });

        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewTrucks.class));
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookedDays.class));
            }
        });

    }
}