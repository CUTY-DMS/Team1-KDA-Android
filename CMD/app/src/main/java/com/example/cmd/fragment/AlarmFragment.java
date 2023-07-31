package com.example.cmd.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.AlarmAdapter;
import com.example.cmd.databinding.FragmentAlarmBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AlarmFragment extends Fragment {

    FragmentAlarmBinding binding;

    private SharedPreferences sharedPreferences;


    Fragment allFragment,weFragment;
    final int NUM_PAGED = 2;
    //private static final String TAG = "Alarm_Fragment";

    //private TabLayout tabLayout;
    //private ViewPager2 viewPager2;
    //private AlarmAdapter adapter;

    //AllNoticeFragment allNoticeFragment;
    //WeClassFragment weClassFragment;

    //LinearLayout container;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        //
//        tabLayout = binding.tabLayoutAlarm;
//        viewPager2 = binding.viewPagerAlarm;
//        //adapter = new AlarmAdapter(getActivity().getSupportFragmentManager(), 1);
//        adapter = new AlarmAdapter(getChildFragmentManager(), getLifecycle());
//
//
//        adapter.addFragment(new AllNoticeFragment());
//        adapter.addFragment(new WeClassFragment());
//
//        viewPager2.setAdapter(adapter);
//
//        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//
//            switch (position) {
//                case 0:
//                    tab.setText("전체 공지");
//                    break;
//                case 1:
//                    tab.setText("우리 반 공지");
//                    break;
//
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlarmBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();


        allFragment = new AllNoticeFragment();
        weFragment = new WeClassFragment();

        ViewPager2 viewPager2 = binding.viewPagerAlarm;
        viewPager2.setAdapter(new AlarmAdapter(this,allFragment,weFragment,(int)NUM_PAGED));
        viewPager2.setCurrentItem(0);


        TabLayout tabLayout = binding.tabLayoutAlarm;
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("전체 공지");
                }else {
                    tab.setText("우리 반 공지");
                }
            }
        });
        tabLayoutMediator.attach();

        if(LoginActivity.accessToken == null){
            binding.textviewAlarmLogin.setVisibility(View.VISIBLE);
        }
//---------------------------------------------------------------------------------------
//        adapter = new AlarmAdapter(getChildFragmentManager(), getLifecycle());
//        //container = binding.getRoot().getRootView().findViewById(R.id.container)
//        container = rootView.findViewById(R.id.layout_container);
//        //viewPager2 = container.findViewById(R.id.viewPager_alarm);
//        viewPager2 = new ViewPager2(requireContext());
//        container.addView(viewPager2);
//        //viewPager2 = binding.viewPagerAlarm;
//        viewPager2.setAdapter(adapter);
//
//        tabLayout = binding.tabLayoutAlarm;
//        //tabLayout = rootView.findViewById(R.id.tabLayout_alarm);
//        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//            Log.d("TEST","인"+tab);
//            if(position == 0) {
//                tab.setText("전체 공지");
//                //getChildFragmentManager().beginTransaction().replace(R.id.layout_container, new AllNoticeFragment()).commit();
//            }
//            else {
//                tab.setText("우리 반 공지");
//                //getChildFragmentManager().beginTransaction().replace(R.id.layout_container, new WeClassFragment()).commit();
//            }
//        }).attach();
//
//
//        AllNoticeFragment allNoticeFragment1= new AllNoticeFragment();
//        WeClassFragment weClassFragment1 = new WeClassFragment();
//
//        //FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.d("TEST", "dldl");
//                //FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                if(tab.getPosition() == 0) {
//                    tab.setText("전체 공지");
//                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                    //getChildFragmentManager().beginTransaction().replace(R.id.layout_container, new AllNoticeFragment()).commit();
//                    transaction.replace(R.id.layout_container, allNoticeFragment1);
//                    //transaction.addToBackStack(null);
//                    transaction.commit();
//                    Log.d("TEST","잉인");
//                }
//                else if (tab.getPosition() == 1){
//                    tab.setText("우리 반 공지");
//                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                    //getChildFragmentManager().beginTransaction().replace(R.id.layout_container, new WeClassFragment()).commit();
//                    transaction.replace(R.id.layout_container, weClassFragment1);
//                    Log.d("TEST","db"+weClassFragment1);
//                    //transaction.addToBackStack(null);
//                    transaction.commit();
//                }
//                Log.d("TEST","잉이인");
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


//----------------------------------------------------------------------------

//        adapter = new AlarmAdapter(getActivity().getSupportFragmentManager(), 1);
//
//        adapter.addFragment(new AllNoticeFragment());
//        adapter.addFragment(new WeClassFragment());
//
//        viewPager2.setAdapter(adapter);
//
//        tabLayout.setupWithViewPager(viewPager2);
        //return binding.getRoot();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}