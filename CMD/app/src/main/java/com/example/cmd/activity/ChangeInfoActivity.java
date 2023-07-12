package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
        //Button editBtn = binding.buttonChangeEdit;
        //EditText first = binding.editTextChangeUserName;

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
        check();
        clear();

    }

    private void check() {
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

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken",null);

        ChangeMyInfoRequest changeMyInfoRequest = new ChangeMyInfoRequest(name,classIdNumber,birth,majorField,clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        Call<Void> call = severApi.changeInfo(accessToken,changeMyInfoRequest);
        call.enqueue(new Callback<Void>() {
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

    private void clear() {
        ImageButton name = binding.imageBtnChangeName;
        ImageButton number = binding.imageBtnChangeNumber;
        ImageButton birth = binding.imageBtnChangeBirth;
        ImageButton major = binding.imageBtnChangeMajor;
        ImageButton club = binding.imageBtnChangeClub;

        EditText userName = binding.editTextChangeUserName;
        EditText userClassNumber = binding.editTextChangeUserNumber;
        EditText userBirth = binding.editTextChangeUserBirth;
        EditText userMajorField = binding.editTextChangeUserMajor;
        EditText userClubName = binding.editTextChangeUserClub;

        name.setOnClickListener(click(userName));
        number.setOnClickListener(click(userClassNumber));
        birth.setOnClickListener(click(userBirth));
        major.setOnClickListener(click(userMajorField));
        club.setOnClickListener(click(userClubName));
    }

    private View.OnClickListener click(EditText id){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.setText(null);
            }
        };

    }
}