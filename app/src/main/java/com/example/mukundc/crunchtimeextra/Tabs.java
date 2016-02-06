package com.example.mukundc.crunchtimeextra;

/**
 * Created by mukundc on 2/1/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tabs extends FragmentPagerAdapter {

    public Tabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new ExerciseFragment();
            case 1:
                // Games fragment activity
                return new CaloriesFragment();
            case 2:
                // Games fragment activity
                return new NotificationFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}

