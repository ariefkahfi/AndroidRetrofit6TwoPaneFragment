package com.example.arief.belajarandroidretrofit6twopanefragment.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arief.belajarandroidretrofit6twopanefragment.tabfragments.TabOneFragment;
import com.example.arief.belajarandroidretrofit6twopanefragment.tabfragments.TabTwoFragment;

/**
 * Created by Arief on 8/20/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0 :
                return new TabOneFragment();
            case  1 :
                return new TabTwoFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Input Data";
            case 1 :
                return "List Data";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
