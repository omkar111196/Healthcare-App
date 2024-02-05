package com.example.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText editTextEmail2, editTextPassword2, editName2, editPhone2, editCity2, editTextAddress2;
    private Button buttonSignUp2, buttonLogin2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        editTextEmail2 = findViewById(R.id.editTextEmail2);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        editName2 = findViewById(R.id.editName2);
        editPhone2 = findViewById(R.id.editPhone2);
        editCity2 = findViewById(R.id.editCity2);
        editTextAddress2 = findViewById(R.id.editTextAddress2);
        buttonSignUp2 = findViewById(R.id.buttonSignUp2);
        buttonLogin2 = findViewById(R.id.buttonLogin2);
        mAuth = FirebaseAuth.getInstance();

        buttonSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail2.getText().toString().trim();
                String password = editTextPassword2.getText().toString().trim();
                String name = editName2.getText().toString().trim();
                String phone = editPhone2.getText().toString().trim();
                String city = editCity2.getText().toString().trim();
                String address = editTextAddress2.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || city.isEmpty() || address.isEmpty()) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String userId = mAuth.getCurrentUser().getUid();

                                        DatabaseReference userDataRef = FirebaseDatabase.getInstance().getReference().child("userData").child(userId);
                                        userDataRef.child("name").setValue(name);
                                        userDataRef.child("phone").setValue(phone);
                                        userDataRef.child("city").setValue(city);
                                        userDataRef.child("address").setValue(address);

                                        Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUp.this, Details.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        buttonLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this, "Login clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
