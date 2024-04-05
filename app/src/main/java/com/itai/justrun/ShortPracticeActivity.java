package com.itai.justrun;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itai.justrun.R;

public class ShortPracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_short_practice);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
               // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                // logic to set the EditText could go here
                Log.e("XXXXXX","onTick " + millisUntilFinished );
            }

            public void onFinish() {
                //mTextField.setText("done!");
            }

        }.start();
    }
}