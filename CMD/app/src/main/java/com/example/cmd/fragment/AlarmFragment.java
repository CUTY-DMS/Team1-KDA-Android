package com.example.cmd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.AlarmAdapter;
import com.example.cmd.databinding.FragmentAlarmBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AlarmFragment extends Fragment {

    final int NUM_PAGED = 2;
    FragmentAlarmBinding binding;
    Fragment allFragment, weFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlarmBinding.inflate(inflater, container, false);

        allFragment = new AllNoticeFragment();
        weFragment = new WeClassFragment();

        ViewPager2 viewPager2 = binding.viewPagerAlarm;
        viewPager2.setAdapter(new AlarmAdapter(this, allFragment, weFragment, (int) NUM_PAGED));
        viewPager2.setCurrentItem(0);

        TabLayout tabLayout = binding.tabLayoutAlarm;
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("전체 공지");
                } else {
                    tab.setText("우리 반 공지");
                }
            }
        });
        tabLayoutMediator.attach();

        if (LoginActivity.accessToken == null) {
            binding.textviewAlarmLogin.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}