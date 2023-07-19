package com.example.cmd.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentAllNoticeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllNoticeFragment extends Fragment {


    FragmentAllNoticeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllNoticeBinding.inflate(inflater);

        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.allNotice("Bearer " + LoginActivity.accessToken).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}