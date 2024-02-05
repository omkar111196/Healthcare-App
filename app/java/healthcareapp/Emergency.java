package com.example.healthcareapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Emergency extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("emergencyContacts");

        EditText editEmergencyName = findViewById(R.id.editEmergencyName);
        EditText editEmergencyNumber = findViewById(R.id.editEmergencyNumber);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        Button buttonHomePage = findViewById(R.id.button8);
        Button buttonCallEmergency = findViewById(R.id.button9);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emergencyName = editEmergencyName.getText().toString().trim();
                String emergencyNumber = editEmergencyNumber.getText().toString().trim();

                if (!emergencyName.isEmpty() && !emergencyNumber.isEmpty()) {
                    String key = mDatabase.push().getKey();
                    mDatabase.child(key).child("emergencyName").setValue(emergencyName);
                    mDatabase.child(key).child("emergencyNumber").setValue(emergencyNumber);
                    Toast.makeText(Emergency.this, "Emergency contact setup successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Emergency.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emergency.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });

        buttonCallEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emergencyNumber = "100";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + emergencyNumber));
                startActivity(intent);
            }
        });
    }
}
