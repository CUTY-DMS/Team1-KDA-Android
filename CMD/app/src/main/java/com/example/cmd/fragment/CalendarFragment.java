package com.example.cmd.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.databinding.FragmentCalendarBinding;

public class CalendarFragment extends Fragment {

    FragmentCalendarBinding binding;

    private SharedPreferences sharedPreferences;


    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater);

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken",null);

        if(LoginActivity.accessToken == null)
            binding.textviewCalendarLogin.setVisibility(View.VISIBLE);

        return binding.getRoot();
    }
}