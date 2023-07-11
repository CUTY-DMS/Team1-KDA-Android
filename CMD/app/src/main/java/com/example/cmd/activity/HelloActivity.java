package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cmd.R;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }
}