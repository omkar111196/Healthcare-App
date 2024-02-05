package com.example.healthcareapp;

import android.content.Intent;
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

public class HomeCheckup extends AppCompatActivity {

    private EditText editHospitalName, editTime2, editCheckupType, editDoctorName;
    private Button buttonSubmit, buttonHomePage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_checkup);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("checkups");

        editHospitalName = findViewById(R.id.editHospitalName);
        editTime2 = findViewById(R.id.editTime2);
        editCheckupType = findViewById(R.id.editCheckupType);
        editDoctorName = findViewById(R.id.editDoctorName);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonHomePage = findViewById(R.id.button7);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hospitalName = editHospitalName.getText().toString().trim();
                String time = editTime2.getText().toString().trim();
                String checkupType = editCheckupType.getText().toString().trim();
                String doctorName = editDoctorName.getText().toString().trim();

                if (!hospitalName.isEmpty() && !time.isEmpty() && !checkupType.isEmpty() && !doctorName.isEmpty()) {
                    String key = mDatabase.push().getKey();
                    mDatabase.child(key).child("hospitalName").setValue(hospitalName);
                    mDatabase.child(key).child("time").setValue(time);
                    mDatabase.child(key).child("checkupType").setValue(checkupType);
                    mDatabase.child(key).child("doctorName").setValue(doctorName);
                    Toast.makeText(HomeCheckup.this, "Data Submitted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeCheckup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCheckup.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}
