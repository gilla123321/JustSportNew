package com.itai.justrun.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.itai.justrun.MainActivity;
import com.itai.justrun.R;
import com.itai.justrun.firebase.FirebaseHandler;
import com.itai.justrun.login_activity.SignupActivity;

public class NavigatorActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);


    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorDelay();
    }


    void navigate(){
        FirebaseHandler.navigate(new FirebaseHandler.SuccessCallbackInterface() {
            @Override
            public void onResponse(boolean success) {
                Intent intent;
                if (success){
                    intent = new Intent(NavigatorActivity.this, MainActivity.class);
                }
                else{
                    intent = new Intent(NavigatorActivity.this, SignupActivity.class);
                }

                startActivity(intent);
                finish();
            }
        });
    }
    void navigatorDelay(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
              navigate();
            }
        }, 5000);

    }




}