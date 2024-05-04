package com.itai.justrun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itai.justrun.activities.AddTaskActivity;
import com.itai.justrun.activities.NavigatorActivity;
import com.itai.justrun.firebase.FirebaseHandler;
import com.itai.justrun.login_activity.SignupActivity;
import com.itai.justrun.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnLog, btnAddTaskScreen, btnOpenShortPractice;

    MainRecyclerAdapter adapter;
    RecyclerView recyclerView;
    ClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainRecyclerAdapter(new ArrayList<>(), this, new ClickListener() {
            @Override
            public void click(int index) {
                // Handle click event here, if needed
            }
        });
        recyclerView.setAdapter(adapter);

        FirebaseHandler.getTasks(new FirebaseHandler.TaskCallback() {
            @Override
            public void onTaskLoaded(List<taskData> tasks) {
                adapter.updateData(tasks);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, "Error loading tasks: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }


    // Sample data for RecyclerView
    private List<taskData> getData()
    {

        //simulation data - change to reading from DB
        List<taskData> list = new ArrayList<>();
        list.add(new taskData("First Exam",
                "May 23, 2015",
                "Best Of Luck"));
        list.add(new taskData("Second Exam",
                "June 09, 2015",
                "b of l"));
        list.add(new taskData("My Test Exam",
                "April 27, 2017",
                "This is testing exam .."));

        return list;
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
        btnAddTaskScreen= findViewById(R.id.btnAddTaskScreen);
        btnAddTaskScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
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