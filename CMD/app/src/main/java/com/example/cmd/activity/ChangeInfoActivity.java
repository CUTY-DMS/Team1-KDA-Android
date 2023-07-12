package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityChangeInfoBinding;
import com.example.cmd.request.ChangeMyInfoRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfoActivity extends AppCompatActivity {

    ActivityChangeInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangeInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = binding.editTextChangeUserName.getText().toString();
        String classIdNumber = binding.editTextChangeUserNumber.getText().toString();
        String birth = binding.editTextChangeUserBirth.getText().toString();
        String majorField = binding.editTextChangeUserMajor.getText().toString();
        String clubName = binding.editTextChangeUserClub.getText().toString();

        check(name,classIdNumber,birth,majorField,clubName);
    }

    private void check(String name,String classIdNumber,String birth,String majorField, String clubName) {
        EditText userName = binding.editTextChangeUserName;
        EditText userClassNumber = binding.editTextChangeUserNumber;
        EditText userBirth = binding.editTextChangeUserBirth;
        EditText userMajorField = binding.editTextChangeUserMajor;
        EditText userClubName = binding.editTextChangeUserClub;

        TextWatcher textWatcher = createTextWatcher();

        userName.addTextChangedListener(textWatcher);
        userClassNumber.addTextChangedListener(textWatcher);
        userBirth.addTextChangedListener(textWatcher);
        userMajorField.addTextChangedListener(textWatcher);
        userClubName.addTextChangedListener(textWatcher);
    }

    private TextWatcher createTextWatcher() {

        Button editBtn = binding.buttonChangeEdit;
        Button saveBtn = binding.buttonChangeSave;


        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editBtn.setVisibility(View.INVISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                sever();
            }
        };
    }

    private void sever() {
        String name = binding.editTextChangeUserName.getText().toString();
        String classIdNumber = binding.editTextChangeUserNumber.getText().toString();
        String birth = binding.editTextChangeUserBirth.getText().toString();
        String majorField = binding.editTextChangeUserMajor.getText().toString();
        String clubName = binding.editTextChangeUserClub.getText().toString();

        ChangeMyInfoRequest changeMyInfoRequest = new ChangeMyInfoRequest(name,classIdNumber,birth,majorField,clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.changeInfo(changeMyInfoRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}