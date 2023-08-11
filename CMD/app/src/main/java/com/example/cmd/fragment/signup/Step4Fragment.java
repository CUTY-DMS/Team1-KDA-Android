package com.example.cmd.fragment.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentStep4Binding;
import com.example.cmd.request.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Step4Fragment extends Fragment {


    FragmentStep4Binding binding;
    private String name;
    private String email;
    private String password;
    private String classId;
    private String birth;
    private String majorField;
    private String clubName;

    public Step4Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
            email = bundle.getString("email");
            classId = bundle.getString("classNumber");
            birth = bundle.getString("birth");
            majorField = bundle.getString("major");
            clubName = bundle.getString("clubName");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStep4Binding.inflate(inflater);

        String passW = binding.edittextSignupPassword.getText().toString();
        String passCh = binding.edittextSignupPasswordCheck.getText().toString();

        if (passW != passCh) {
            binding.textviewSignupCheck.setVisibility(View.VISIBLE);
        } else {
            binding.buttonStep4Next.setOnClickListener(v -> sever());
        }

        return inflater.inflate(R.layout.fragment_step4, container, false);
    }

    private void sever() {
        SignupRequest signupRequest = new SignupRequest(name, email, password, classId, birth, majorField, clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.signup(signupRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

}