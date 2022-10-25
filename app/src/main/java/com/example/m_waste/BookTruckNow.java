package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.m_waste.databinding.ActivityBookTruckNowBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookTruckNow extends AppCompatActivity {
    ActivityBookTruckNowBinding binding;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBookTruckNowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pd= new ProgressDialog(this);
        pd.setTitle("Please wait...");
        pd.setCanceledOnTouchOutside(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        Intent intent=getIntent();
        String Names=intent.getStringExtra("");
        databaseReference2 = firebaseDatabase.getReference("Truck").child(Names);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    binding.txtname.setText(snapshot.child("t_name").getValue().toString());
                    binding.txtcollectionPoint.setText(snapshot.child("collection_point").getValue().toString());
                    binding.txtday.setText(snapshot.child("day").getValue().toString());
                    binding.txtdate.setText(snapshot.child("date").getValue().toString());
                }
                else {
                    Toast.makeText(BookTruckNow.this, "No Such Space", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BookTruckNow.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference = firebaseDatabase.getReference("BookedRequest");
        binding.bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = binding.txtname.getText().toString().trim();
                String Type = binding.txttype.getText().toString().trim();
                String Point= binding.txtcollectionPoint.getText().toString().trim();
                String Day = binding.txtday.getText().toString().trim();
                String Date = binding.txtdate.getText().toString().trim();
                String Phone = binding.phone.getText().toString().trim();
                String Id = binding.idNumber.getText().toString().trim();
                String Statuss="Booked";
                if (Phone.isEmpty()){
                    binding.phone.setError("Required");
                    binding.phone.requestFocus();
                }
                else if (Id.isEmpty()){
                    binding.idNumber.setError("Required");
                    binding.idNumber.requestFocus();
                }
                else {
                    pd.setMessage("Saving...");
                    pd.show();
                    BookingClass bookingClass = new BookingClass(Name,Point,Type,Day,Date,Statuss,Phone,Id);
                    databaseReference.child(Name).setValue(bookingClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(BookTruckNow.this, "Added successfully", Toast.LENGTH_SHORT).show();
                            changestatus();

                        }

                        private void changestatus() {
                            pd.setMessage("Saving...");
                            pd.show();
                            TruckClass truckClass = new TruckClass(Name,Point,Type,Day,Date,Statuss);
                            databaseReference.child(Name).setValue(truckClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(BookTruckNow.this, "Added successfully", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(BookTruckNow.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BookTruckNow.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}