package com.example.cmd.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityChangePasswordBinding;
import com.example.cmd.request.ChangePasswordRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBtnChangePasswordClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        changePassword();

    }

    private void changePassword() {
        TextView pwCheck = binding.textviewChangePasswordCheck;
        Button change = binding.buttonChangePasswordChange;

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPw = binding.editTextNowPassword.getText().toString();
                String newPw = binding.editTextNewPassword.getText().toString();
                String newPwCheck = binding.editTextNewPasswordCheck.getText().toString();

                if (!newPw.equals(newPwCheck)) {
                    pwCheck.setVisibility(View.VISIBLE);
                } else {
                    sever(oldPw, newPw, newPwCheck);
                }
            }
        });

    }

    private void sever(String oldPw, String newPw, String newPwCheck) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(oldPw, newPw, newPwCheck);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        Call<Void> changePassword = severApi.changePassword(accessToken, changePasswordRequest);
        changePassword.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChangePasswordActivity.this, "비밀번호가 수정되었습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}