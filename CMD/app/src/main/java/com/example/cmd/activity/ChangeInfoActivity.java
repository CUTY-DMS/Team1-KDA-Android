package com.example.cmd.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        binding.editTextChangeUserName.setText(bundle.getString("name"));
        binding.editTextChangeUserNumber.setText(String.valueOf(bundle.getLong("classId")));
        binding.editTextChangeUserBirth.setText(String.valueOf(bundle.getLong("birth")));
        binding.editTextChangeUserMajor.setText(bundle.getString("majorField"));
        binding.editTextChangeUserClub.setText(bundle.getString("clubName"));

        binding.buttonChangeEdit.setOnClickListener(v -> showSoftKeyboard(binding.editTextChangeUserName));

        binding.imageBtnChangeInfoClose.setOnClickListener(v -> onBackPressed());

        check();
        clear();
    }

    private void showSoftKeyboard(EditText editText) {
        //커서 포커스 맞추기
        editText.requestFocus();
        //커서 마지막 글씨에 맞추기
        editText.setSelection(editText.getText().length());

        //키보드 올리기
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void check() {
        TextWatcher textWatcher = createTextWatcher();

        binding.editTextChangeUserName.addTextChangedListener(textWatcher);
        binding.editTextChangeUserNumber.addTextChangedListener(textWatcher);
        binding.editTextChangeUserBirth.addTextChangedListener(textWatcher);
        binding.editTextChangeUserMajor.addTextChangedListener(textWatcher);
        binding.editTextChangeUserClub.addTextChangedListener(textWatcher);
    }

    private TextWatcher createTextWatcher() {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.buttonChangeEdit.setVisibility(View.INVISIBLE);
                binding.buttonChangeSave.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                sendDataToSever();
            }
        };
    }

    private void sendDataToSever() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);

        String name = binding.editTextChangeUserName.getText().toString();
        String classIdNumber = binding.editTextChangeUserNumber.getText().toString();
        String birth = binding.editTextChangeUserBirth.getText().toString();
        String majorField = binding.editTextChangeUserMajor.getText().toString();
        String clubName = binding.editTextChangeUserClub.getText().toString();

        Long classIdNum = null;
        Long birthDay = null;
        try {
            classIdNum = Long.parseLong(classIdNumber);
            birthDay = Long.parseLong(birth);

        } catch (NumberFormatException e) {

        }

        ChangeMyInfoRequest changeMyInfoRequest = new ChangeMyInfoRequest(name, classIdNum, birthDay, majorField, clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        binding.buttonChangeSave.setOnClickListener(v -> {
            severApi.changeInfo(accessToken, changeMyInfoRequest).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ChangeInfoActivity.this, "정보가 수정 되었습니다", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ChangeInfoActivity.this, "통신 실패", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void clear() {

        binding.imageBtnChangeName.setOnClickListener(click(binding.editTextChangeUserName));
        binding.imageBtnChangeNumber.setOnClickListener(click(binding.editTextChangeUserNumber));
        binding.imageBtnChangeBirth.setOnClickListener(click(binding.editTextChangeUserBirth));
        binding.imageBtnChangeMajor.setOnClickListener(click(binding.editTextChangeUserMajor));
        binding.imageBtnChangeClub.setOnClickListener(click(binding.editTextChangeUserClub));
    }

    private View.OnClickListener click(EditText id) {
        return v -> id.setText(null);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}