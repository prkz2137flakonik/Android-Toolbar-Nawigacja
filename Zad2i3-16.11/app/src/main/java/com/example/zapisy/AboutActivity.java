package com.example.zapisy;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "AboutActivity - onCreate");

        setContentView(R.layout.activity_about);

        Button btnBack = findViewById(R.id.btnAboutBack);
        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "AboutActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "AboutActivity - onDestroy");
    }
}