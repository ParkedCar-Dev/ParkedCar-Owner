package com.example.spaceowner.view.bookings;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spaceowner.view.bookings.fragments.CurrentBookingsFragment;
import com.example.spaceowner.view.bookings.fragments.PreviousBookingsFragment;
import com.example.spaceowner.view.bookings.fragments.UpcomingBookingsFragment;

public class BookingViewpagerAdapter extends FragmentStateAdapter {
    public BookingViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new PreviousBookingsFragment();
            case 2: return new UpcomingBookingsFragment();
            default: return new CurrentBookingsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
