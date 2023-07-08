package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityLoginBinding;
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

        binding.textviewGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonSignupSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup(){
        String email = binding.edittextSignupEmail.getText().toString();
        String password = binding.edittextSignupPassword.getText().toString();
        String passwordCheck = binding.edittextSignupPasswordCheck.getText().toString();
        TextView checkPassword = binding.textviewSignupCheck;

        if(email.length() == 0){
            Toast.makeText(SignupActivity.this, "이메일을 입력해주세요",Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(SignupActivity.this,"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();
        }else {
            if(password != passwordCheck){
                checkPassword.setVisibility(View.VISIBLE);
            }
            signupResponse(email,password);
        }
    }

    private void signupResponse(String email, String password) {
        SignupRequest signupRequest = new SignupRequest(email,password);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.signup(signupRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}