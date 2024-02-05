package com.example.healthcareapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    private Button buttonSubmit1, buttonSubmit2, buttonSubmit3, buttonSubmit4, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        buttonSubmit1 = findViewById(R.id.buttonSubmit1);
        buttonSubmit2 = findViewById(R.id.buttonSubmit2);
        buttonSubmit3 = findViewById(R.id.buttonSubmit3);
        buttonSubmit4 = findViewById(R.id.buttonSubmit4);
        buttonLogout = findViewById(R.id.buttonLogout);


        buttonSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic here
                Toast.makeText(Homepage.this, "Opening Medicine Reminder", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Homepage.this, Medicines.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic here
                Toast.makeText(Homepage.this, "Showing available Hospitals", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Homepage.this, Hospitals.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSubmit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic here
                Toast.makeText(Homepage.this, "Showing Home Checkup Options", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Homepage.this, HomeCheckup.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSubmit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic here
                Toast.makeText(Homepage.this, "Showing Emergency Options", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Homepage.this, Emergency.class);
                startActivity(intent);
                finish();
            }
        });

        // Set onClickListener for the logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic here
                Toast.makeText(Homepage.this, "Logging Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
