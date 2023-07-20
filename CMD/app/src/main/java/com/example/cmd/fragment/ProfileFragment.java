package com.example.cmd.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cmd.activity.ChangeInfoActivity;
import com.example.cmd.activity.ChangePasswordActivity;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentProfileBinding;
import com.example.cmd.response.MypageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    FragmentProfileBinding binding;

    public ProfileFragment() {
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater);


        bringInfo();


        ImageButton editBtn = binding.imageBtnProfileEdit;
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonProfileChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    private void bringInfo() {
        TextView email = binding.textViewProfileEmail;
        TextView name = binding.textViewProfileUserName;
        TextView classIdNumber = binding.textViewProfileUserClassNumber;
        TextView birth = binding.textViewProfileUserBirth;
        TextView majorField = binding.textViewProfileUserMajor;
        TextView clubName = binding.textViewProfileUserClub;

        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.myPage().enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        email.setText(response.body().getEmail());
                        name.setText(response.body().getName());
                        classIdNumber.setText(response.body().getClassIdNumber());
                        birth.setText(response.body().getBirth());
                        majorField.setText(response.body().getMajorField());
                        clubName.setText(response.body().getClubName());
                    }

                }
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {

            }
        });
    }
}