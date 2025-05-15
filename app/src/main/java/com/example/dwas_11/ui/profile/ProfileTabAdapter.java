package com.example.dwas_11.ui.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProfileTabAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 2;
    
    public ProfileTabAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileInfoFragment();
            case 1:
                return new BookingsTabFragment();
            default:
                return new ProfileInfoFragment();
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Profile";
            case 1:
                return "Bookings";
            default:
                return "Profile";
        }
    }
} 