package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityChangeInfoBinding;
import com.example.cmd.fragment.ProfileFragment;
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


        binding.buttonChangeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //커서 포커스 맞추기
                binding.editTextChangeUserName.requestFocus();
                //커서 마지막 글씨에 맞추기
                binding.editTextChangeUserName.setSelection(binding.editTextChangeUserName.getText().length());

                //키보드 올리기
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(binding.editTextChangeUserName, InputMethodManager.SHOW_IMPLICIT);
                Log.d("TEST", "클릭");
            }
        });

        binding.imageBtnChangeInfoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bringInfo();
        check();
        clear();

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

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken",null);

        ChangeMyInfoRequest changeMyInfoRequest = new ChangeMyInfoRequest(name,classIdNumber,birth,majorField,clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        binding.buttonChangeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> changeInfo = severApi.changeInfo(accessToken,changeMyInfoRequest);
                changeInfo.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ChangeInfoActivity.this, "정보가 수정 되었습니다", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void clear() {

        binding.imageBtnChangeName.setOnClickListener(click(binding.editTextChangeUserName));
        binding.imageBtnChangeNumber.setOnClickListener(click(binding.editTextChangeUserNumber));
        binding.imageBtnChangeBirth.setOnClickListener(click(binding.editTextChangeUserBirth));
        binding.imageBtnChangeMajor.setOnClickListener(click(binding.editTextChangeUserMajor));
        binding.imageBtnChangeClub.setOnClickListener(click(binding.editTextChangeUserClub));
    }

    private View.OnClickListener click(EditText id){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.setText(null);
            }
        };

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    private void bringInfo() {

    }


}