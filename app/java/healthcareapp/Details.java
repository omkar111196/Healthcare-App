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

public class Details extends AppCompatActivity {

    private EditText editHeight, editWeight, editGender, editID, editAge, editAllergies;
    private Button buttonSubmit;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("userData").child(mAuth.getCurrentUser().getUid());

        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        editGender = findViewById(R.id.editGender);
        editID = findViewById(R.id.editID);
        editAge = findViewById(R.id.editAge);
        editAllergies = findViewById(R.id.editAllergies);
        buttonSubmit = findViewById(R.id.buttonSubmit);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = editHeight.getText().toString().trim();
                String weight = editWeight.getText().toString().trim();
                String gender = editGender.getText().toString().trim();
                String id = editID.getText().toString().trim();
                String age = editAge.getText().toString().trim();
                String allergies = editAllergies.getText().toString().trim();

                if (height.isEmpty() || weight.isEmpty() || gender.isEmpty() || id.isEmpty() || age.isEmpty() || allergies.isEmpty()) {
                    Toast.makeText(Details.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    mDatabase.child("height").setValue(height);
                    mDatabase.child("weight").setValue(weight);
                    mDatabase.child("gender").setValue(gender);
                    mDatabase.child("id").setValue(id);
                    mDatabase.child("age").setValue(age);
                    mDatabase.child("allergies").setValue(allergies);

                    Toast.makeText(Details.this, "Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Details.this, Homepage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
