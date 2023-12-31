package com.example.cmd.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cmd.R;
import com.example.cmd.activity.ChangeInfoActivity;
import com.example.cmd.activity.ChangePasswordActivity;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentProfileBinding;
import com.example.cmd.databinding.FragmentProfileNoBinding;
import com.example.cmd.response.MypageResponse;
import com.example.cmd.response.ReissueResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    FragmentProfileNoBinding noLoginBinding;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (LoginActivity.accessToken != null) {
            binding = FragmentProfileBinding.inflate(inflater);
            bringInfo();


            binding.buttonProfileChangePassword.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            });

            binding.buttonProfileLogout.setOnClickListener(v -> logOut());

            return binding.getRoot();
        } else {
            noLoginBinding = FragmentProfileNoBinding.inflate(inflater);

            noLoginBinding.buttonProfileNoGoLogin.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            });

            return noLoginBinding.getRoot();
        }
    }

    private void bringInfo() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String refreshToken = sharedPreferences.getString("refreshToken", null);

        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        Call<MypageResponse> myPage = severApi.myPage(accessToken);

        myPage.enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        binding.textViewProfileEmail.setText(response.body().getEmail());
                        binding.textViewProfileUserName.setText(response.body().getName());
                        binding.textViewProfileTopName.setText(response.body().getName());
                        binding.textViewProfileUserClassNumber.setText(String.valueOf(response.body().getClassId()));
                        binding.textViewProfileUserBirth.setText(String.valueOf(response.body().getBirth()));
                        binding.textViewProfileUserMajor.setText(response.body().getMajorField());
                        binding.textViewProfileUserClub.setText(response.body().getClubName());

                        binding.imageBtnProfileEdit.setOnClickListener(v -> {
                            Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", response.body().getName());
                            bundle.putLong("classId", response.body().getClassId());
                            bundle.putLong("birth", response.body().getBirth());
                            bundle.putString("majorField", response.body().getMajorField());
                            bundle.putString("clubName", response.body().getClubName());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        });
                    }

                }
                if (response.code() == 403 || response.code() == 401) {
                    Call<ReissueResponse> responseCall = severApi.reissue(refreshToken);
                    responseCall.enqueue(new Callback<ReissueResponse>() {
                        @Override
                        public void onResponse(Call<ReissueResponse> call, Response<ReissueResponse> response) {
                            if (response.isSuccessful()) {
                                String newAccessToken;
                                newAccessToken = response.body().getAccessToken();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("accessToken", newAccessToken);
                                editor.apply();

                                if (response.code() == 401 || response.code() == 403) {
                                    Log.d("TEST", "refreshToken 만료");
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(getContext(), "다시 로그인 해주세요", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ReissueResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {

            }
        });

    }

    private void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogTheme));

        builder.setTitle("로그아웃");
        builder.setMessage("로그아웃 하실?");

        builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Log.아웃", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

                Toast.makeText(getContext(), "Log.아웃 되었습니다!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_radious);
        dialog.show();
    }
}