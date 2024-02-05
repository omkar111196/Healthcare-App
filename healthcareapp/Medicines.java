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

public class Medicines extends AppCompatActivity {

    private EditText editMedicineName, editTime, editRepeat, editDosage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("medicineReminders");

        editMedicineName = findViewById(R.id.editMedicineName);
        editTime = findViewById(R.id.editTime);
        editRepeat = findViewById(R.id.editRepeat);
        editDosage = findViewById(R.id.editDosage);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        Button buttonHomePage = findViewById(R.id.button8);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicineName = editMedicineName.getText().toString().trim();
                String time = editTime.getText().toString().trim();
                String repeat = editRepeat.getText().toString().trim();
                String dosage = editDosage.getText().toString().trim();

                if (!medicineName.isEmpty() && !time.isEmpty() && !repeat.isEmpty() && !dosage.isEmpty()) {
                    String key = mDatabase.push().getKey();
                    mDatabase.child(key).child("medicineName").setValue(medicineName);
                    mDatabase.child(key).child("time").setValue(time);
                    mDatabase.child(key).child("repeat").setValue(repeat);
                    mDatabase.child(key).child("dosage").setValue(dosage);

                    Toast.makeText(Medicines.this, "Medicine Reminder setup successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Medicines.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medicines.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
