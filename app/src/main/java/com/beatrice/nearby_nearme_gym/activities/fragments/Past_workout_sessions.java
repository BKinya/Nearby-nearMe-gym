package com.beatrice.nearby_nearme_gym.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beatrice.nearby_nearme_gym.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Past_workout_sessions extends Fragment {


    public Past_workout_sessions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_workout_sessions, container, false);
    }

}