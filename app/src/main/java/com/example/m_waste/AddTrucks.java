package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.m_waste.databinding.ActivityAddTrucksBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTrucks extends AppCompatActivity {
    ActivityAddTrucksBinding binding;
    List<String> name1,type1,collect,day1;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTrucksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-mmm-yyy", Locale.getDefault());
        String Dateformat=df.format(c);
        pd= new ProgressDialog(this);
        pd.setTitle("Please Wait...");
        pd.setCanceledOnTouchOutside(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Truck");
        name1 = new ArrayList<>();
        name1.add("Truck Name");
        name1.add("Lorry");
        name1.add("Pickup");
        name1.add("");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,name1);
        binding.truckName.setAdapter(adapter);
        binding.truckName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        day1 = new ArrayList<>();
        day1.add("Select day");
        day1.add("Wdnesday");
        day1.add("Saturday");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,day1);
        binding.day.setAdapter(adapter1);
        binding.day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        type1= new ArrayList<>();
        type1.add("Select type of waste");
        type1.add("Chemical waste");
        type1.add("Solid Waste");
        type1.add("Sewage");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,type1);
        binding.solidType.setAdapter(adapter2);
        binding.solidType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        collect= new ArrayList<>();
        collect.add("Select Collection");
        collect.add("Kanduyi");
        collect.add("Mayanja");
        collect.add("Tuuti");
        ArrayAdapter<String> adapter3= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,collect);
        binding.point.setAdapter(adapter3);
        binding.point.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.addTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  Truck_Name = binding.truckName.getSelectedItem().toString().trim();
                String  Point = binding.truckName.getSelectedItem().toString().trim();
                String  Waste_Type = binding.solidType.getSelectedItem().toString().trim();
                String  DAy = binding.day.getSelectedItem().toString().trim();
                String Status="Available";
                if (Truck_Name.equals("Truck Name")){
                    Toast.makeText(AddTrucks.this, "Select Truck Name", Toast.LENGTH_SHORT).show();

                }
                else if (Waste_Type.equals("Select type of waste")){
                    Toast.makeText(AddTrucks.this, "Select Waste Type", Toast.LENGTH_SHORT).show();
                }
                else if (Point.equals("")){

                }
                else if (DAy.equals("")){

                }
                else {
                    pd.setMessage("Saving...");
                    pd.show();
                    TruckClass truckClass = new TruckClass(Truck_Name,Point,Waste_Type,DAy,Dateformat,Status);
                    databaseReference.child(Truck_Name).setValue(truckClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddTrucks.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddTrucks.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}