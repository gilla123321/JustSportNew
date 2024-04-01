package com.itai.justrun.login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.itai.justrun.MainActivity;
import com.itai.justrun.R;

public class password_activity extends AppCompatActivity {
    private EditText txtPassword;
    private Button btnSend;
    private String verificationId;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setView();
    }

    private void setView(){
        mAuth = FirebaseAuth.getInstance();
        txtPassword = findViewById(R.id.txtPassword);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Signup","btn login clicked");
//                startPhoneNumberVerification(txtPhone.getText().toString());
                verifyVerificationCode(txtPassword.getText().toString());
            }
        });

        verificationId = getIntent().getStringExtra("verificationId");
    }

    private void verifyVerificationCode(String code) {
        // Create a PhoneAuthCredential with the code
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
//        progressBar.setVisibility(View.VISIBLE);
        // Sign in the user with the credential
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
//                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // Verification successful, proceed to next activity
                        Intent intent = new Intent(password_activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Verification failed, handle errors
                        if (task.getException() != null) {
                            String errorMessage = task.getException().getMessage();
                            // Display the error message to the user or log it
                            Toast.makeText(password_activity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}