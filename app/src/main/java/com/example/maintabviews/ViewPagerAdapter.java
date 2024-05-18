package com.example.maintabviews;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;



public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        // return the fragment instance based on the position
        switch (position) {
            case 0:
                return new FragmentMandatory();
            case 1:
                return new FragmentOptional();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2; // number of tabs
    }

}


