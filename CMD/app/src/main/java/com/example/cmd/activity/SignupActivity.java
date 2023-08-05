package com.example.cmd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityLoginBinding;
import com.example.cmd.databinding.ActivitySignupBinding;
import com.example.cmd.fragment.signup.Step1Fragment;
import com.example.cmd.fragment.signup.Step2Fragment;
import com.example.cmd.fragment.signup.Step3Fragment;
import com.example.cmd.fragment.signup.Step4Fragment;
import com.example.cmd.request.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressBar = binding.progressBar;
        progressBar.setMax(100);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                if(msg.what == 0) {
                    if(progressStatus < 100) {
                        progressStatus++;
                        progressBar.setProgress(progressStatus);
                    } else{
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        };

        showFragment(new Step1Fragment());
    }

    public void onPreButtonClick(View view) {
        if(progressStatus > 0) {
            progressBar.setVisibility(View.VISIBLE);
            progressStatus -= 25;
            progressBar.setProgress(progressStatus);
            moveToStep(progressStatus);
        }
    }

    public void onNextButton(View view) {
        if(progressStatus < 100) {
            progressBar.setVisibility(View.VISIBLE);
            progressStatus +=25;
            progressBar.setProgress(progressStatus);
            moveToStep(progressStatus);
        }
    }

    public void moveToStep(int progress) {
        switch (progress) {
            case 0:
                showFragment(new Step1Fragment());
                break;
            case 25:
                showFragment(new Step2Fragment());
                break;
            case 50:
                showFragment(new Step3Fragment());
                break;
            case 75:
                showFragment(new Step4Fragment());
                break;
            default:

                break;
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_signup, fragment);
        fragmentTransaction.commit();
    }



}