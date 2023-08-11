package com.example.cmd.fragment.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
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

        binding.buttonStep1Next.setOnClickListener(v -> {
            String name = binding.edittextSignupUsername.getText().toString();

            if (name.length() == 0) {
                binding.textViewStep1Name.setVisibility(View.VISIBLE);
            } else {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("name", name);

                Step2Fragment step2Fragment = new Step2Fragment();
                step2Fragment.setArguments(bundle);

                transaction.replace(R.id.frame_layout_signup, step2Fragment);
                transaction.commit();
                //.moveToStep(25);
            }
        });

        binding.buttonStep1Previous.setText("로그인하기");

        binding.buttonStep1Previous.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}