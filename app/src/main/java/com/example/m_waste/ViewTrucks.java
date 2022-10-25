package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.m_waste.databinding.ActivityViewTrucksBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewTrucks extends AppCompatActivity {
    ActivityViewTrucksBinding binding;
    ArrayList<TruckClass> truckClassesArray;
    MainAdapter myAdapter;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressDialog pd;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewTrucksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        truckClassesArray=new ArrayList<TruckClass>();
        myAdapter=new MainAdapter(this,truckClassesArray);

        binding.recyclerView.setAdapter(myAdapter);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference=firebaseDatabase.getReference("Truck");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    TruckClass myData=dataSnapshot.getValue(TruckClass.class);
                    truckClassesArray.add(myData);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewTrucks.this,"error occurred",Toast.LENGTH_SHORT).show();
            }
        });

    }
}