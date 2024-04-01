package com.itai.justrun.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itai.justrun.R;
import com.itai.justrun.firebase.FirebaseHandler;
import com.itai.justrun.login_activity.password_activity;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {
    TextView txtTask;
    Button btnAddTask;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setView();
    }

    private void setView() {
        txtTask = findViewById(R.id.txtTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        progress = findViewById(R.id.spinner);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtTask.getText().toString().trim().equals("")){
                    Toast.makeText(AddTaskActivity.this, "Missing task info", Toast.LENGTH_LONG).show();
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                Map<String, Object> data = new HashMap<>();
                data.put("taskDesc", txtTask.getText().toString());
                FirebaseHandler.addTask(data, new FirebaseHandler.SuccessCallbackInterface() {
                    @Override
                    public void onResponse(boolean success) {

                        if(success){
                            finish();
                        }
                        else{
                            Toast.makeText(AddTaskActivity.this, "Could not update firestore database ", Toast.LENGTH_LONG).show();

                        }
                        progress.setVisibility(View.GONE);
                    }
                });


            }
        });
    }
}