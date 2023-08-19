package com.example.spaceowner.view.dashboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spaceowner.view.dashboard.fragments.ActiveSpotsFragment;
import com.example.spaceowner.view.dashboard.fragments.DisabledSpotsFragment;
import com.example.spaceowner.view.dashboard.fragments.RequestedSpotsFragment;

public class DashboardViewpagerAdapter extends FragmentStateAdapter {
    public DashboardViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new DisabledSpotsFragment();
            case 2: return new RequestedSpotsFragment();
            default: return new ActiveSpotsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
