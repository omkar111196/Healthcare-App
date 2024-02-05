package com.example.healthcareapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hospitals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitals);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        Button buttonHospital1 = findViewById(R.id.buttonHospital1);
        Button buttonHospital2 = findViewById(R.id.buttonHospital2);
        Button buttonHospital3 = findViewById(R.id.buttonHospital3);
        Button buttonHospital4 = findViewById(R.id.buttonHospital4);

        buttonHospital1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("tel:1234567890");
            }
        });

        buttonHospital2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("tel:0987654321");
            }
        });

        buttonHospital3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("tel:1357924680");
            }
        });

        buttonHospital4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("tel:2468013579");
            }
        });

        Button buttonSetupNewHospital = findViewById(R.id.button5);
        buttonSetupNewHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hospitals.this, NewHospital.class);
                startActivity(intent);
                finish();
                   }
        });


        Button buttonHomePage = findViewById(R.id.button6);
        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hospitals.this, Homepage.class);
                startActivity(intent);
                finish();
                   }
        });
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
