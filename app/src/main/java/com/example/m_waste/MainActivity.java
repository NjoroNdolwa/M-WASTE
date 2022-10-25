package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.m_waste.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String email,name,plate,id,phone;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private MyAdapter adapter;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("image");
    int[] images= {R.drawable.car1,R.drawable.car2,R.drawable.car1,R.drawable.car2};
    String[] names={"PARKING AT MAIN GATE","PARKING AT POINT C","PARKING AT MAIN GATE","WAY TO PARKING AT B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.adapterViewFlipper.setFlipInterval(3500);
        binding.adapterViewFlipper.setAutoStart(true);
        //start of getting code
        //end of uploading code
        firebaseAuth = FirebaseAuth.getInstance();
       /*binding.btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, AllPakingPlaces.class) );
           }
       });*/
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewTrucks.class));
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTrucks.class));
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BookedDays.class));
            }
        });
        /*binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BookedStatus.class));
            }
        });
        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminAddingSlot.class));
            }
        });*/
        checkStatus();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.navigationVie.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.hme:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                    case R.id.share:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Android Studio Pro");
                        intent.putExtra(Intent.EXTRA_TEXT,"Learn Android App Development https://play.google.com/store/apps/details?id=com.tutorial.personal.androidstudiopro");
                        intent.setType("text/plain");
                        startActivity(intent);
                        break;
                    case R.id.logout:
                        logoutMethod();
                        break;
                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        break;
                }
                return false;
            }

            private void logoutMethod() {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser !=null){
                    firebaseAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), Welcome.class));
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkStatus() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            String  email = firebaseUser.getEmail();
            binding.email.setText(email);
        }
        else {
            startActivity(new Intent(MainActivity.this, LoginUser.class));
            finish();
        }
    }
}