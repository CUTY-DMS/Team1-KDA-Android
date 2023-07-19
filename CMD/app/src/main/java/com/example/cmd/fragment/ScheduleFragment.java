package com.example.cmd.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.R;
import com.example.cmd.adapter.ScheduleAdapter;
import com.example.cmd.databinding.FragmentScheduleBinding;
import com.example.cmd.fragment.schedule.FriFragment;
import com.example.cmd.fragment.schedule.MonFragment;
import com.example.cmd.fragment.schedule.ThursFragment;
import com.example.cmd.fragment.schedule.TuesFragment;
import com.example.cmd.fragment.schedule.WednesFragment;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;


public class ScheduleFragment extends Fragment {

    FragmentScheduleBinding binding;
    Fragment monFragment,tuesFragment,wednesFragment,thursFragment,friFragment;
    final int NUM_PAGED = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentScheduleBinding.inflate(inflater);

        monFragment = new MonFragment();
        tuesFragment = new TuesFragment();
        wednesFragment = new WednesFragment();
        thursFragment = new ThursFragment();
        friFragment = new FriFragment();


        //dotsIndicator.setViewPager2(binding.viewPagerSchedule);

        ViewPager2 viewPager2 = binding.viewPagerSchedule;
        viewPager2.setAdapter(new ScheduleAdapter(this,monFragment,tuesFragment,wednesFragment,thursFragment,friFragment,NUM_PAGED));
        viewPager2.setCurrentItem(0);
        viewPager2.setSaveEnabled(false);

        WormDotsIndicator dotsIndicator = binding.indicatorSchedule;
        //dotsIndicator.setSelected(true);
        dotsIndicator.attachTo(viewPager2);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}