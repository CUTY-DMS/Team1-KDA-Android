package com.example.cmd.fragment.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cmd.R;
import com.example.cmd.activity.SignupActivity;
import com.example.cmd.databinding.FragmentStep2Binding;


public class Step2Fragment extends Fragment {

    FragmentStep2Binding binding;
    public Step2Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null){
            Log.d("TEST","sss"+args);
        }else{
            Log.d("TEST","ss");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStep2Binding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        Button nextBtn = binding.buttonStep2Next;

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edittextSignupEmail.getText().toString();
                String classN = binding.edittextSignupClassNumber.getText().toString();
                String bir = binding.edittextSignupBirth.getText().toString();

                if(email.length() == 0 || classN.length() == 0 || bir.length() == 0) {
                    binding.textViewStep2Noti.setVisibility(View.VISIBLE);
                } else {
                    Long classNumber = Long.parseLong(binding.edittextSignupClassNumber.getText().toString());
                    Long birth = Long.parseLong(binding.edittextSignupBirth.getText().toString());

                    Bundle args = getArguments();
                    if(args != null) {
                        Log.d("TEST","d");
                        String name = args.getString("name");

                        Bundle bundle = new Bundle();
                        bundle.putString("email",email);
                        bundle.putLong("classNumber",classNumber);
                        bundle.putLong("birth",birth);
                        bundle.putString("name",name);

                        Step3Fragment step3Fragment = new Step3Fragment();
                        step3Fragment.setArguments(bundle);
                        ((SignupActivity) requireActivity()).moveToStep(50);
                    }else {
                        Log.d("TEST","n");
                    }

                }
            }
        });



        Button preBtn = binding.buttonStep2Previous;
        preBtn.setText("이전");
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity) requireActivity()).moveToStep(0);
                //((SignupActivity) requireActivity()).onPreButtonClick(v);
                ((SignupActivity) requireActivity()).onPreButtonClick(v);
            }
        });

    }
}