package com.itai.justrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.itai.justrun.activities.AddTaskActivity;
import com.itai.justrun.activities.NavigatorActivity;
import com.itai.justrun.firebase.FirebaseHandler;
import com.itai.justrun.login_activity.SignupActivity;
import com.itai.justrun.utils.Utils;

public class MainActivity extends AppCompatActivity {
    Button btnLog, btnAddTaskScreen, btnOpenShortPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        FirebaseHandler.getTasks();
    }

    private void setView() {

        AppUser.sherdInstance().setPhone(FirebaseHandler.getPhoneNumber());
        btnLog = findViewById(R.id.btnLogout);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.showAlertConformWithCallback("Logout", "Are you sure you want to logout?", MainActivity.this, new Utils.UserClick() {
                    @Override
                    public void onUserClick(boolean ok) {
                        if (ok) {
                            FirebaseHandler.logout(MainActivity.this);
                            finish();
                            Intent intent = new Intent(MainActivity.this, SignupActivity.class);

                        }
                    }
                });

            }
        });

        btnOpenShortPractice = findViewById(R.id.button2);
        btnOpenShortPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShortPracticeActivity.class);
                startActivity(intent);
            }
        });


    }
}