package com.example.cmd.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.R;
import com.example.cmd.adapter.AlarmAdapter;
import com.example.cmd.databinding.FragmentAlarmBinding;
import com.google.android.material.tabs.TabLayout;

public class AlarmFragment extends Fragment {

    FragmentAlarmBinding binding;
    private static final String TAG = "Alarm_Fragment";

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private AlarmAdapter adapter;

    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        adapter = new AlarmAdapter(getActivity().getSupportFragmentManager(), 1);

        adapter.addFragment(new AllNoticeFragment());
        adapter.addFragment(new WeClassFragment());

        viewPager2.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager2);
        binding = FragmentAlarmBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}