package com.example.spaceowner.view.space;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spaceowner.view.space.fragments.SpaceDetailsFragment;
import com.example.spaceowner.view.space.fragments.SpaceRequestsFragment;
import com.example.spaceowner.view.space.fragments.SpaceUpdateFragment;

public class SpaceViewpagerAdapter extends FragmentStateAdapter {
    public static enum SpaceFragmentType{
        REQUESTS(0),
        DETAILS(1),
        UPDATE(2);

        private final int value;
        private SpaceFragmentType(int value){
            this.value = value;
        }
    }


    public SpaceViewpagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new SpaceDetailsFragment();
            case 2: return new SpaceUpdateFragment();
            default: return new SpaceRequestsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
