package com.example.pushkar.habitcreatingapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pushkar.habitcreatingapp.AddRitual;
import com.example.pushkar.habitcreatingapp.MainActivity;
import com.example.pushkar.habitcreatingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home extends Fragment {


    FloatingActionButton fab;
    public Fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AddRitual.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
