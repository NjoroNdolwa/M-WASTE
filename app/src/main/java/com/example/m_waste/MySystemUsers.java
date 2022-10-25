package com.example.m_waste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.m_waste.databinding.ActivityMySystemUsersBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MySystemUsers extends AppCompatActivity {
    ActivityMySystemUsersBinding binding;
    ArrayList<UserClass> userClassArrayList;
    MyAdapter mainAdapter;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  =ActivityMySystemUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pd= new ProgressDialog(this);
        pd.setTitle("Please wait...");
        pd.show();
        pd.setCanceledOnTouchOutside(false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UserClass> options = new FirebaseRecyclerOptions.Builder<UserClass>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Registered"),UserClass.class)
                .build();

        mainAdapter= new MyAdapter(options);
        binding.recyclerView.setAdapter(mainAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
        pd.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}