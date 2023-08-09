package com.example.cmd.fragment.signup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.R;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentStep4Binding;
import com.example.cmd.request.SignupRequest;

public class Step4Fragment extends Fragment {


    private String name;
    private String email;
    private String password;
    private String classId;
    private String birth;
    private String majorField;
    private String clubName;
    FragmentStep4Binding binding;
    public Step4Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            Log.d("TEST","sss"+bundle);
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

        if(passW != passCh) {
            binding.textviewSignupCheck.setVisibility(View.VISIBLE);
        }else {
            binding.buttonStep4Next.setOnClickListener(v -> sever());
        }

        return inflater.inflate(R.layout.fragment_step4, container, false);
    }

    private void sever() {
        SignupRequest signupRequest = new SignupRequest(name,email,password,classId,birth,majorField,clubName);
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);
    }

}