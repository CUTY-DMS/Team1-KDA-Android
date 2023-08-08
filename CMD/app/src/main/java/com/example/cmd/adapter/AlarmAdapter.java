package com.example.cmd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AlarmAdapter extends FragmentStateAdapter {

    Fragment allFragment, weFragment;
    int NUM_PAGED = 2;

    public AlarmAdapter(Fragment fragment, Fragment allFragment, Fragment weFragment, int NUM_PAGED) {
        super(fragment);
        this.allFragment = allFragment;
        this.weFragment = weFragment;
        this.NUM_PAGED = NUM_PAGED;

    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return allFragment;
        } else {
            return weFragment;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGED;
    }
}
