package com.itai.justrun.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.credentials.Action;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itai.justrun.R;
import com.itai.justrun.Task;
import com.itai.justrun.firebase.FirebaseHandler;
import com.itai.justrun.login_activity.password_activity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddTaskActivity extends AppCompatActivity {
    TextView txtTask;
    TextView txtStartTime;
    Button btnAddTask;
    String startTime;
    ProgressBar progress;

    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        date = getIntent().getLongExtra("date", 0L);
        setView();
    }

    private void setView() {
        txtTask = findViewById(R.id.txtTask);
        txtStartTime = findViewById(R.id.txtStartTime);
        btnAddTask = findViewById(R.id.btnAddTask);
        progress = findViewById(R.id.spinner);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: add time for practice and set notification

                Log.e("XXX","Line 39");
                if(txtTask.getText().toString().trim().equals("")||txtStartTime.getText().toString().trim().equals("")){
                    Toast.makeText(AddTaskActivity.this, "Missing task info", Toast.LENGTH_LONG).show();
                    Log.e("XXX","Line 42");
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                Task task = new Task(UUID.randomUUID().toString(), txtTask.getText().toString(), date, txtStartTime.getText().toString());
                FirebaseHandler.addTask(task, new FirebaseHandler.SuccessCallbackInterface() {
                    @Override
                    public void onResponse(boolean success) {

                        if(success){
                            Log.e("XXX","Succes");
                            Intent intent  = new Intent();
                            intent.putExtra("task", task);
                            Log.e("ttttt", task.getStartTime().toString());
                            setResult(Activity.RESULT_OK, intent);

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