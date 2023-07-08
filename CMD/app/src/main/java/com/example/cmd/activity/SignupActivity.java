package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        String name = binding.edittextSignupUsername.getText().toString();
        String email = binding.edittextSignupEmail.getText().toString();
        String password = binding.edittextSignupPassword.getText().toString();
        String passwordCheck = binding.edittextSignupPasswordCheck.getText().toString();
        TextView checkPassword = binding.textviewSignupCheck;

        if(name.length() == 0){
            Toast.makeText(SignupActivity.this, "이름을 입력해주세요",Toast.LENGTH_SHORT).show();
        }else if(email.length() == 0){
            Toast.makeText(SignupActivity.this, "이메일을 입력해주세요",Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(SignupActivity.this,"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();
        }else {
            if(!password.equals(passwordCheck)){
                checkPassword.setVisibility(View.VISIBLE);
            }
            signupResponse();
        }
    }

    private void signupResponse() {
        String username = binding.edittextSignupUsername.getText().toString().trim();
        String email = binding.edittextSignupEmail.getText().toString().trim();
        String password = binding.edittextSignupPassword.getText().toString().trim();

        SignupRequest signupRequest = new SignupRequest(username,email,password);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.signup(signupRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SignupActivity.this ,"회원가입에 성공했습니다" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입에 실패했습니다" , Toast.LENGTH_SHORT).show();
                Log.e("TAG", "네트워크 요청 실패: "+t.getMessage());
            }
        });
    }
}