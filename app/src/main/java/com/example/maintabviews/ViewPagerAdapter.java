package com.example.maintabviews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
                return new Field1();
            case 1:
                return new Field2();
            case 2:
                return new Field3();
            case 3:
                return new Field4();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4; // number of tabs
    }

}


