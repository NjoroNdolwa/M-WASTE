package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.m_waste.databinding.ActivityRegisterUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity {
    ActivityRegisterUserBinding binding;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        pd= new ProgressDialog(this);
        pd.setTitle("Please Wait...");
        pd.setCanceledOnTouchOutside(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name= binding.name.getText().toString().trim();
                String Email=binding.email.getText().toString().trim();
                String Phone=binding.phone.getText().toString().trim();
                String County = binding.county.getText().toString().trim();
                String Collection = binding.preferred.getText().toString().trim();
                String Password = binding.password.getText().toString().trim();
                String ConPass= binding.conpass.getText().toString().trim();
                if (Name.isEmpty()){
                    binding.name.setError("Required");
                    binding.name.requestFocus();
                }
                else if (Email.isEmpty()){
                    binding.email.setError("Required");
                    binding.email.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    binding.email.setError("Please Provide valid Email");
                    binding.email.requestFocus();
                }
                else if (Phone.isEmpty()){
                    binding.phone.setError("Required");
                    binding.phone.requestFocus();
                }
                else if (County.isEmpty()){
                    binding.county.setError("Required");
                    binding.county.requestFocus();
                }
                else if (Collection.isEmpty()){
                    binding.preferred.setError("Required");
                    binding.preferred.requestFocus();
                }
                else if (Password.isEmpty()){
                    binding.password.setError("Required");
                    binding.password.requestFocus();
                }
                else if (ConPass.isEmpty()){
                    binding.conpass.setError("Required");
                    binding.conpass.requestFocus();
                }
                else if (!Password.equals(ConPass)){
                    Toast.makeText(RegisterUser.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                }
                else {
                    pd.setMessage("Creating Account...");
                    pd.show();
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                saveToDatabase();
                            }
                        }

                        private void saveToDatabase() {
                            Map<String,Object> map = new HashMap<>();
                            /*this.name = name;
                            this.county = county;
                            this.phone = phone;
                            this.point = point;
                            this.email = email;
                            this.pass = pass;*/
                            map.put("name",Name);
                            map.put("county",County);
                            map.put("email",Email);
                            map.put("point", Collection);
                            map.put("phone",Phone);
                            FirebaseDatabase.getInstance().getReference().child("Registered").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                    startActivity(new Intent(getApplicationContext(),LoginUser.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error while Saving", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                            });

                        }
                    });

                }
            }
        });
        binding.log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginUser.class));
            }
        });
    }
}