package com.example.cmd.fragment.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cmd.R;
import com.example.cmd.activity.SignupActivity;
import com.example.cmd.databinding.FragmentStep3Binding;


public class Step3Fragment extends Fragment {

    FragmentStep3Binding binding;
    public Step3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStep3Binding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        Button nextBtn = binding.buttonStep3Next;
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String major = binding.edittextSignupMajorField.getText().toString();
                String clubName = binding.edittextSignupClubName.getText().toString();

                if(major.length() == 0 || clubName.length() == 0) {
                    binding.textViewStep3Noti.setVisibility(View.VISIBLE);
                } else {
                    Bundle args = getArguments();
                    String name = args.getString("name");
                    String email = args.getString("email");
                    Long classNumber = args.getLong("classNumber");
                    Long birth = args.getLong("birth");


                    Bundle bundle = new Bundle();
                    bundle.putString("email",email);
                    bundle.putLong("classNumber",classNumber);
                    bundle.putLong("birth",birth);
                    bundle.putString("name",name);
                    bundle.putString("major",major);
                    bundle.putString("clubName",clubName);

                    Step3Fragment step3Fragment = new Step3Fragment();
                    step3Fragment.setArguments(bundle);
                    ((SignupActivity) requireActivity()).moveToStep(75);
                }


            }
        });


        Button preBtn = binding.buttonStep3Previous;
        preBtn.setText("이전");
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity) requireActivity()).onPreButtonClick(v);
            }
        });

    }
}