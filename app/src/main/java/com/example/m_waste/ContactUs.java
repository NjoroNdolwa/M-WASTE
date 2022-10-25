package com.example.m_waste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.example.m_waste.databinding.ActivityContactUsBinding;

public class ContactUs extends AppCompatActivity {
    ActivityContactUsBinding binding;
    private static final int REQUEST_CALL = 1;
    Intent intent=null,chooser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();

            }

            private void makeCall() {
                String CallingNumber = "+254721707288";
                if (ContextCompat.checkSelfPermission(ContactUs.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ContactUs.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

                }
                else {
                    /*String dial = "tel:"+CallingNumber;
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));

                     */
                    String dial = "tel:"+ CallingNumber;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(dial));
                    startActivity(intent);
                }
            }
        });
        binding.facebookiIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto"));
                String[] to = {"ndolwavincent@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Looking for your Urgent assistant");
                intent.putExtra(Intent.EXTRA_TEXT,"Hey I'm a visitor and am Stuck i want to pack my car ");
                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent,"Send Email");
                startActivity(chooser);
            }
        });

        binding.whatssapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wpurl = "https://wa.me/+254(721707288)?text=Hi,Im vincent ndolwan how can i help you?";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });
        binding.messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myframe.setVisibility(View.VISIBLE);
            }
        });
        binding.sentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  if (ContextCompat.checkSelfPermission(ContactUs.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED);{
                //     sendMessage();
                // }
                if (ContextCompat.checkSelfPermission(ContactUs.this,Manifest.permission.SEND_SMS)
                        ==PackageManager.PERMISSION_GRANTED){
                    sendMessage();
                }
                else {
                    ActivityCompat.requestPermissions(ContactUs.this,new String[]{Manifest.permission.SEND_SMS},100);
                }

            }

            private void sendMessage() {
                String Number = binding.sentToNumber.getText().toString().trim();
                String Messages = binding.textmessage.getText().toString().trim();
                if (!Messages.isEmpty()){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(Number,null,Messages,null,null);
                    Toast.makeText(ContactUs.this, "SMS Send Successfully", Toast.LENGTH_SHORT).show();
                    binding.myframe.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(ContactUs.this, "Enter Message", Toast.LENGTH_SHORT).show();

                }
            }


        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==0 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
        }
    }
}