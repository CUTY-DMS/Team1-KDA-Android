package com.example.cmd.fragment.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cmd.R;
import com.example.cmd.activity.SignupActivity;
import com.example.cmd.databinding.FragmentStep2Binding;


public class Step2Fragment extends Fragment {

    FragmentStep2Binding binding;
    private String name;

    public Step2Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStep2Binding.inflate(inflater);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        binding.buttonStep2Next.setOnClickListener(v -> {
            String email = binding.edittextSignupEmail.getText().toString();
            String classN = binding.edittextSignupClassNumber.getText().toString();
            String bir = binding.edittextSignupBirth.getText().toString();

            if (email.length() == 0 || classN.length() == 0 || bir.length() == 0) {
                binding.textViewStep2Noti.setVisibility(View.VISIBLE);
            } else {

                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("classNumber", classN);
                bundle.putString("birth", bir);
                bundle.putString("name", name);

                Step3Fragment step3Fragment = new Step3Fragment();
                step3Fragment.setArguments(bundle);
                transaction.replace(R.id.frame_layout_signup, step3Fragment);
                transaction.commit();
                //((SignupActivity) requireActivity()).moveToStep(50);

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        Button preBtn = binding.buttonStep2Previous;
        preBtn.setText("이전");
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity) requireActivity()).moveToStep(0);
                ((SignupActivity) requireActivity()).onPreButtonClick(v);
            }
        });
    }
}