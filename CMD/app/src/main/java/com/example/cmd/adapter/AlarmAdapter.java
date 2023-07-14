package com.example.cmd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cmd.fragment.AllNoticeFragment;
import com.example.cmd.fragment.WeClassFragment;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public AlarmAdapter(@NonNull FragmentManager fragmentManager,@NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AllNoticeFragment();
            case 1:
                return new WeClassFragment();
            default:
                return null;
        }
        //return fragmentList.get(position);
    }

//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return fragmentList.get(position);
//    }

//    @Override
//    public int getCount() {
//        return fragmentList.size();
//    }

    @Override
    public int getItemCount() {
        return 2;
        //return fragmentList.size();
    }
}
