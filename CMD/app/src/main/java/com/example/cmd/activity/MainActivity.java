package com.example.cmd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivityMainBinding;
import com.example.cmd.fragment.AlarmFragment;
import com.example.cmd.fragment.CalendarFragment;
import com.example.cmd.fragment.ProfileFragment;
import com.example.cmd.fragment.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private AlarmFragment alarmFragment = new AlarmFragment();
    private CalendarFragment calendarFragment = new CalendarFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private ScheduleFragment scheduleFragment = new ScheduleFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainActivityTheme);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_main, scheduleFragment).commitAllowingStateLoss();
        //transaction.addToBackStack(null);

        Map<Integer, Fragment> fragments = new HashMap<>();
        fragments.put(R.id.menu_calendar, calendarFragment);
        fragments.put(R.id.menu_schedule, scheduleFragment);
        fragments.put(R.id.menu_alarm, alarmFragment);
        fragments.put(R.id.menu_profile, profileFragment);

        BottomNavigationView bottomNavigationView = binding.bottomNavigationMain;
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = fragments.get(item.getItemId());

                if(fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction1 = fragmentManager.beginTransaction();

                    if(fragment instanceof AlarmFragment) {
                        transaction1.replace(R.id.frame_layout_main, fragment)
                                .addToBackStack(null)
                                .commit();
                    }else{
                        transaction1.replace(R.id.frame_layout_main, fragment)
                                .commit();
                    }
                    return true;
                }

//                if(fragment != null) {
//                    getSupportFragmentManager().beginTransaction()
//                            //.replace(R.id.frame_layout_main, fragments, fragments.class.getSimpleName()).addToBackStack(fragments.class.getSimpleName())
//                            .replace(R.id.frame_layout_main ,fragment)
//                            .commit();
//                    return true;
//                }





//                switch (item.getItemId()){
//                    case **R.id.menu_calendar:**
//                        transaction.replace(R.id.frame_layout_main, calendarFragment).commitAllowingStateLoss();
//                        break;
//                    case R.id.menu_schedule:
//                        transaction.replace(R.id.frame_layout_main, scheduleFragment).commitAllowingStateLoss();
//                        break;
//                    case R.id.menu_alarm:
//                        transaction.replace(R.id.frame_layout_main, alarmFragment).commitAllowingStateLoss();
//                        break;
//                    case R.id.menu_profile:
//                        transaction.replace(R.id.frame_layout_main, profileFragment).commitAllowingStateLoss();
//                        break;
//                }
                return false;
            }
        });
    }
}