package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m_waste.databinding.ActivityLoginUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUser extends AppCompatActivity {
    ActivityLoginUserBinding binding;
    private FirebaseAuth firebaseAuth;
    ProgressDialog pd;
    String admin = "adminemail@gmail.com";
    String keyIn = "adminpass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pd= new ProgressDialog(this);
        pd.setTitle("Please wait...");
        pd.setCanceledOnTouchOutside(false);
        firebaseAuth = FirebaseAuth.getInstance();
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = binding.email.getText().toString();
                String Password = binding.password.getText().toString();
                if (Email.isEmpty()){
                    binding.email.setError("Required");
                    binding.email.requestFocus();
                }
                else if (Password.isEmpty()){
                    binding.password.setError("Required");
                    binding.password.requestFocus();
                }
                else if (Password.equals(keyIn)&& Email.equals(admin)){
                    startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
                }
                else {
                    pd.setMessage("Signing in");
                    pd.show();
                    firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(LoginUser.this, "Logged in Successfully !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginUser.this, MainActivity.class);
                                intent.putExtra("email",Email);
                                startActivity(intent);
                                pd.dismiss();
                            }
                            else {
                                Toast.makeText(LoginUser.this, "Failed to Login Please Enable Internet connection and check credentials and try again", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        }
                    });
                }
            }
        });
        binding.forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder  passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter Your Email To receive a reset link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString().trim();
                        if (!mail.isEmpty()){

                        }
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginUser.this, "Reset Link send to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginUser.this, "Error Reset link is not send", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterUser.class));
            }
        });
    }
}