package com.example.cmd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivitySignupBinding;
import com.example.cmd.request.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textviewGotoLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.buttonSignupSignup.setOnClickListener(v -> signup());
    }

    private void signup() {
        String name = binding.edittextSignupUsername.getText().toString();
        String email = binding.edittextSignupEmail.getText().toString();
        String password = binding.edittextSignupPassword.getText().toString();
        String passwordCheck = binding.edittextSignupPasswordCheck.getText().toString();
        TextView checkPassword = binding.textviewSignupCheck;

        if (name.length() == 0) {
            Toast.makeText(SignupActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (email.length() == 0) {
            Toast.makeText(SignupActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(SignupActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            if (!password.equals(passwordCheck)) {
                checkPassword.setVisibility(View.VISIBLE);
            } else {
                signupResponse();
            }
        }
    }

    private void signupResponse() {
        String username = binding.edittextSignupUsername.getText().toString();
        String email = binding.edittextSignupEmail.getText().toString();
        String password = binding.edittextSignupPassword.getText().toString();
        String classId = binding.edittextSignupClassNumber.getText().toString();
        String birth = binding.edittextSignupBirth.getText().toString();
        String majorField = binding.edittextSignupMajorField.getText().toString();
        String clubName = binding.edittextSignupClubName.getText().toString();

        SignupRequest signupRequest = new SignupRequest(username, email, password, classId, birth, majorField, clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.signup(signupRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}