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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewHospital extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newhospitals);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("hospitals");

        EditText editHospitalName1 = findViewById(R.id.editHospitalName1);
        EditText editHospitalNumber = findViewById(R.id.editHospitalNumber);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        Button buttonHomePage = findViewById(R.id.button8);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hospitalName = editHospitalName1.getText().toString().trim();
                String contactNumber = editHospitalNumber.getText().toString().trim();

                if (!hospitalName.isEmpty() && !contactNumber.isEmpty()) {
                    String key = mDatabase.push().getKey();
                    mDatabase.child(key).child("name").setValue(hospitalName);
                    mDatabase.child(key).child("contactNumber").setValue(contactNumber);
                    Toast.makeText(NewHospital.this, "Hospital setup successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewHospital.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewHospital.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
