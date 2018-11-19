package com.example.pushkar.habitcreatingapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pushkar.habitcreatingapp.MainActivity;
import com.example.pushkar.habitcreatingapp.Models.RitualData;
import com.example.pushkar.habitcreatingapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_progress extends Fragment {


    ArrayList<RitualData> ritualDataArrayList ;
    public Fragment_progress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ritualDataArrayList = MainActivity.ritualDataList;
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

}
