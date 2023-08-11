package com.example.cmd.fragment.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cmd.R;
import com.example.cmd.activity.SignupActivity;
import com.example.cmd.databinding.FragmentStep3Binding;


public class Step3Fragment extends Fragment {

    FragmentStep3Binding binding;

    private String name;
    private String email;
    private String classN;
    private String birth;
    public Step3Fragment() {
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
            classN = bundle.getString("classNumber");
            birth = bundle.getString("birth");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStep3Binding.inflate(inflater);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        binding.buttonStep3Next.setOnClickListener(v -> {
            String major = binding.edittextSignupMajorField.getText().toString();
            String clubName = binding.edittextSignupClubName.getText().toString();

            if(major.length() == 0 || clubName.length() == 0) {
                binding.textViewStep3Noti.setVisibility(View.VISIBLE);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                bundle.putString("classNumber",classN);
                bundle.putString("birth",birth);
                bundle.putString("name",name);
                bundle.putString("major",major);
                bundle.putString("clubName",clubName);

                Step4Fragment step4Fragment = new Step4Fragment();
                step4Fragment.setArguments(bundle);
                transaction.replace(R.id.frame_layout_signup, step4Fragment);
                transaction.commit();
                //((SignupActivity) requireActivity()).moveToStep(75);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);



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