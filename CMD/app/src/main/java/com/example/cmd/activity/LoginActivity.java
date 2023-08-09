package com.example.cmd.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityLoginBinding;
import com.example.cmd.request.LoginRequest;
import com.example.cmd.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static String accessToken;
    public static String refreshToken;
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLoginLogin.setOnClickListener(v -> login());

        binding.textviewGotoSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        String email = binding.edittextEmailId.getText().toString();
        String password = binding.edittextLoginPassword.getText().toString();

        if (email.length() == 0) {
            Toast.makeText(LoginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            loginResponse(email, password);
        }
    }

    private void loginResponse(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    accessToken = response.body().getAccessToken();
                    refreshToken = response.body().getRefreshToken();

                    sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("accessToken", accessToken);
                    editor.putString("refreshToken", refreshToken);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                if (response.code() == 500) {
                    binding.textViewLoginCheck.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}