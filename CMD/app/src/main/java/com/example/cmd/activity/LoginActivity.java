package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityLoginBinding;
import com.example.cmd.request.LoginRequest;
import com.example.cmd.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public static String accessToken;
    public static String refreshToken;
    private static final String KEY_IS_LOGIN = "isLoggedIn";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        binding.textviewGotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        savePreferences(false);
    }

    private void login() {
        String email = binding.edittextEmailId.getText().toString();
        String password = binding.edittextLoginPassword.getText().toString();

        if(email.length() == 0){
            Toast.makeText(LoginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }else {
            loginResponse(email, password);
        }
    }

    private void loginResponse(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email,password);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    savePreferences(true);

                    accessToken = response.body().getAccessToken();
                    refreshToken = response.body().getRefreshToken();
                    Log.d("TEST","토큰"+accessToken);

                    sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Log.d("TEST","로그인 accessToken/" + accessToken);
                    editor.putString("accessToken", accessToken);
                    editor.putString("refreshToken", refreshToken);
                    editor.apply();




                    Toast.makeText(LoginActivity.this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "네트워크 요청 실패: "+t.getMessage());
            }
        });
    }

    private void savePreferences(boolean isLogIn) {
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putBoolean(KEY_IS_LOGIN, isLogIn);
        editor.apply();
    }

}