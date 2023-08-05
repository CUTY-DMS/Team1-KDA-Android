package com.example.cmd.fragment.signup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.activity.SignupActivity;
import com.example.cmd.databinding.FragmentStep1Binding;


public class Step1Fragment extends Fragment {

    FragmentStep1Binding binding;
    public Step1Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStep1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button nextBtn = binding.buttonStep1Next;

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edittextSignupUsername.getText().toString();
                //String name = view.findViewById(R.id.edittext_signup_username).toString();

                if(name.length() == 0) {
                    binding.textViewStep1Name.setVisibility(View.VISIBLE);
                } else {
                    Step2Fragment step2Fragment = new Step2Fragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    Log.d("TEST","df"+name);


                    step2Fragment.setArguments(bundle);
                    Log.d("TEST","dsf"+bundle);
                    ((SignupActivity) requireActivity()).moveToStep(25);
                }

            }
        });


        Button loginBtn = binding.buttonStep1Previous;
        loginBtn.setText("로그인하기");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}