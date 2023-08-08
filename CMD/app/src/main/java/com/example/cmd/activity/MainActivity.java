package com.example.cmd.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivityMainBinding;
import com.example.cmd.fragment.AlarmFragment;
import com.example.cmd.fragment.CalendarFragment;
import com.example.cmd.fragment.ProfileFragment;
import com.example.cmd.fragment.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final AlarmFragment alarmFragment = new AlarmFragment();
    private final CalendarFragment calendarFragment = new CalendarFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final ScheduleFragment scheduleFragment = new ScheduleFragment();
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private Map<Integer, Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTheme(R.style.MainActivityTheme);

        fragmentManager = getSupportFragmentManager();
        fragments = new HashMap<>();
        initializeFragments();

        initBottomNavigation();
    }

    private void initializeFragments() {
        fragments.put(R.id.menu_calendar, calendarFragment);
        fragments.put(R.id.menu_schedule, scheduleFragment);
        fragments.put(R.id.menu_alarm, alarmFragment);
        fragments.put(R.id.menu_profile, profileFragment);
    }

    private void initBottomNavigation() {
        BottomNavigationView bottomNavigationView = binding.bottomNavigationMain;

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = fragments.get(item.getItemId());

            if (fragment != null) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if (fragment instanceof AlarmFragment) {
                    transaction.replace(R.id.frame_layout_main, fragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    transaction.replace(R.id.frame_layout_main, fragment)
                            .commit();
                }
                return true;
            }
            return false;
        });
    }
}