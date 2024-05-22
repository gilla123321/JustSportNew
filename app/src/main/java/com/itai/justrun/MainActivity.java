package com.itai.justrun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.itai.justrun.activities.AddTaskActivity;
import com.itai.justrun.activities.NavigatorActivity;
import com.itai.justrun.firebase.FirebaseHandler;
import com.itai.justrun.login_activity.SignupActivity;
import com.itai.justrun.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    Button btnLog, btnAddTaskScreen, btnOpenShortPractice;

    MainRecyclerAdapter adapter;
    RecyclerView recyclerView;
    ClickListener listener;

    CalendarView calView;

    private Calendar calendar = Calendar.getInstance();

    private  List<Task> taskArrayList = new ArrayList<>();
    private List<Task> filterTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
Log.e("rrrr", "line 36");
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainRecyclerAdapter(filterTasks, this, new ClickListener() {
            @Override
            public void click(int index) {
                Log.e("yyyyy" ,"line 41");
                adapter.notifyDataSetChanged();
                // Handle click event here, if needed
            }
        });
        recyclerView.setAdapter(adapter);

        FirebaseHandler.getTasks(new FirebaseHandler.TaskCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                taskArrayList.addAll(tasks);
                sortTasks();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, "Error loading tasks: " + error, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sortTasks() {
        filterTasks.clear();
        filterTasks.addAll(taskArrayList.stream().filter(new Predicate<Task>() {
            @Override
            public boolean test(Task task) {
                return task.getDate() == calendar.getTimeInMillis();
            }
        }).collect(Collectors.toList()));
    }


    // Sample data for RecyclerView
    private List<taskData> getData()
    {

        //simulation data - change to reading from DB
        List<taskData> list = new ArrayList<>();
        list.add(new taskData("First Exam", "May 23, 2015", "Best Of Luck", "11:00"));
        list.add(new taskData("Second Exam", "June 09, 2015", "b of l", "11:00"));
        list.add(new taskData("My Test Exam", "April 27, 2017", "This is testing exam ..", "11:00"));


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
                intent.putExtra("date", calendar.getTimeInMillis());
                startActivityForResult(intent, 1);
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

        Calendar temp = Calendar.getInstance();
        temp.clear();
        temp.setTimeInMillis(calendar.getTimeInMillis());
        calendar.clear();
        calendar.set(temp.get(Calendar.YEAR),temp.get(Calendar.MONTH), temp.get(Calendar.DAY_OF_MONTH));
           calView=findViewById(R.id.calendarView);
           calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
               @Override
               public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                   calendar.clear();
                   calendar.set(year, month, dayOfMonth);
                   sortTasks();
                   adapter.notifyDataSetChanged();
               }
           });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Task task = (Task) data.getExtras().get("task");
            taskArrayList.add(task);
            sortTasks();
            adapter.notifyDataSetChanged();
        }
    }
}