package com.beatrice.nearby_nearme_gym.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beatrice.nearby_nearme_gym.activities.fragments.Gym_instructors;
import com.beatrice.nearby_nearme_gym.activities.fragments.Home;
import com.beatrice.nearby_nearme_gym.activities.fragments.Past_workout_sessions;

public class SimplePageAdapter extends FragmentPagerAdapter {

    public  SimplePageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new Past_workout_sessions();
            case 2:
                return new Gym_instructors();
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
