package com.example.cmd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends FragmentPagerAdapter, RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private List<Fragment> fragmentList = new ArrayList<>();

    public AlarmAdapter(@NonNull FragmentManager fm, int behavior){
        super(fm,behavior);
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
