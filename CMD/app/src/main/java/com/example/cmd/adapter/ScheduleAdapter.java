package com.example.cmd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ScheduleAdapter extends FragmentStateAdapter {

    Fragment monFragment, tuesFragment, wednesFragment, thursFragment, friFragment;
    int NUM_PAGED = 5;

    public ScheduleAdapter(Fragment fragment, Fragment monFragment, Fragment tuesFragment, Fragment wednesFragment, Fragment thursFragment, Fragment friFragment, int NUM_PAGED) {
        super(fragment);

        this.monFragment = monFragment;
        this.tuesFragment = tuesFragment;
        this.wednesFragment = wednesFragment;
        this.thursFragment = thursFragment;
        this.friFragment = friFragment;
        this.NUM_PAGED = NUM_PAGED;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return monFragment;
        } else if (position == 1) {
            return tuesFragment;
        } else if (position == 2) {
            return wednesFragment;
        } else if (position == 3) {
            return thursFragment;
        } else {
            return friFragment;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGED;
    }
}
